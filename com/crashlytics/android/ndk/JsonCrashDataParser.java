package com.crashlytics.android.ndk;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.crashlytics.android.core.internal.models.BinaryImageData;
import com.crashlytics.android.core.internal.models.CustomAttributeData;
import com.crashlytics.android.core.internal.models.DeviceData;
import com.crashlytics.android.core.internal.models.SessionEventData;
import com.crashlytics.android.core.internal.models.SignalData;
import com.crashlytics.android.core.internal.models.ThreadData;
import com.crashlytics.android.core.internal.models.ThreadData.FrameData;
import io.fabric.sdk.android.Fabric;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonCrashDataParser {
    private static final String CUSTOM_KEY_JSON_SESSION = "json_session";
    private static final String DATA_DIR = "/data";
    private static final BinaryImageData[] EMPTY_BINARY_IMAGES = new BinaryImageData[0];
    private static final FrameData[] EMPTY_FRAMES = new FrameData[0];
    private static final ThreadData[] EMPTY_THREADS = new ThreadData[0];
    static final int IDX_DEVICE_INFO = 4;
    static final int IDX_MAPS = 6;
    static final int IDX_SIGNAL_INFO = 1;
    static final int IDX_THREADS = 7;
    static final int IDX_TIME = 2;
    static final int IDX_TOP_FRAME = 0;
    static final String KEY_AVAILABLE_INTERNAL_STORAGE = "available_internal_storage";
    static final String KEY_AVAILABLE_PHYSICAL_MEMORY = "available_physical_memory";
    static final String KEY_BATTERY_CAPACITY = "battery";
    static final String KEY_BATTERY_VELOCITY = "battery_velocity";
    static final String KEY_CRASHED = "crashed";
    static final String KEY_FRAMES = "frames";
    static final String KEY_MAPS = "maps";
    static final String KEY_ORIENTATION = "orientation";
    static final String KEY_PC = "pc";
    static final String KEY_PROXIMITY_ENABLED = "proximity_enabled";
    static final String KEY_SIG_CODE = "sig_code";
    static final String KEY_SIG_NAME = "sig_name";
    static final String KEY_SI_ADDR = "si_addr";
    static final String KEY_SYMBOL = "symbol";
    static final String KEY_THREADS = "threads";
    static final String KEY_THREAD_NAME = "name";
    static final String KEY_TIME = "time";
    static final String KEY_TOTAL_INTERNAL_STORAGE = "total_internal_storage";
    static final String KEY_TOTAL_PHYSICAL_MEMORY = "total_physical_memory";
    private static final String TAG = "JsonCrashDataParser";
    private final FileIdStrategy fileIdStrategy;

    public JsonCrashDataParser() {
        this(new Sha1FileIdStrategy());
    }

    JsonCrashDataParser(FileIdStrategy fileIdStrategy) {
        this.fileIdStrategy = fileIdStrategy;
    }

    public SessionEventData parseCrashEventData(String jsonString) throws JSONException {
        String[] jsonLines = jsonString.split("\\n");
        JSONObject topFrame = null;
        JSONObject signalInfo = null;
        JSONObject time = null;
        JSONObject deviceInfo = null;
        JSONObject threadsObj = null;
        JSONObject mapsObj = null;
        int i = 0;
        while (i < jsonLines.length) {
            try {
                JSONObject parsed = new JSONObject(jsonLines[i]);
                switch (i) {
                    case 0:
                        topFrame = parsed;
                        break;
                    case 1:
                        signalInfo = parsed;
                        break;
                    case 2:
                        time = parsed;
                        break;
                    case 4:
                        deviceInfo = parsed;
                        break;
                    case 6:
                        mapsObj = parsed;
                        break;
                    case 7:
                        threadsObj = parsed;
                        break;
                    default:
                        break;
                }
                i++;
            } catch (JSONException e) {
            }
        }
        if (time == null || signalInfo == null || topFrame == null) {
            throw new JSONException("Could not parse required crash data");
        }
        return new SessionEventData(time.getLong(KEY_TIME), parseSignalData(signalInfo), threadsObj == null ? parseTopFrameData(topFrame) : parseThreadData(threadsObj), parseBinaryImageData(mapsObj), parseCustomAttributes(jsonString), deviceInfo != null ? parseDeviceData(deviceInfo) : null);
    }

    public DeviceData parseDeviceData(JSONObject jsonCrash) {
        return new DeviceData(jsonCrash.optInt(KEY_ORIENTATION), jsonCrash.optLong(KEY_TOTAL_PHYSICAL_MEMORY), jsonCrash.optLong(KEY_TOTAL_INTERNAL_STORAGE), jsonCrash.optLong(KEY_AVAILABLE_PHYSICAL_MEMORY), jsonCrash.optLong(KEY_AVAILABLE_INTERNAL_STORAGE), jsonCrash.optInt(KEY_BATTERY_CAPACITY), jsonCrash.optInt(KEY_BATTERY_VELOCITY, 1), jsonCrash.optBoolean(KEY_PROXIMITY_ENABLED, false));
    }

    public SignalData parseSignalData(JSONObject jsonCrash) {
        return new SignalData(jsonCrash.optString(KEY_SIG_NAME, ""), jsonCrash.optString(KEY_SIG_CODE, ""), jsonCrash.optLong(KEY_SI_ADDR));
    }

    public ThreadData[] parseTopFrameData(JSONObject topFrame) {
        FrameData[] frames = new FrameData[]{parseFrame(topFrame, 4)};
        return new ThreadData[]{new ThreadData(4, frames)};
    }

    public BinaryImageData[] parseBinaryImageData(JSONObject jsonCrash) {
        if (jsonCrash == null) {
            return EMPTY_BINARY_IMAGES;
        }
        try {
            String[] mapsEntries = joinMapsEntries(jsonCrash.getJSONArray(KEY_MAPS)).split("\\|");
            List<BinaryImageData> binaryImages = new LinkedList();
            for (String mapEntryString : mapsEntries) {
                ProcMapEntry mapInfo = ProcMapEntryParser.parse(mapEntryString);
                if (mapInfo != null && isRelevant(mapInfo)) {
                    String path = mapInfo.path;
                    try {
                        binaryImages.add(new BinaryImageData(mapInfo.address, mapInfo.size, path, this.fileIdStrategy.getId(getLibraryFile(path))));
                    } catch (IOException e) {
                        Fabric.getLogger().mo2377d(TAG, "Could not generate ID for file " + mapInfo.path, e);
                    }
                }
            }
            return (BinaryImageData[]) binaryImages.toArray(new BinaryImageData[binaryImages.size()]);
        } catch (JSONException e2) {
            return EMPTY_BINARY_IMAGES;
        }
    }

    public ThreadData[] parseThreadData(JSONObject jsonCrash) {
        JSONArray jsonThreads = jsonCrash.optJSONArray(KEY_THREADS);
        if (jsonThreads == null) {
            return EMPTY_THREADS;
        }
        int len = jsonThreads.length();
        ThreadData[] threads = new ThreadData[len];
        for (int i = 0; i < len; i++) {
            JSONObject jsonThread = jsonThreads.optJSONObject(i);
            String threadName = jsonThread.optString(KEY_THREAD_NAME);
            int threadImportance = jsonThread.optBoolean(KEY_CRASHED) ? 4 : 0;
            threads[i] = new ThreadData(threadName, threadImportance, parseFrames(jsonThread, threadImportance));
        }
        return threads;
    }

    public FrameData[] parseFrames(JSONObject jsonThread, int threadImportance) {
        JSONArray jsonFrames = jsonThread.optJSONArray(KEY_FRAMES);
        if (jsonFrames == null) {
            return EMPTY_FRAMES;
        }
        int len = jsonFrames.length();
        FrameData[] frames = new FrameData[len];
        for (int i = 0; i < len; i++) {
            JSONObject frame = jsonFrames.optJSONObject(i);
            if (frame != null) {
                frames[i] = parseFrame(frame, threadImportance);
            }
        }
        return frames;
    }

    public FrameData parseFrame(JSONObject frame, int threadImportance) {
        String symbol;
        long pc = frame.optLong(KEY_PC);
        String maybeSymbol = frame.optString(KEY_SYMBOL);
        if (maybeSymbol == null) {
            symbol = "";
        } else {
            symbol = maybeSymbol;
        }
        return new FrameData(pc, symbol, "", 0, threadImportance);
    }

    public CustomAttributeData[] parseCustomAttributes(String json) {
        return new CustomAttributeData[]{new CustomAttributeData(CUSTOM_KEY_JSON_SESSION, json)};
    }

    private static String joinMapsEntries(JSONArray array) throws JSONException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length(); i++) {
            sb.append(array.getString(i));
        }
        return sb.toString();
    }

    private static File getLibraryFile(String path) {
        File libFile = new File(path);
        if (libFile.exists()) {
            return libFile;
        }
        return correctDataPath(libFile);
    }

    private static File correctDataPath(File missingFile) {
        if (!missingFile.getAbsolutePath().startsWith(DATA_DIR)) {
            return missingFile;
        }
        try {
            Context context = CrashlyticsNdk.getInstance().getContext();
            return new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).nativeLibraryDir, missingFile.getName());
        } catch (NameNotFoundException e) {
            Fabric.getLogger().mo2379e(TAG, "Error getting ApplicationInfo", e);
            return missingFile;
        }
    }

    private static boolean isRelevant(ProcMapEntry mapEntry) {
        return (mapEntry.perms.indexOf(120) == -1 || mapEntry.path.indexOf(47) == -1) ? false : true;
    }
}
