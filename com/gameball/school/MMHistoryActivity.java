package com.gameball.school;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.gameball.api.MMAPI;
import com.gameball.api.MMAPI.MMCustomCallBack;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Response;

public class MMHistoryActivity extends Activity {
    private static String HISTORY = "HISTORY";
    private ItemAnimator mCachedAnimator = null;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private JsonArray mJsonArrayHistory = new JsonArray();
    private NormalRecyclerViewAdapter mNormalRecyclerViewAdapter;
    @BindView(2131689660)
    RecyclerView mRecyclerView;
    @BindView(2131689659)
    SwipeRefreshLayout mSwipeRefreshLayout;

    class C07171 implements OnRefreshListener {
        C07171() {
        }

        public void onRefresh() {
        }
    }

    public class NormalRecyclerViewAdapter extends Adapter<NormalViewHolder> {
        private JsonObject TYPE = new JsonObject();
        private final Context mContext;
        private JsonArray mJsonArrayHistoryList = new JsonArray();
        private final LayoutInflater mLayoutInflater;
        private final int f43 = 8;
        private final int f44 = 9;

        class NormalViewHolder extends ViewHolder {
            @BindView(2131689654)
            TextView mTextViewMoney;
            @BindView(2131689619)
            TextView mTextViewOrderId;
            @BindView(2131689622)
            TextView mTextViewQuantity;
            @BindView(2131689739)
            TextView mTextViewStatus;
            @BindView(2131689615)
            TextView mTextViewSubTitle;
            @BindView(2131689738)
            TextView mTextViewTexNumber;
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
                target.mTextViewQuantity = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_quantity, "field 'mTextViewQuantity'", TextView.class);
                target.mTextViewMoney = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_money, "field 'mTextViewMoney'", TextView.class);
                target.mTextViewStatus = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_status, "field 'mTextViewStatus'", TextView.class);
                target.mTextViewOrderId = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_order_id, "field 'mTextViewOrderId'", TextView.class);
                target.mTextViewTexNumber = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_tex_number, "field 'mTextViewTexNumber'", TextView.class);
            }

            @CallSuper
            public void unbind() {
                T target = this.target;
                if (target == null) {
                    throw new IllegalStateException("Bindings already cleared.");
                }
                target.mTextViewTitle = null;
                target.mTextViewSubTitle = null;
                target.mTextViewQuantity = null;
                target.mTextViewMoney = null;
                target.mTextViewStatus = null;
                target.mTextViewOrderId = null;
                target.mTextViewTexNumber = null;
                this.target = null;
            }
        }

        public NormalRecyclerViewAdapter(Context context) {
            this.mContext = context;
            this.mLayoutInflater = LayoutInflater.from(context);
            this.TYPE.addProperty("1", "未付款");
            this.TYPE.addProperty("4", "未兌換完畢");
            this.TYPE.addProperty("7", "已過期");
            this.TYPE.addProperty("8", "已兌換完畢");
            this.TYPE.addProperty("9", "已退貨");
        }

        public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NormalViewHolder(this.mLayoutInflater.inflate(C0308R.layout.recycleview_history, parent, false));
        }

        public void onBindViewHolder(NormalViewHolder viewHolder, int position) {
            final JsonObject history = this.mJsonArrayHistoryList.get(position).getAsJsonObject();
            viewHolder.mTextViewTitle.setText(history.get("prodTitle").getAsString());
            viewHolder.mTextViewSubTitle.setText(MMHistoryActivity.this.getString(C0308R.string.pay_time) + history.get("payTime").getAsString());
            viewHolder.mTextViewQuantity.setText(MMHistoryActivity.this.getString(C0308R.string.buy_quantity) + history.get(Param.QUANTITY).getAsInt() + MMHistoryActivity.this.getString(C0308R.string.set) + "(" + (history.get(Param.QUANTITY).getAsInt() * history.get("setNo").getAsInt()) + history.get("unitName").getAsString() + ")");
            viewHolder.mTextViewMoney.setText(history.get("totalAmount").getAsString());
            viewHolder.mTextViewOrderId.setText(history.get("orderID").getAsString());
            viewHolder.mTextViewTexNumber.setText(history.get("taxNo").getAsString());
            if (this.TYPE.has(history.get("status").getAsString())) {
                viewHolder.mTextViewStatus.setText(this.TYPE.get(history.get("status").getAsString()).getAsString());
                switch (history.get("status").getAsInt()) {
                    case 8:
                        viewHolder.mTextViewMoney.setText(MMHistoryActivity.this.getString(C0308R.string.payed_money) + history.get("totalAmount").getAsString() + MMHistoryActivity.this.getString(C0308R.string.dolor));
                        break;
                    case 9:
                        viewHolder.mTextViewMoney.setText(MMHistoryActivity.this.getString(C0308R.string.returned_money) + history.get("returnPrice").getAsString() + MMHistoryActivity.this.getString(C0308R.string.dolor));
                        break;
                    default:
                        viewHolder.mTextViewMoney.setText(MMHistoryActivity.this.getString(C0308R.string.payed_money) + history.get("totalAmount").getAsString() + MMHistoryActivity.this.getString(C0308R.string.dolor));
                        break;
                }
            }
            viewHolder.mTextViewStatus.setText("");
            viewHolder.mTextViewMoney.setText("");
            viewHolder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MMHistoryDetailActivity.startActivity(MMHistoryActivity.this, history);
                }
            });
        }

        public int getItemCount() {
            return this.mJsonArrayHistoryList.size();
        }

        public void setStoreList(JsonArray settingList) {
            this.mJsonArrayHistoryList = settingList;
            notifyDataSetChanged();
        }
    }

    class C09342 extends MMCustomCallBack {
        C09342() {
        }

        public void onResponse() {
        }

        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            MMHistoryActivity.this.mNormalRecyclerViewAdapter.setStoreList(responseJsonObject.get("retnObject").getAsJsonObject().get("pageData").getAsJsonArray());
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

    public static void startActivity(Context context, JsonArray product) {
        Intent intent = new Intent(context, MMHistoryActivity.class);
        intent.putExtra(HISTORY, product.toString());
        ((Activity) context).startActivityForResult(intent, BarcodeActivity.REQUEST_CODE_PAY_BARCODE);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activiy_history);
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
        this.mSwipeRefreshLayout.setOnRefreshListener(new C07171());
        this.mSwipeRefreshLayout.setEnabled(false);
        this.mNormalRecyclerViewAdapter.setStoreList(this.mJsonArrayHistory);
        MMAPI.getInstance(this).getOrderDetailList("0", "0", new C09342());
    }
}
