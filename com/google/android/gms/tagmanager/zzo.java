package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tagmanager.ContainerHolder.ContainerAvailableListener;

class zzo implements ContainerHolder {
    private boolean zzaKt;
    private Status zzahq;
    private Container zzbDa;
    private Container zzbDb;
    private zzb zzbDc;
    private zza zzbDd;
    private TagManager zzbDe;
    private final Looper zzrx;

    public interface zza {
        String zzOC();

        void zzOE();

        void zzgZ(String str);
    }

    private class zzb extends Handler {
        private final ContainerAvailableListener zzbDf;
        final /* synthetic */ zzo zzbDg;

        public zzb(zzo com_google_android_gms_tagmanager_zzo, ContainerAvailableListener containerAvailableListener, Looper looper) {
            this.zzbDg = com_google_android_gms_tagmanager_zzo;
            super(looper);
            this.zzbDf = containerAvailableListener;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    zzhb((String) message.obj);
                    return;
                default:
                    zzbo.m10e("Don't know how to handle this message.");
                    return;
            }
        }

        public void zzha(String str) {
            sendMessage(obtainMessage(1, str));
        }

        protected void zzhb(String str) {
            this.zzbDf.onContainerAvailable(this.zzbDg, str);
        }
    }

    public zzo(Status status) {
        this.zzahq = status;
        this.zzrx = null;
    }

    public zzo(TagManager tagManager, Looper looper, Container container, zza com_google_android_gms_tagmanager_zzo_zza) {
        this.zzbDe = tagManager;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.zzrx = looper;
        this.zzbDa = container;
        this.zzbDd = com_google_android_gms_tagmanager_zzo_zza;
        this.zzahq = Status.zzayh;
        tagManager.zza(this);
    }

    private void zzOD() {
        if (this.zzbDc != null) {
            this.zzbDc.zzha(this.zzbDb.zzOA());
        }
    }

    public synchronized Container getContainer() {
        Container container = null;
        synchronized (this) {
            if (this.zzaKt) {
                zzbo.m10e("ContainerHolder is released.");
            } else {
                if (this.zzbDb != null) {
                    this.zzbDa = this.zzbDb;
                    this.zzbDb = null;
                }
                container = this.zzbDa;
            }
        }
        return container;
    }

    String getContainerId() {
        if (!this.zzaKt) {
            return this.zzbDa.getContainerId();
        }
        zzbo.m10e("getContainerId called on a released ContainerHolder.");
        return "";
    }

    public Status getStatus() {
        return this.zzahq;
    }

    public synchronized void refresh() {
        if (this.zzaKt) {
            zzbo.m10e("Refreshing a released ContainerHolder.");
        } else {
            this.zzbDd.zzOE();
        }
    }

    public synchronized void release() {
        if (this.zzaKt) {
            zzbo.m10e("Releasing a released ContainerHolder.");
        } else {
            this.zzaKt = true;
            this.zzbDe.zzb(this);
            this.zzbDa.release();
            this.zzbDa = null;
            this.zzbDb = null;
            this.zzbDd = null;
            this.zzbDc = null;
        }
    }

    public synchronized void setContainerAvailableListener(ContainerAvailableListener containerAvailableListener) {
        if (this.zzaKt) {
            zzbo.m10e("ContainerHolder is released.");
        } else if (containerAvailableListener == null) {
            this.zzbDc = null;
        } else {
            this.zzbDc = new zzb(this, containerAvailableListener, this.zzrx);
            if (this.zzbDb != null) {
                zzOD();
            }
        }
    }

    String zzOC() {
        if (!this.zzaKt) {
            return this.zzbDd.zzOC();
        }
        zzbo.m10e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }

    public synchronized void zza(Container container) {
        if (!this.zzaKt) {
            if (container == null) {
                zzbo.m10e("Unexpected null container.");
            } else {
                this.zzbDb = container;
                zzOD();
            }
        }
    }

    public synchronized void zzgX(String str) {
        if (!this.zzaKt) {
            this.zzbDa.zzgX(str);
        }
    }

    void zzgZ(String str) {
        if (this.zzaKt) {
            zzbo.m10e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzbDd.zzgZ(str);
        }
    }
}
