package com.gameball.api;

import android.widget.Toast;
import com.gameball.school.MMApplication;

public class MMToast {
    private static Toast mToast = null;

    private MMToast() {
    }

    public static void showTextToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(MMApplication.getAppContext(), msg, 0);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
