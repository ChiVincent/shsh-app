package com.cocosw.favor;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

abstract class Taste {
    protected final String[] defaultValue;
    protected final String key;
    protected final SharedPreferences sp;

    static class BoolTaste extends Taste {
        BoolTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        Object get() {
            boolean z = false;
            SharedPreferences sharedPreferences = this.sp;
            String str = this.key;
            if (this.defaultValue[0] != null) {
                z = Boolean.valueOf(this.defaultValue[0]).booleanValue();
            }
            return Boolean.valueOf(sharedPreferences.getBoolean(str, z));
        }

        @SuppressLint({"CommitPrefEdits"})
        Editor editor(Object object) {
            return this.sp.edit().putBoolean(this.key, ((Boolean) object).booleanValue());
        }
    }

    static class EmptyTaste extends Taste {
        EmptyTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        Object get() {
            return null;
        }

        Editor editor(Object object) {
            return null;
        }
    }

    static class FloatTaste extends Taste {
        FloatTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        Object get() {
            return Float.valueOf(this.sp.getFloat(this.key, this.defaultValue[0] == null ? 0.0f : Float.valueOf(this.defaultValue[0]).floatValue()));
        }

        @SuppressLint({"CommitPrefEdits"})
        Editor editor(Object object) {
            return this.sp.edit().putFloat(this.key, ((Float) object).floatValue());
        }
    }

    static class IntTaste extends Taste {
        IntTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        Object get() {
            int i = 0;
            SharedPreferences sharedPreferences = this.sp;
            String str = this.key;
            if (this.defaultValue[0] != null) {
                i = Integer.valueOf(this.defaultValue[0]).intValue();
            }
            return Integer.valueOf(sharedPreferences.getInt(str, i));
        }

        @SuppressLint({"CommitPrefEdits"})
        Editor editor(Object object) {
            return this.sp.edit().putInt(this.key, ((Integer) object).intValue());
        }
    }

    static class LongTaste extends Taste {
        LongTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        Object get() {
            return Long.valueOf(this.sp.getLong(this.key, this.defaultValue[0] == null ? 0 : Long.valueOf(this.defaultValue[0]).longValue()));
        }

        @SuppressLint({"CommitPrefEdits"})
        Editor editor(Object object) {
            return this.sp.edit().putLong(this.key, ((Long) object).longValue());
        }
    }

    static class SerializableTaste extends Taste {
        SerializableTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        public static byte[] serialize(Object obj) {
            try {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                new ObjectOutputStream(b).writeObject(obj);
                return b.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        public static Object deserialize(byte[] bytes) {
            try {
                return new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        Object get() {
            String gets = this.sp.getString(this.key, null);
            if (TextUtils.isEmpty(gets)) {
                return null;
            }
            return deserialize(Base64.decode(gets, 0));
        }

        @SuppressLint({"CommitPrefEdits"})
        Editor editor(Object object) {
            byte[] bytes = serialize(object);
            if (bytes != null) {
                return this.sp.edit().putString(this.key, Base64.encodeToString(bytes, 0));
            }
            return null;
        }
    }

    @TargetApi(11)
    static class StringSetTaste extends Taste {
        private final HashSet<String> dv;

        StringSetTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
            if (defaultValue == null || defaultValue.length <= 0 || defaultValue[0] == null) {
                this.dv = null;
            } else {
                this.dv = new HashSet(Arrays.asList(defaultValue));
            }
        }

        Object get() {
            return this.sp.getStringSet(this.key, this.dv);
        }

        @SuppressLint({"CommitPrefEdits"})
        Editor editor(Object object) {
            return this.sp.edit().putStringSet(this.key, (Set) object);
        }
    }

    static class StringTaste extends Taste {
        StringTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        public Object get() {
            return this.sp.getString(this.key, this.defaultValue[0]);
        }

        @SuppressLint({"CommitPrefEdits"})
        Editor editor(Object object) {
            return this.sp.edit().putString(this.key, (String) object);
        }
    }

    abstract Editor editor(Object obj);

    abstract Object get();

    Taste(SharedPreferences sp, String key, String[] defaultValue) {
        this.sp = sp;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @SuppressLint({"CommitPrefEdits"})
    protected void save(Editor editor, boolean commit) {
        if (!commit || VERSION.SDK_INT >= 9) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    void set(Object object) {
        save(editor(object), false);
    }

    void commit(Object object) {
        save(editor(object), true);
    }
}
