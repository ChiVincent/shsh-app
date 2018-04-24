package com.cocosw.favor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

public class FavorAdapter {
    private static final String TAG = "Favor";
    private boolean allFavor;
    private boolean log;
    private String prefix;
    private final Map<Class<?>, Map<Method, MethodInfo>> serviceMethodInfoCache;
    private final SharedPreferences sp;

    public static class Builder {
        private String prefix;
        private SharedPreferences sp;

        public Builder(Context context) {
            this.sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        }

        public Builder(SharedPreferences sp) {
            this.sp = sp;
        }

        public Builder prefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        public FavorAdapter build() {
            FavorAdapter adapter = new FavorAdapter(this.sp);
            adapter.prefix = this.prefix;
            return adapter;
        }
    }

    private class FavorHandler implements InvocationHandler {
        private final Map<Method, MethodInfo> methodDetailsCache;

        FavorHandler(Map<Method, MethodInfo> methodDetailsCache) {
            this.methodDetailsCache = methodDetailsCache;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, args);
            }
            MethodInfo methodInfo = FavorAdapter.getMethodInfo(this.methodDetailsCache, method, FavorAdapter.this.sp, FavorAdapter.this.prefix, FavorAdapter.this.allFavor);
            methodInfo.init();
            if (FavorAdapter.this.log) {
                Log.d(FavorAdapter.TAG, methodInfo.toString());
            }
            switch (methodInfo.responseType) {
                case VOID:
                    return methodInfo.set(args);
                case OBJECT:
                    return methodInfo.get();
                case OBSERVABLE:
                    return methodInfo.rxPref;
                default:
                    return null;
            }
        }
    }

    private FavorAdapter(SharedPreferences sp) {
        this.serviceMethodInfoCache = new LinkedHashMap();
        this.sp = sp;
    }

    private static MethodInfo getMethodInfo(Map<Method, MethodInfo> cache, Method method, SharedPreferences sp, String prefix, boolean allFavor) {
        MethodInfo methodInfo;
        synchronized (cache) {
            methodInfo = (MethodInfo) cache.get(method);
            if (methodInfo == null) {
                methodInfo = new MethodInfo(method, sp, prefix, allFavor);
                cache.put(method, methodInfo);
            }
        }
        return methodInfo;
    }

    private static <T> void validateServiceClass(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException("Only interface definitions are supported.");
        } else if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("Interface definitions must not extend other interfaces.");
        }
    }

    public <T> T create(Class<T> service) {
        validateServiceClass(service);
        for (Annotation annotation : service.getDeclaredAnnotations()) {
            if (annotation.annotationType() == AllFavor.class) {
                this.allFavor = true;
            }
        }
        return Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new FavorHandler(getMethodInfoCache(service)));
    }

    void enableLog(boolean enable) {
        this.log = enable;
    }

    private Map<Method, MethodInfo> getMethodInfoCache(Class<?> service) {
        Map<Method, MethodInfo> methodInfoCache;
        synchronized (this.serviceMethodInfoCache) {
            methodInfoCache = (Map) this.serviceMethodInfoCache.get(service);
            if (methodInfoCache == null) {
                methodInfoCache = new LinkedHashMap();
                this.serviceMethodInfoCache.put(service, methodInfoCache);
            }
        }
        return methodInfoCache;
    }
}
