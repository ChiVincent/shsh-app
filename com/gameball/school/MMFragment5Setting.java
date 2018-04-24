package com.gameball.school;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gameball.api.MMFavor;
import com.gameball.api.MMToast;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MMFragment5Setting extends Fragment {
    private static final int FILECHOOSER_RESULTCODE = 2;
    public static final int REQUEST_SELECT_FILE = 100;
    private View mFragmentView;
    private ValueCallback<Uri> mUploadMessage;
    @BindView(2131689678)
    WebView mWebView;
    public ValueCallback<Uri[]> uploadMessage;

    class C02861 extends WebChromeClient {
        C02861() {
        }

        protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            MMFragment5Setting.this.mUploadMessage = uploadMsg;
            Intent i = new Intent("android.intent.action.GET_CONTENT");
            i.addCategory("android.intent.category.OPENABLE");
            i.setType("image/*");
            MMFragment5Setting.this.startActivityForResult(Intent.createChooser(i, "File Browser"), 2);
        }

        @TargetApi(21)
        public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            if (MMFragment5Setting.this.uploadMessage != null) {
                MMFragment5Setting.this.uploadMessage.onReceiveValue(null);
                MMFragment5Setting.this.uploadMessage = null;
            }
            MMFragment5Setting.this.uploadMessage = filePathCallback;
            try {
                MMFragment5Setting.this.startActivityForResult(fileChooserParams.createIntent(), 100);
                return true;
            } catch (ActivityNotFoundException e) {
                MMFragment5Setting.this.uploadMessage = null;
                Toast.makeText(MMFragment5Setting.this.getActivity(), "Cannot Open File Chooser", 1).show();
                return false;
            }
        }

        protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            MMFragment5Setting.this.mUploadMessage = uploadMsg;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("image/*");
            MMFragment5Setting.this.startActivityForResult(Intent.createChooser(intent, "File Browser"), 2);
        }

        protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
            MMFragment5Setting.this.mUploadMessage = uploadMsg;
            Intent i = new Intent("android.intent.action.GET_CONTENT");
            i.addCategory("android.intent.category.OPENABLE");
            i.setType("image/*");
            MMFragment5Setting.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), 2);
        }
    }

    class C02872 implements OnKeyListener {
        C02872() {
        }

        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() != 0 || keyCode != 4 || !MMFragment5Setting.this.mWebView.canGoBack()) {
                return false;
            }
            MMFragment5Setting.this.mWebView.goBack();
            return true;
        }
    }

    class MyWebView extends WebViewClient {
        MyWebView() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("tag", "url：" + url);
            int haveNP = url.indexOf("np=yes");
            Log.i("tag", "haveNP：" + haveNP);
            if (url.startsWith("line://") || url.startsWith("intent://") || url.startsWith("market://") || url.startsWith("jhs://") || haveNP != -1) {
                Intent intent = null;
                try {
                    intent = Intent.parseUri(url, 0);
                    MMFragment5Setting.this.startActivity(intent);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                MMFragment5Setting.this.startActivity(intent);
            } else {
                view.loadUrl(url);
            }
            return true;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!(MMFavor.getInstance().account.getSettingReappear().equals("N") || MMFavor.getInstance().account.getSettingReappear().equals(""))) {
            this.mFragmentView = null;
        }
        if (this.mFragmentView == null) {
            this.mFragmentView = inflater.inflate(C0308R.layout.fragment_2, null);
            ButterKnife.bind((Object) this, this.mFragmentView);
            if (checkAppConnectionStatus(getActivity())) {
                String url = getString(C0308R.string.url) + "service";
                Map<String, String> extraHeaders = new HashMap();
                extraHeaders.put("Access-Token", MMFavor.getInstance().account.getToken());
                this.mWebView.loadUrl(url, extraHeaders);
                this.mWebView.getSettings().setJavaScriptEnabled(true);
                this.mWebView.setWebViewClient(new MyWebView());
                this.mWebView.setWebChromeClient(new C02861());
                this.mWebView.setOnKeyListener(new C02872());
            } else {
                MMToast.showTextToast(MMApplication.getAppContext().getString(C0308R.string.connection_fail));
            }
        }
        if (this.mFragmentView.getParent() != null) {
            ((ViewGroup) this.mFragmentView.getParent()).removeAllViews();
        }
        return this.mFragmentView;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (VERSION.SDK_INT >= 21) {
            if (requestCode == 100 && this.uploadMessage != null) {
                this.uploadMessage.onReceiveValue(FileChooserParams.parseResult(resultCode, data));
                this.uploadMessage = null;
            }
        } else if (requestCode != 2) {
            Toast.makeText(getActivity(), "Failed to Upload Image", 1).show();
        } else if (this.mUploadMessage != null) {
            Uri result;
            if (data != null) {
                getActivity();
                if (resultCode == -1) {
                    result = data.getData();
                    this.mUploadMessage.onReceiveValue(result);
                    this.mUploadMessage = null;
                }
            }
            result = null;
            this.mUploadMessage.onReceiveValue(result);
            this.mUploadMessage = null;
        }
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
}
