package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class zzbgj {
    public static final Integer zzbLq = Integer.valueOf(0);
    public static final Integer zzbLr = Integer.valueOf(1);
    private final Context mContext;
    private final ExecutorService zzbFy;

    public zzbgj(Context context) {
        this(context, Executors.newSingleThreadExecutor());
    }

    zzbgj(Context context, ExecutorService executorService) {
        this.mContext = context;
        this.zzbFy = executorService;
    }
}
