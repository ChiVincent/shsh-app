package com.orhanobut.logger;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class LoggerPrinter implements Printer {
    private static final int ASSERT = 7;
    private static final String BOTTOM_BORDER = "╚════════════════════════════════════════════════════════════════════════════════════════";
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final int CHUNK_SIZE = 4000;
    private static final int DEBUG = 3;
    private static final String DEFAULT_TAG = "PRETTYLOGGER";
    private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
    private static final int ERROR = 6;
    private static final char HORIZONTAL_DOUBLE_LINE = '║';
    private static final int INFO = 4;
    private static final int JSON_INDENT = 2;
    private static final String MIDDLE_BORDER = "╟────────────────────────────────────────────────────────────────────────────────────────";
    private static final char MIDDLE_CORNER = '╟';
    private static final int MIN_STACK_OFFSET = 3;
    private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";
    private static final String TOP_BORDER = "╔════════════════════════════════════════════════════════════════════════════════════════";
    private static final char TOP_LEFT_CORNER = '╔';
    private static final int VERBOSE = 2;
    private static final int WARN = 5;
    private final ThreadLocal<Integer> localMethodCount = new ThreadLocal();
    private final ThreadLocal<String> localTag = new ThreadLocal();
    private final Settings settings = new Settings();
    private String tag;

    public LoggerPrinter() {
        init(DEFAULT_TAG);
    }

    public Settings init(String tag) {
        if (tag == null) {
            throw new NullPointerException("tag may not be null");
        } else if (tag.trim().length() == 0) {
            throw new IllegalStateException("tag may not be empty");
        } else {
            this.tag = tag;
            return this.settings;
        }
    }

    public Settings getSettings() {
        return this.settings;
    }

    public Printer mo2349t(String tag, int methodCount) {
        if (tag != null) {
            this.localTag.set(tag);
        }
        this.localMethodCount.set(Integer.valueOf(methodCount));
        return this;
    }

    public void mo2340d(String message, Object... args) {
        log(3, null, message, args);
    }

    public void mo2339d(Object object) {
        String message;
        if (object.getClass().isArray()) {
            message = Arrays.deepToString((Object[]) object);
        } else {
            message = object.toString();
        }
        log(3, null, message, new Object[0]);
    }

    public void mo2341e(String message, Object... args) {
        mo2342e(null, message, args);
    }

    public void mo2342e(Throwable throwable, String message, Object... args) {
        log(6, throwable, message, args);
    }

    public void mo2351w(String message, Object... args) {
        log(5, null, message, args);
    }

    public void mo2344i(String message, Object... args) {
        log(4, null, message, args);
    }

    public void mo2350v(String message, Object... args) {
        log(2, null, message, args);
    }

    public void wtf(String message, Object... args) {
        log(7, null, message, args);
    }

    public void json(String json) {
        if (Helper.isEmpty(json)) {
            mo2339d("Empty/Null json content");
            return;
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                mo2339d(new JSONObject(json).toString(2));
            } else if (json.startsWith("[")) {
                mo2339d(new JSONArray(json).toString(2));
            } else {
                mo2341e("Invalid Json", new Object[0]);
            }
        } catch (JSONException e) {
            mo2341e("Invalid Json", new Object[0]);
        }
    }

    public void xml(String xml) {
        if (Helper.isEmpty(xml)) {
            mo2339d("Empty/Null xml content");
            return;
        }
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            mo2339d(xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
        } catch (TransformerException e) {
            mo2341e("Invalid xml", new Object[0]);
        }
    }

    public synchronized void log(int priority, String tag, String message, Throwable throwable) {
        if (this.settings.getLogLevel() != LogLevel.NONE) {
            if (!(throwable == null || message == null)) {
                message = message + " : " + Helper.getStackTraceString(throwable);
            }
            if (throwable != null && message == null) {
                message = Helper.getStackTraceString(throwable);
            }
            if (message == null) {
                message = "No message/exception is set";
            }
            int methodCount = getMethodCount();
            if (Helper.isEmpty(message)) {
                message = "Empty/NULL log message";
            }
            logTopBorder(priority, tag);
            logHeaderContent(priority, tag, methodCount);
            byte[] bytes = message.getBytes();
            int length = bytes.length;
            if (length <= CHUNK_SIZE) {
                if (methodCount > 0) {
                    logDivider(priority, tag);
                }
                logContent(priority, tag, message);
                logBottomBorder(priority, tag);
            } else {
                if (methodCount > 0) {
                    logDivider(priority, tag);
                }
                for (int i = 0; i < length; i += CHUNK_SIZE) {
                    logContent(priority, tag, new String(bytes, i, Math.min(length - i, CHUNK_SIZE)));
                }
                logBottomBorder(priority, tag);
            }
        }
    }

    public void resetSettings() {
        this.settings.reset();
    }

    private synchronized void log(int priority, Throwable throwable, String msg, Object... args) {
        if (this.settings.getLogLevel() != LogLevel.NONE) {
            log(priority, getTag(), createMessage(msg, args), throwable);
        }
    }

    private void logTopBorder(int logType, String tag) {
        logChunk(logType, tag, TOP_BORDER);
    }

    private void logHeaderContent(int logType, String tag, int methodCount) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        if (this.settings.isShowThreadInfo()) {
            logChunk(logType, tag, "║ Thread: " + Thread.currentThread().getName());
            logDivider(logType, tag);
        }
        String level = "";
        int stackOffset = getStackOffset(trace) + this.settings.getMethodOffset();
        if (methodCount + stackOffset > trace.length) {
            methodCount = (trace.length - stackOffset) - 1;
        }
        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + stackOffset;
            if (stackIndex < trace.length) {
                StringBuilder builder = new StringBuilder();
                builder.append("║ ").append(level).append(getSimpleClassName(trace[stackIndex].getClassName())).append(".").append(trace[stackIndex].getMethodName()).append(" ").append(" (").append(trace[stackIndex].getFileName()).append(":").append(trace[stackIndex].getLineNumber()).append(")");
                level = level + "   ";
                logChunk(logType, tag, builder.toString());
            }
        }
    }

    private void logBottomBorder(int logType, String tag) {
        logChunk(logType, tag, BOTTOM_BORDER);
    }

    private void logDivider(int logType, String tag) {
        logChunk(logType, tag, MIDDLE_BORDER);
    }

    private void logContent(int logType, String tag, String chunk) {
        for (String line : chunk.split(System.getProperty("line.separator"))) {
            logChunk(logType, tag, "║ " + line);
        }
    }

    private void logChunk(int logType, String tag, String chunk) {
        String finalTag = formatTag(tag);
        switch (logType) {
            case 2:
                this.settings.getLogAdapter().mo2336v(finalTag, chunk);
                return;
            case 4:
                this.settings.getLogAdapter().mo2335i(finalTag, chunk);
                return;
            case 5:
                this.settings.getLogAdapter().mo2337w(finalTag, chunk);
                return;
            case 6:
                this.settings.getLogAdapter().mo2334e(finalTag, chunk);
                return;
            case 7:
                this.settings.getLogAdapter().wtf(finalTag, chunk);
                return;
            default:
                this.settings.getLogAdapter().mo2333d(finalTag, chunk);
                return;
        }
    }

    private String getSimpleClassName(String name) {
        return name.substring(name.lastIndexOf(".") + 1);
    }

    private String formatTag(String tag) {
        if (Helper.isEmpty(tag) || Helper.equals(this.tag, tag)) {
            return this.tag;
        }
        return this.tag + "-" + tag;
    }

    private String getTag() {
        String tag = (String) this.localTag.get();
        if (tag == null) {
            return this.tag;
        }
        this.localTag.remove();
        return tag;
    }

    private String createMessage(String message, Object... args) {
        return (args == null || args.length == 0) ? message : String.format(message, args);
    }

    private int getMethodCount() {
        Integer count = (Integer) this.localMethodCount.get();
        int result = this.settings.getMethodCount();
        if (count != null) {
            this.localMethodCount.remove();
            result = count.intValue();
        }
        if (result >= 0) {
            return result;
        }
        throw new IllegalStateException("methodCount cannot be negative");
    }

    private int getStackOffset(StackTraceElement[] trace) {
        for (int i = 3; i < trace.length; i++) {
            String name = trace[i].getClassName();
            if (!name.equals(LoggerPrinter.class.getName()) && !name.equals(Logger.class.getName())) {
                return i - 1;
            }
        }
        return -1;
    }
}
