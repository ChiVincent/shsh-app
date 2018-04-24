package com.gameball.api;

import android.preference.PreferenceManager;
import android.support.annotation.Keep;
import com.cocosw.favor.AllFavor;
import com.cocosw.favor.Default;
import com.cocosw.favor.FavorAdapter.Builder;
import com.gameball.school.MMApplication;

public class MMFavor {
    static MMFavor mmFavor = null;
    public Account account;
    public Setting setting;

    @Keep
    @AllFavor
    public interface Account {
        @Default({""})
        String getAlbumUrl();

        @Default({""})
        String getAreaCode();

        @Default({""})
        String getEmail();

        @Default({""})
        String getExpireTime();

        @Default({""})
        String getNewReappear();

        @Default({""})
        String getOriginalPage();

        @Default({""})
        String getPhone();

        @Default({""})
        String getPhotoReappear();

        @Default({""})
        String getRegToken();

        @Default({""})
        String getSettingReappear();

        @Default({""})
        String getToken();

        @Default({""})
        String getUserName();

        @Default({""})
        String getUsrGrade();

        @Default({""})
        String getVipReappear();

        void setAlbumUrl(String str);

        void setAreaCode(String str);

        void setEmail(String str);

        void setExpireTime(String str);

        void setNewReappear(String str);

        void setOriginalPage(String str);

        void setPhone(String str);

        void setPhotoReappear(String str);

        void setRegToken(String str);

        void setSettingReappear(String str);

        void setToken(String str);

        void setUserName(String str);

        void setUsrGrade(String str);

        void setVipReappear(String str);
    }

    @Keep
    @AllFavor
    public interface Setting {
        @Default({""})
        String getAboutNote();

        @Default({""})
        String getEmail();

        @Default({""})
        String getNoServiceStore();

        @Default({""})
        String getPrivacyNote();

        @Default({""})
        String getServeNote();

        @Default({""})
        String getServiceNote();

        @Default({""})
        String getServiceNoteEN();

        @Default({""})
        String getVersionInfo();

        void setAboutNote(String str);

        void setEmail(String str);

        void setNoServiceStore(String str);

        void setPrivacyNote(String str);

        void setServeNote(String str);

        void setServiceNote(String str);

        void setServiceNoteEN(String str);

        void setVersionInfo(String str);
    }

    private MMFavor() {
        this.account = null;
        this.setting = null;
        this.account = (Account) new Builder(MMApplication.getAppContext()).build().create(Account.class);
        this.setting = (Setting) new Builder(MMApplication.getAppContext()).build().create(Setting.class);
    }

    public static MMFavor getInstance() {
        if (mmFavor == null) {
            mmFavor = new MMFavor();
        }
        return mmFavor;
    }

    public void clean() {
        PreferenceManager.getDefaultSharedPreferences(MMApplication.getAppContext()).edit().clear().apply();
    }
}
