package com.gameball.school;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gameball.api.MMFavor;
import com.gameball.api.MMToast;
import com.orhanobut.logger.Logger;
import java.util.HashMap;
import java.util.Map;

public class MMFragment1ContactService extends Fragment {
    private View mFragmentView;
    @BindView(2131689678)
    WebView mWebView;

    class C02731 implements OnKeyListener {
        C02731() {
        }

        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() != 0 || keyCode != 4 || !MMFragment1ContactService.this.mWebView.canGoBack()) {
                return false;
            }
            MMFragment1ContactService.this.mWebView.goBack();
            return true;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!(MMFavor.getInstance().account.getNewReappear().equals("N") || MMFavor.getInstance().account.getNewReappear().equals(""))) {
            this.mFragmentView = null;
        }
        if (this.mFragmentView == null) {
            this.mFragmentView = inflater.inflate(C0308R.layout.fragment_2, null);
            ButterKnife.bind((Object) this, this.mFragmentView);
            if (checkAppConnectionStatus(getActivity())) {
                String url = getString(C0308R.string.url) + "news";
                Map<String, String> extraHeaders = new HashMap();
                extraHeaders.put("Access-Token", MMFavor.getInstance().account.getToken());
                this.mWebView.loadUrl(url, extraHeaders);
                this.mWebView.getSettings().setJavaScriptEnabled(true);
                this.mWebView.setWebViewClient(new WebViewClient());
                this.mWebView.setOnKeyListener(new C02731());
            } else {
                MMToast.showTextToast(MMApplication.getAppContext().getString(C0308R.string.connection_fail));
            }
        }
        if (this.mFragmentView.getParent() != null) {
            ((ViewGroup) this.mFragmentView.getParent()).removeAllViews();
        }
        return this.mFragmentView;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public boolean checkAppConnectionStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        }
        return false;
    }

    public void repp() {
        Logger.m21e("我在測試會不會跑過來唷", new Object[0]);
        onResume();
    }
}
