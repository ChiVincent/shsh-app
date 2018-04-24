package com.gameball.school;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.Builder;
import com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback;
import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.gameball.api.MMAPI;
import com.gameball.api.MMAPI.MMCustomCallBack;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Iterator;
import retrofit2.Call;
import retrofit2.Response;

public class MMNotificationActivity extends Activity {
    private ItemAnimator mCachedAnimator = null;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private NormalRecyclerViewAdapter mNormalRecyclerViewAdapter;
    @BindView(2131689660)
    RecyclerView mRecyclerView;
    private String mStringNotifyAccess = "Y";
    @BindView(2131689659)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(2131689662)
    Switch mSwitchNotification;
    private OnCheckedChangeListener onCheckedChangeListener;

    class C02982 implements OnCheckedChangeListener {
        C02982() {
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        }
    }

    class C07191 implements OnRefreshListener {
        C07191() {
        }

        public void onRefresh() {
        }
    }

    class C07204 implements SingleButtonCallback {

        class C09401 extends MMCustomCallBack {
            C09401() {
            }

            public void onResponse() {
            }

            public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                MMNotificationActivity.this.mNormalRecyclerViewAdapter.setNotificationList(new JsonArray());
            }

            public boolean onSuccessFailure(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                return false;
            }

            public boolean onFailureQQ(Call<JsonObject> call, Throwable t) {
                return false;
            }

            public void onFinish() {
            }
        }

        C07204() {
        }

        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            MMAPI.getInstance(MMNotificationActivity.this).setNotificationClean(new C09401());
        }
    }

    public class NormalRecyclerViewAdapter extends Adapter<NormalViewHolder> {
        private final Context mContext;
        public JsonArray mJsonArrayNotificationList = new JsonArray();
        private final LayoutInflater mLayoutInflater;

        class NormalViewHolder extends ViewHolder {
            @BindView(2131689615)
            TextView mTextViewSubTitle;
            @BindView(2131689613)
            TextView mTextViewTitle;
            View mView;

            NormalViewHolder(View view) {
                super(view);
                this.mView = view;
                ButterKnife.bind((Object) this, view);
            }
        }

        public class NormalViewHolder_ViewBinding<T extends NormalViewHolder> implements Unbinder {
            protected T target;

            @UiThread
            public NormalViewHolder_ViewBinding(T target, View source) {
                this.target = target;
                target.mTextViewTitle = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_title, "field 'mTextViewTitle'", TextView.class);
                target.mTextViewSubTitle = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_subtitle, "field 'mTextViewSubTitle'", TextView.class);
            }

            @CallSuper
            public void unbind() {
                T target = this.target;
                if (target == null) {
                    throw new IllegalStateException("Bindings already cleared.");
                }
                target.mTextViewTitle = null;
                target.mTextViewSubTitle = null;
                this.target = null;
            }
        }

        public NormalRecyclerViewAdapter(Context context) {
            this.mContext = context;
            this.mLayoutInflater = LayoutInflater.from(context);
        }

        public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NormalViewHolder(this.mLayoutInflater.inflate(C0308R.layout.recycleview_notification, parent, false));
        }

        public void onBindViewHolder(NormalViewHolder viewHolder, int position) {
            final JsonObject history = this.mJsonArrayNotificationList.get(position).getAsJsonObject();
            viewHolder.mTextViewTitle.setText(history.get("subject").getAsString());
            viewHolder.mTextViewSubTitle.setText(history.get("time").getAsString());
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(MMNotificationActivity.this, history.get("read").getAsInt() == 0 ? C0308R.color.mm_gray_cccccc : 17170445));
            viewHolder.itemView.setOnClickListener(new OnClickListener() {

                class C09421 extends MMCustomCallBack {
                    C09421() {
                    }

                    public void onResponse() {
                    }

                    public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                        history.addProperty("read", Integer.valueOf(1));
                        NormalRecyclerViewAdapter.this.notifyDataSetChanged();
                        String url = history.get("url_schema").getAsString();
                        Intent i = new Intent("android.intent.action.VIEW");
                        i.setData(Uri.parse(url));
                        MMNotificationActivity.this.startActivity(i);
                    }

                    public boolean onSuccessFailure(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                        return false;
                    }

                    public boolean onFailureQQ(Call<JsonObject> call, Throwable t) {
                        return false;
                    }

                    public void onFinish() {
                    }
                }

                public void onClick(View view) {
                    if (history.get("read").getAsInt() == 1) {
                        String url = history.get("url_schema").getAsString();
                        Intent i = new Intent("android.intent.action.VIEW");
                        i.setData(Uri.parse(url));
                        MMNotificationActivity.this.startActivity(i);
                        return;
                    }
                    MMApplication.sendGoogleAnalyticsEvent("notify", "read", history.get("notifyID").getAsString());
                    MMAPI.getInstance(MMNotificationActivity.this).setNotificationRead(history.get("notifyID").getAsString(), new C09421());
                }
            });
        }

        public int getItemCount() {
            return this.mJsonArrayNotificationList.size();
        }

        public void setNotificationList(JsonArray settingList) {
            this.mJsonArrayNotificationList = settingList;
            notifyDataSetChanged();
        }
    }

    class C09393 extends MMCustomCallBack {
        C09393() {
        }

        public void onResponse() {
        }

        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            MMNotificationActivity.this.mStringNotifyAccess = responseJsonObject.get("retnObject").getAsJsonObject().get("notifyAccess").getAsString();
            MMNotificationActivity.this.mNormalRecyclerViewAdapter.setNotificationList(responseJsonObject.get("retnObject").getAsJsonObject().get("notifyData").getAsJsonArray());
            MMNotificationActivity.this.mSwitchNotification.setOnCheckedChangeListener(null);
            MMNotificationActivity.this.mSwitchNotification.setChecked(responseJsonObject.get("retnObject").getAsJsonObject().get("notifyAccess").getAsString().equals("Y"));
            MMNotificationActivity.this.mSwitchNotification.setOnCheckedChangeListener(MMNotificationActivity.this.onCheckedChangeListener);
        }

        public boolean onSuccessFailure(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            return false;
        }

        public boolean onFailureQQ(Call<JsonObject> call, Throwable t) {
            return false;
        }

        public void onFinish() {
        }
    }

    class C09415 extends MMCustomCallBack {
        C09415() {
        }

        public void onResponse() {
        }

        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            Iterator it = MMNotificationActivity.this.mNormalRecyclerViewAdapter.mJsonArrayNotificationList.iterator();
            while (it.hasNext()) {
                ((JsonElement) it.next()).getAsJsonObject().addProperty("read", Integer.valueOf(1));
            }
            MMNotificationActivity.this.mNormalRecyclerViewAdapter.notifyDataSetChanged();
        }

        public boolean onSuccessFailure(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            return false;
        }

        public boolean onFailureQQ(Call<JsonObject> call, Throwable t) {
            return false;
        }

        public void onFinish() {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activiy_notification);
        ButterKnife.bind((Activity) this);
        this.mCachedAnimator = this.mRecyclerView.getItemAnimator();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, 1);
        this.mNormalRecyclerViewAdapter = new NormalRecyclerViewAdapter(this);
        this.mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(this.mNormalRecyclerViewAdapter);
        this.mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        this.mRecyclerView.setAdapter(this.mHeaderAndFooterRecyclerViewAdapter);
        this.mRecyclerView.setItemAnimator(this.mCachedAnimator);
        if (this.mCachedAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) this.mCachedAnimator).setSupportsChangeAnimations(true);
        }
        this.mSwipeRefreshLayout.setOnRefreshListener(new C07191());
        this.mSwipeRefreshLayout.setEnabled(false);
        this.onCheckedChangeListener = new C02982();
        this.mSwitchNotification.setOnCheckedChangeListener(this.onCheckedChangeListener);
        MMAPI.getInstance(this).getNotification(new C09393());
    }

    public void cleanAllNotification(View view) {
        MMApplication.sendGoogleAnalyticsEvent("notify", "clean_all");
        new Builder(this).title((CharSequence) "確定要清除嗎?").content((CharSequence) "是否要清除所有推播訊息?").positiveText((int) C0308R.string.confirm).negativeText((int) C0308R.string.cancel).cancelable(false).onPositive(new C07204()).show();
    }

    public void readAllNotification(View view) {
        MMApplication.sendGoogleAnalyticsEvent("notify", "read_all");
        MMAPI.getInstance(this).setNotificationReadAll(new C09415());
    }
}
