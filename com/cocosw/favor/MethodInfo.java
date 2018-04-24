package com.cocosw.favor;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Set;

class MethodInfo {
    static final boolean HAS_RX_JAVA = hasRxJavaOnClasspath();
    private static final String TAG = "Favor";
    private Type FavorType;
    private final boolean allFavor;
    private boolean commit;
    String[] defaultValues = new String[1];
    final boolean isObservable;
    String key;
    boolean loaded = false;
    final Method method;
    private final String prefix;
    Type responseObjectType;
    final ResponseType responseType;
    Object rxPref;
    private final SharedPreferences sp;
    private Taste taste;

    enum ResponseType {
        VOID,
        OBSERVABLE,
        OBJECT
    }

    MethodInfo(Method method, SharedPreferences sp, String prefix, boolean allFavor) {
        boolean z = true;
        this.method = method;
        this.sp = sp;
        this.prefix = prefix;
        this.allFavor = allFavor;
        this.responseType = parseResponseType();
        if (this.responseType != ResponseType.OBSERVABLE) {
            z = false;
        }
        this.isObservable = z;
    }

    private static Type getParameterUpperBound(ParameterizedType type) {
        Type[] types = type.getActualTypeArguments();
        for (int i = 0; i < types.length; i++) {
            Type paramType = types[i];
            if (paramType instanceof WildcardType) {
                types[i] = ((WildcardType) paramType).getUpperBounds()[0];
            }
        }
        return types[0];
    }

    private static boolean hasRxJavaOnClasspath() {
        try {
            Class.forName("com.f2prateek.rx.preferences.Preference");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static void checkDefaultValueType(Type reponse, String[] defaultValues) {
        if (reponse != String.class && defaultValues[0] != null) {
            if (reponse == Integer.TYPE || reponse == Integer.class) {
                Integer.parseInt(defaultValues[0]);
            } else if (reponse == Long.TYPE || reponse == Long.class) {
                Long.parseLong(defaultValues[0]);
            } else if (reponse == Boolean.TYPE || reponse == Boolean.class) {
                Boolean.parseBoolean(defaultValues[0]);
            } else if (reponse == Float.TYPE || reponse == Float.class) {
                Float.parseFloat(defaultValues[0]);
            }
        }
    }

    private RuntimeException methodError(String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        return new IllegalArgumentException(this.method.getDeclaringClass().getSimpleName() + "." + this.method.getName() + ": " + message);
    }

    private RuntimeException parameterError(int index, String message, Object... args) {
        return methodError(message + " (parameter #" + (index + 1) + ")", args);
    }

    synchronized void init() {
        if (!this.loaded) {
            parseMethodAnnotations();
            this.loaded = true;
        }
    }

    private void parseMethodAnnotations() {
        Boolean bool = null;
        for (Annotation methodAnnotation : this.method.getAnnotations()) {
            Class<? extends Annotation> annotationType = methodAnnotation.annotationType();
            if (annotationType == Favor.class) {
                this.key = ((Favor) methodAnnotation).value();
                if (this.key.trim().length() == 0) {
                    this.key = getKeyFromMethod(this.method);
                }
                if (!TextUtils.isEmpty(this.prefix)) {
                    this.key = this.prefix + this.key;
                }
            } else if (annotationType == Default.class) {
                this.defaultValues = ((Default) methodAnnotation).value();
            } else if (annotationType == Commit.class) {
                this.commit = true;
            }
        }
        if (this.allFavor && this.key == null) {
            this.key = getKeyFromMethod(this.method);
            if (!TextUtils.isEmpty(this.prefix)) {
                this.key = this.prefix + this.key;
            }
        }
        if (this.responseType == ResponseType.OBSERVABLE) {
            checkDefaultValueType(this.responseObjectType, this.defaultValues);
            if (this.commit) {
                Log.w(TAG, "@Commit will be ignored for RxReference");
            }
            RxSharedPreferences rx = RxSharedPreferences.create(this.sp);
            if (this.responseObjectType == String.class) {
                this.rxPref = rx.getString(this.key, this.defaultValues[0]);
            } else if (this.responseObjectType == Integer.class) {
                Integer valueOf;
                r7 = this.key;
                if (this.defaultValues[0] != null) {
                    valueOf = Integer.valueOf(this.defaultValues[0]);
                }
                this.rxPref = rx.getInteger(r7, valueOf);
            } else if (this.responseObjectType == Float.class) {
                Float valueOf2;
                r7 = this.key;
                if (this.defaultValues[0] != null) {
                    valueOf2 = Float.valueOf(this.defaultValues[0]);
                }
                this.rxPref = rx.getFloat(r7, valueOf2);
            } else if (this.responseObjectType == Long.class) {
                Long valueOf3;
                r7 = this.key;
                if (this.defaultValues[0] != null) {
                    valueOf3 = Long.valueOf(this.defaultValues[0]);
                }
                this.rxPref = rx.getLong(r7, valueOf3);
            } else if (this.responseObjectType == Boolean.class) {
                r7 = this.key;
                if (this.defaultValues[0] != null) {
                    bool = Boolean.valueOf(this.defaultValues[0]);
                }
                this.rxPref = rx.getBoolean(r7, bool);
            }
        } else if (this.FavorType == String.class) {
            this.taste = new StringTaste(this.sp, this.key, this.defaultValues);
        } else if (this.FavorType == Boolean.TYPE) {
            this.taste = new BoolTaste(this.sp, this.key, this.defaultValues);
        } else if (this.FavorType == Integer.TYPE) {
            this.taste = new IntTaste(this.sp, this.key, this.defaultValues);
        } else if (this.FavorType == Float.TYPE) {
            this.taste = new FloatTaste(this.sp, this.key, this.defaultValues);
        } else if (this.FavorType == Long.TYPE) {
            this.taste = new LongTaste(this.sp, this.key, this.defaultValues);
        } else if (Types.getRawType(this.FavorType) == Set.class) {
            this.taste = new StringSetTaste(this.sp, this.key, this.defaultValues);
        } else if (Serializable.class.isAssignableFrom(Types.getRawType(this.FavorType))) {
            this.taste = new SerializableTaste(this.sp, this.key, this.defaultValues);
        } else {
            this.taste = new EmptyTaste(this.sp, this.key, this.defaultValues);
            throw methodError("Unsupported type " + this.FavorType.toString(), new Object[0]);
        }
    }

    private String getKeyFromMethod(Method method) {
        String value = method.getName().toLowerCase();
        if (value.startsWith("is") && this.FavorType == Boolean.TYPE) {
            return value.substring(2);
        }
        if (value.startsWith("get")) {
            return value.substring(3);
        }
        if (value.startsWith("set")) {
            return value.substring(3);
        }
        return value;
    }

    private ResponseType parseResponseType() {
        Type returnType = this.method.getGenericReturnType();
        Type[] parameterTypes = this.method.getGenericParameterTypes();
        if (parameterTypes.length > 1) {
            throw methodError("%s method has more than one parameter", this.method.getName());
        }
        boolean hasReturnType;
        Type typeToCheck = null;
        if (parameterTypes.length > 0) {
            typeToCheck = parameterTypes[0];
        }
        if (returnType != Void.TYPE) {
            hasReturnType = true;
        } else {
            hasReturnType = false;
        }
        if (typeToCheck != null && hasReturnType) {
            Log.w(TAG, String.format("Setter method %s should not have return value", new Object[]{this.method.getName()}));
            hasReturnType = false;
            returnType = Void.TYPE;
        }
        if (hasReturnType) {
            Class rawReturnType = Types.getRawType(returnType);
            if (HAS_RX_JAVA && rawReturnType == Preference.class) {
                this.responseObjectType = getParameterUpperBound((ParameterizedType) Types.getSupertype(returnType, rawReturnType, Preference.class));
                return ResponseType.OBSERVABLE;
            }
            this.responseObjectType = returnType;
        }
        if (hasReturnType) {
            typeToCheck = returnType;
        }
        this.FavorType = typeToCheck;
        return hasReturnType ? ResponseType.OBJECT : ResponseType.VOID;
    }

    Object get() {
        if (this.responseType == ResponseType.OBSERVABLE) {
            return this.rxPref;
        }
        return this.taste.get();
    }

    Object set(Object[] args) {
        if (this.commit) {
            this.taste.commit(args[0]);
        } else {
            this.taste.set(args[0]);
        }
        return null;
    }

    public String toString() {
        return "MethodInfo{method=" + this.method + ", responseType=" + this.responseType + ", isObservable=" + this.isObservable + ", sp=" + this.sp + ", prefix='" + this.prefix + '\'' + ", allFavor=" + this.allFavor + ", loaded=" + this.loaded + ", responseObjectType=" + this.responseObjectType + ", key='" + this.key + '\'' + ", defaultValues=" + Arrays.toString(this.defaultValues) + ", rxPref=" + this.rxPref + ", taste=" + this.taste + ", commit=" + this.commit + ", FavorType=" + this.FavorType + '}';
    }
}
