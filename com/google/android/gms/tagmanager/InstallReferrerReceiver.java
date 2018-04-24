package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.CampaignTrackingService;

public final class InstallReferrerReceiver extends CampaignTrackingReceiver {
    protected Class<? extends CampaignTrackingService> zzlR() {
        return InstallReferrerService.class;
    }

    protected void zzp(Context context, String str) {
        zzbf.zzhn(str);
        zzbf.zzG(context, str);
    }
}
