package com.gameball.school;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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
import com.gameball.api.MMFavor;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import retrofit2.Call;
import retrofit2.Response;

public class MMFragment4OrderList extends Fragment {
    public static final int f35PRODUCT_STATUS_ = 1;
    public static final int f36PRODUCT_STATUS_ = 5;
    public static final int f37PRODUCT_STATUS_ = 8;
    public static final int f38PRODUCT_STATUS_ = 9;
    public static final int f39PRODUCT_STATUS_ = 7;
    public static final int f40PRODUCT_STATUS__ = 0;
    public static final int f41PRODUCT_STATUS__ = 4;
    public static final int f42PRODUCT_STATUS_ = 6;
    public final String ITEM_HEADER = "ITEM_HEADER";
    public final int ITEM_TYPE_EXPIRED_HEADER = 44;
    public final int ITEM_TYPE_FINISHED_HEADER = 33;
    public final int ITEM_TYPE_NORMAL = 0;
    public final int ITEM_TYPE_RETURNED_HEADER = 55;
    public final String ITEM_TYPE_TAG = "ITEM_TYPE_TAG";
    public final int ITEM_TYPE_UNCHANGED_HEADER = 22;
    public final int ITEM_TYPE_UNPAID_HEADER = 11;
    private ItemAnimator mCachedAnimator = null;
    private View mFragmentView;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private NormalRecyclerViewAdapter mNormalRecyclerViewAdapter;
    @BindView(2131689660)
    RecyclerView mRecyclerView;
    @BindView(2131689659)
    SwipeRefreshLayout mSwipeRefreshLayout;

    class C07151 implements OnRefreshListener {
        C07151() {
        }

        public void onRefresh() {
            MMFragment4OrderList.this.getOrderList();
        }
    }

    public class NormalRecyclerViewAdapter extends Adapter<ViewHolder> {
        private boolean IS_FINISHED_EDIT = false;
        private boolean IS_UNCHANGED_EDIT = false;
        private boolean IS_UNPAID_EDIT = false;
        private final Context mContext;
        private JsonArray mJsonArrayOfferProducts = new JsonArray();
        private final LayoutInflater mLayoutInflater;
        private String[] mTitles;

        class C02761 implements OnClickListener {
            C02761() {
            }

            public void onClick(View view) {
                NormalRecyclerViewAdapter.this.IS_UNPAID_EDIT = !NormalRecyclerViewAdapter.this.IS_UNPAID_EDIT;
                NormalRecyclerViewAdapter.this.notifyDataSetChanged();
            }
        }

        class C02772 implements OnClickListener {
            C02772() {
            }

            public void onClick(View view) {
                NormalRecyclerViewAdapter.this.IS_UNCHANGED_EDIT = !NormalRecyclerViewAdapter.this.IS_UNCHANGED_EDIT;
                NormalRecyclerViewAdapter.this.notifyDataSetChanged();
            }
        }

        class C02783 implements OnClickListener {

            class C09311 extends MMCustomCallBack {
                C09311() {
                }

                public void onResponse() {
                }

                public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                    MMFragment4OrderList.this.getOrderList();
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

            C02783() {
            }

            public void onClick(View view) {
                MMApplication.sendGoogleAnalyticsEvent("complete", "archive");
                MMAPI.getInstance(MMFragment4OrderList.this.getActivity()).setOrderToHistory(new C09311());
            }
        }

        class C02794 implements OnClickListener {

            class C09321 extends MMCustomCallBack {
                C09321() {
                }

                public void onResponse() {
                }

                public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                    MMFragment4OrderList.this.getOrderList();
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

            C02794() {
            }

            public void onClick(View view) {
                MMApplication.sendGoogleAnalyticsEvent("return", "archive");
                MMAPI.getInstance(MMFragment4OrderList.this.getActivity()).setReturnedToHistory(new C09321());
            }
        }

        class HeaderViewHolder extends ViewHolder {
            @BindView(2131689737)
            TextView mTextViewEdit;

            HeaderViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }
        }

        public class HeaderViewHolder_ViewBinding<T extends HeaderViewHolder> implements Unbinder {
            protected T target;

            @UiThread
            public HeaderViewHolder_ViewBinding(T target, View source) {
                this.target = target;
                target.mTextViewEdit = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_edit, "field 'mTextViewEdit'", TextView.class);
            }

            @CallSuper
            public void unbind() {
                T target = this.target;
                if (target == null) {
                    throw new IllegalStateException("Bindings already cleared.");
                }
                target.mTextViewEdit = null;
                this.target = null;
            }
        }

        class NormalViewHolder extends ViewHolder {
            @BindView(2131689622)
            TextView mTextViewQuantity;
            @BindView(2131689727)
            TextView mTextViewSpecialPrice;
            @BindView(2131689615)
            TextView mTextViewSubTitle;
            @BindView(2131689728)
            TextView mTextViewTaxNumber;
            @BindView(2131689729)
            TextView mTextViewTime;
            @BindView(2131689613)
            TextView mTextViewTitle;
            @BindView(2131689624)
            View mViewArchive;
            @BindView(2131689730)
            View mViewCancel;
            @BindView(2131689736)
            View mViewExchanging;
            @BindView(2131689735)
            View mViewGoToExchange;
            @BindView(2131689733)
            View mViewGoToPay;
            @BindView(2131689734)
            View mViewPaying;
            @BindView(2131689731)
            View mViewReturn;
            @BindView(2131689732)
            View mViewReturning;

            NormalViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
                hideAllButton();
                addAutoShowWatcherToTextView(this.mTextViewTitle);
                addAutoShowWatcherToTextView(this.mTextViewQuantity);
                addAutoShowWatcherToTextView(this.mTextViewSpecialPrice);
                addAutoShowWatcherToTextView(this.mTextViewSubTitle);
                addAutoShowWatcherToTextView(this.mTextViewTime);
                addAutoShowWatcherToTextView(this.mTextViewTaxNumber);
            }

            void addAutoShowWatcherToTextView(final TextView textView) {
                textView.addTextChangedListener(new TextWatcher() {
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        String s = charSequence.toString();
                        if (s.contains(MMFragment4OrderList.this.getString(C0308R.string.pay_expire)) || s.contains(MMFragment4OrderList.this.getString(C0308R.string.exchange_expire))) {
                            try {
                                if ((((new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(s.replace(MMFragment4OrderList.this.getString(C0308R.string.pay_expire), "").replace(MMFragment4OrderList.this.getString(C0308R.string.exchange_expire), "")).getTime() - new Date().getTime()) / 1000) / 60) / 60 < 24) {
                                    textView.setTextColor(ContextCompat.getColor(MMFragment4OrderList.this.getActivity(), C0308R.color.mm_red_e60012));
                                    return;
                                } else {
                                    textView.setTextColor(ContextCompat.getColor(MMFragment4OrderList.this.getActivity(), C0308R.color.mm_gray_989898));
                                    return;
                                }
                            } catch (ParseException e) {
                                Logger.m21e(e.toString(), new Object[0]);
                                textView.setTextColor(ContextCompat.getColor(MMFragment4OrderList.this.getActivity(), C0308R.color.mm_gray_989898));
                                return;
                            }
                        }
                        textView.setTextColor(ContextCompat.getColor(MMFragment4OrderList.this.getActivity(), C0308R.color.mm_black_3d3d3d));
                    }

                    public void afterTextChanged(Editable editable) {
                        textView.setVisibility(0);
                    }
                });
            }

            void hideAllTextView() {
                this.mTextViewTitle.setVisibility(8);
                this.mTextViewQuantity.setVisibility(8);
                this.mTextViewSpecialPrice.setVisibility(8);
                this.mTextViewSubTitle.setVisibility(8);
                this.mTextViewTime.setVisibility(8);
                this.mTextViewTaxNumber.setVisibility(8);
            }

            void hideAllButton() {
                this.mViewCancel.setVisibility(8);
                this.mViewGoToPay.setVisibility(8);
                this.mViewPaying.setVisibility(8);
                this.mViewReturn.setVisibility(8);
                this.mViewReturning.setVisibility(8);
                this.mViewArchive.setVisibility(8);
                this.mViewGoToExchange.setVisibility(8);
                this.mViewExchanging.setVisibility(8);
                this.itemView.setOnClickListener(null);
            }
        }

        public class NormalViewHolder_ViewBinding<T extends NormalViewHolder> implements Unbinder {
            protected T target;

            @UiThread
            public NormalViewHolder_ViewBinding(T target, View source) {
                this.target = target;
                target.mTextViewTitle = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_title, "field 'mTextViewTitle'", TextView.class);
                target.mTextViewQuantity = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_quantity, "field 'mTextViewQuantity'", TextView.class);
                target.mTextViewSpecialPrice = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_special_price, "field 'mTextViewSpecialPrice'", TextView.class);
                target.mTextViewSubTitle = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_subtitle, "field 'mTextViewSubTitle'", TextView.class);
                target.mTextViewTime = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_time, "field 'mTextViewTime'", TextView.class);
                target.mTextViewTaxNumber = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_tax_code, "field 'mTextViewTaxNumber'", TextView.class);
                target.mViewCancel = Utils.findRequiredView(source, C0308R.id.view_cancel, "field 'mViewCancel'");
                target.mViewGoToPay = Utils.findRequiredView(source, C0308R.id.view_go_to_pay, "field 'mViewGoToPay'");
                target.mViewPaying = Utils.findRequiredView(source, C0308R.id.view_paying, "field 'mViewPaying'");
                target.mViewReturn = Utils.findRequiredView(source, C0308R.id.view_return, "field 'mViewReturn'");
                target.mViewReturning = Utils.findRequiredView(source, C0308R.id.view_returning, "field 'mViewReturning'");
                target.mViewArchive = Utils.findRequiredView(source, C0308R.id.view_archive, "field 'mViewArchive'");
                target.mViewGoToExchange = Utils.findRequiredView(source, C0308R.id.view_go_to_exchange, "field 'mViewGoToExchange'");
                target.mViewExchanging = Utils.findRequiredView(source, C0308R.id.view_exchanging, "field 'mViewExchanging'");
            }

            @CallSuper
            public void unbind() {
                T target = this.target;
                if (target == null) {
                    throw new IllegalStateException("Bindings already cleared.");
                }
                target.mTextViewTitle = null;
                target.mTextViewQuantity = null;
                target.mTextViewSpecialPrice = null;
                target.mTextViewSubTitle = null;
                target.mTextViewTime = null;
                target.mTextViewTaxNumber = null;
                target.mViewCancel = null;
                target.mViewGoToPay = null;
                target.mViewPaying = null;
                target.mViewReturn = null;
                target.mViewReturning = null;
                target.mViewArchive = null;
                target.mViewGoToExchange = null;
                target.mViewExchanging = null;
                this.target = null;
            }
        }

        public void cancelEdit() {
            this.IS_UNPAID_EDIT = false;
            this.IS_UNCHANGED_EDIT = false;
            this.IS_FINISHED_EDIT = false;
        }

        public NormalRecyclerViewAdapter(Context context) {
            this.mContext = context;
            this.mLayoutInflater = LayoutInflater.from(context);
        }

        public int getItemViewType(int position) {
            return this.mJsonArrayOfferProducts.get(position).getAsJsonObject().get("ITEM_TYPE_TAG").getAsInt();
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case 0:
                    return new NormalViewHolder(this.mLayoutInflater.inflate(C0308R.layout.oder_list, parent, false));
                case 11:
                    return new HeaderViewHolder(this.mLayoutInflater.inflate(C0308R.layout.oder_list_header_unpaid, parent, false));
                case 22:
                    return new HeaderViewHolder(this.mLayoutInflater.inflate(C0308R.layout.oder_list_header_unchange, parent, false));
                case 33:
                    return new HeaderViewHolder(this.mLayoutInflater.inflate(C0308R.layout.oder_list_header_exchanged, parent, false));
                case 44:
                    return new HeaderViewHolder(this.mLayoutInflater.inflate(C0308R.layout.oder_list_header_expired, parent, false));
                case 55:
                    return new HeaderViewHolder(this.mLayoutInflater.inflate(C0308R.layout.oder_list_header_returned, parent, false));
                default:
                    return new NormalViewHolder(this.mLayoutInflater.inflate(C0308R.layout.oder_list, parent, false));
            }
        }

        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            final JsonObject item = this.mJsonArrayOfferProducts.get(position).getAsJsonObject();
            HeaderViewHolder headerViewHolder = null;
            if (viewHolder instanceof HeaderViewHolder) {
                headerViewHolder = (HeaderViewHolder) viewHolder;
                headerViewHolder.mTextViewEdit.setVisibility(item.get("ITEM_COUNT").getAsInt() == 0 ? 8 : 0);
            }
            TextView textView;
            CharSequence string;
            switch (getItemViewType(position)) {
                case 0:
                    NormalViewHolder normalViewHolder = (NormalViewHolder) viewHolder;
                    normalViewHolder.hideAllTextView();
                    normalViewHolder.mTextViewTitle.setText(item.get("prodTitle").getAsString());
                    normalViewHolder.hideAllButton();
                    switch (item.get("status").getAsInt()) {
                        case 0:
                        case 1:
                            normalViewHolder.mTextViewSubTitle.setText(item.get("subTitle").getAsString());
                            normalViewHolder.mTextViewQuantity.setText(MMFragment4OrderList.this.getString(C0308R.string.order_quantity) + item.get(Param.QUANTITY).getAsString() + MMFragment4OrderList.this.getString(C0308R.string.set) + '(' + item.get("Amount").getAsString() + item.get("unitName").getAsString() + ')');
                            normalViewHolder.mTextViewSpecialPrice.setText(MMFragment4OrderList.this.getString(C0308R.string.total_money) + item.get("totalAmount").getAsString() + MMFragment4OrderList.this.getString(C0308R.string.dolor));
                            normalViewHolder.mTextViewTime.setText(MMFragment4OrderList.this.getString(C0308R.string.pay_expire) + item.get("payExpire").getAsString());
                            normalViewHolder.mTextViewTime.setVisibility(0);
                            if (this.IS_UNPAID_EDIT) {
                                normalViewHolder.mViewCancel.setVisibility(0);
                                normalViewHolder.itemView.setOnClickListener(new OnClickListener() {

                                    class C07161 implements SingleButtonCallback {

                                        class C09331 extends MMCustomCallBack {
                                            C09331() {
                                            }

                                            public void onResponse() {
                                            }

                                            public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                                                MMFragment4OrderList.this.getOrderList();
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

                                        C07161() {
                                        }

                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            MMAPI.getInstance(MMFragment4OrderList.this.getActivity()).setCancelOrder(item.get("orderID").getAsString(), new C09331());
                                        }
                                    }

                                    public void onClick(View view) {
                                        MMApplication.sendGoogleAnalyticsEvent("buy", "cancel", item.get("prodID").getAsString());
                                        new Builder(MMFragment4OrderList.this.getActivity()).content((int) C0308R.string.cancel_hint).positiveText((int) C0308R.string.confirm).negativeText((int) C0308R.string.cancel).cancelable(false).onPositive(new C07161()).show();
                                    }
                                });
                                return;
                            }
                            View view;
                            if (item.get("status").getAsInt() == 0) {
                                view = normalViewHolder.mViewGoToPay;
                            } else {
                                view = normalViewHolder.mViewPaying;
                            }
                            view.setVisibility(0);
                            normalViewHolder.itemView.setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    if (item.get("status").getAsInt() == 0) {
                                        MMApplication.sendGoogleAnalyticsEvent("buy", ProductAction.ACTION_DETAIL, item.get("prodID").getAsString());
                                    }
                                    MMPaymentActivity.startActivity(MMFragment4OrderList.this, item);
                                }
                            });
                            return;
                        case 4:
                            normalViewHolder.mTextViewSubTitle.setText(item.get("subTitle").getAsString());
                            normalViewHolder.mTextViewQuantity.setText(MMFragment4OrderList.this.getString(C0308R.string.un_exchanged_slash_buyed) + item.get("remianingAmount").getAsString() + item.get("unitName").getAsString() + "/" + item.get("Amount").getAsString() + item.get("unitName").getAsString());
                            normalViewHolder.mTextViewTime.setText(MMFragment4OrderList.this.getString(C0308R.string.exchange_expire) + item.get("exchangeExpire").getAsString());
                            normalViewHolder.mTextViewTaxNumber.setText(MMFragment4OrderList.this.getString(C0308R.string.tax_number) + item.get("invoice").getAsString());
                            if (this.IS_UNCHANGED_EDIT) {
                                normalViewHolder.mViewReturn.setVisibility(0);
                                normalViewHolder.itemView.setOnClickListener(new OnClickListener() {
                                    public void onClick(View view) {
                                        MMReturnProduct.startActivity(MMFragment4OrderList.this.getActivity(), item);
                                    }
                                });
                                return;
                            }
                            MMApplication.sendGoogleAnalyticsEvent("exchange", ProductAction.ACTION_DETAIL, item.get("prodID").getAsString());
                            normalViewHolder.mViewGoToExchange.setVisibility(0);
                            normalViewHolder.itemView.setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    MMExchangeActivity.startActivity(MMFragment4OrderList.this.getContext(), item);
                                }
                            });
                            return;
                        case 5:
                            normalViewHolder.mViewExchanging.setVisibility(0);
                            normalViewHolder.mTextViewSubTitle.setText(item.get("subTitle").getAsString());
                            normalViewHolder.mTextViewQuantity.setText(MMFragment4OrderList.this.getString(C0308R.string.un_exchanged_slash_buyed) + item.get("remianingAmount").getAsString() + item.get("unitName").getAsString() + "/" + item.get("Amount").getAsString() + item.get("unitName").getAsString());
                            normalViewHolder.mTextViewTime.setText(MMFragment4OrderList.this.getString(C0308R.string.exchange_expire) + item.get("exchangeExpire").getAsString());
                            normalViewHolder.mTextViewTaxNumber.setText(MMFragment4OrderList.this.getString(C0308R.string.tax_number) + item.get("invoice").getAsString());
                            normalViewHolder.itemView.setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    MMExchangeActivity2.startActivity(MMFragment4OrderList.this.getContext(), item);
                                }
                            });
                            return;
                        case 6:
                            normalViewHolder.mViewReturning.setVisibility(0);
                            normalViewHolder.mTextViewSubTitle.setText(item.get("subTitle").getAsString());
                            normalViewHolder.mTextViewQuantity.setText(MMFragment4OrderList.this.getString(C0308R.string.un_exchanged_slash_buyed) + item.get("remianingAmount").getAsString() + item.get("unitName").getAsString() + "/" + item.get("Amount").getAsString() + item.get("unitName").getAsString());
                            normalViewHolder.mTextViewTaxNumber.setText(MMFragment4OrderList.this.getString(C0308R.string.tax_number) + item.get("invoice").getAsString());
                            normalViewHolder.mTextViewTime.setText(MMFragment4OrderList.this.getString(C0308R.string.exchange_expire) + item.get("exchangeExpire").getAsString());
                            if (item.get("ITEM_HEADER").getAsInt() == 44) {
                                normalViewHolder.mTextViewTime.setTextColor(ContextCompat.getColor(MMFragment4OrderList.this.getActivity(), C0308R.color.mm_gray_989898));
                            }
                            normalViewHolder.itemView.setOnClickListener(new OnClickListener() {

                                class C09301 extends MMCustomCallBack {
                                    C09301() {
                                    }

                                    public void onResponse() {
                                    }

                                    public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                                        JsonObject retnObject = responseJsonObject.get("retnObject").getAsJsonObject();
                                        retnObject.addProperty("transCode1", item.get("returnBarcode1").getAsString());
                                        retnObject.addProperty("transCode2", item.get("returnBarcode2").getAsString());
                                        retnObject.addProperty("status", item.get("status").getAsString());
                                        MMReturnProduct2.startActivity(MMFragment4OrderList.this.getActivity(), retnObject);
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
                                    MMAPI.getInstance(MMFragment4OrderList.this.getActivity()).getCancelOrderMoney(item.get("orderID").getAsString(), new C09301());
                                }
                            });
                            return;
                        case 7:
                            normalViewHolder.mTextViewSubTitle.setText(item.get("subTitle").getAsString());
                            normalViewHolder.mTextViewQuantity.setText(MMFragment4OrderList.this.getString(C0308R.string.un_exchanged_slash_buyed) + item.get("remianingAmount").getAsString() + item.get("unitName").getAsString() + "/" + item.get("Amount").getAsString() + item.get("unitName").getAsString());
                            normalViewHolder.mTextViewTime.setText(MMFragment4OrderList.this.getString(C0308R.string.exchange_expire) + item.get("exchangeExpire").getAsString());
                            normalViewHolder.mTextViewTime.setTextColor(ContextCompat.getColor(MMFragment4OrderList.this.getActivity(), C0308R.color.mm_gray_989898));
                            normalViewHolder.mTextViewTaxNumber.setText(MMFragment4OrderList.this.getString(C0308R.string.tax_number) + item.get("invoice").getAsString());
                            if (item.get("returnAccess").getAsString().equals("y")) {
                                normalViewHolder.mViewReturn.setVisibility(0);
                                normalViewHolder.itemView.setOnClickListener(new OnClickListener() {

                                    class C09291 extends MMCustomCallBack {
                                        C09291() {
                                        }

                                        public void onResponse() {
                                        }

                                        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                                            JsonObject retnObject = responseJsonObject.get("retnObject").getAsJsonObject();
                                            retnObject.addProperty("transCode1", item.get("returnBarcode1").getAsString());
                                            retnObject.addProperty("transCode2", item.get("returnBarcode2").getAsString());
                                            retnObject.addProperty("status", item.get("status").getAsString());
                                            MMReturnProduct2.startActivity(MMFragment4OrderList.this.getActivity(), retnObject);
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
                                        if (item.has("returnBarcode1")) {
                                            MMAPI.getInstance(MMFragment4OrderList.this.getActivity()).getCancelOrderMoney(item.get("orderID").getAsString(), new C09291());
                                            return;
                                        }
                                        MMApplication.sendGoogleAnalyticsEvent("expired", ProductAction.ACTION_DETAIL, item.get("prodID").getAsString());
                                        MMReturnProduct.startActivity(MMFragment4OrderList.this.getActivity(), item);
                                    }
                                });
                                return;
                            } else if (item.get("returnAccess").getAsString().equals("n")) {
                                normalViewHolder.mViewArchive.setVisibility(0);
                                normalViewHolder.itemView.setOnClickListener(new OnClickListener() {
                                    public void onClick(View view) {
                                        MMHistoryDetailActivity.startActivity(MMFragment4OrderList.this, item);
                                    }
                                });
                                return;
                            } else {
                                return;
                            }
                        case 8:
                            normalViewHolder.mTextViewSubTitle.setText(MMFragment4OrderList.this.getString(C0308R.string.total_money) + item.get("totalAmount").getAsString() + MMFragment4OrderList.this.getString(C0308R.string.dolor));
                            normalViewHolder.mTextViewTime.setText(MMFragment4OrderList.this.getString(C0308R.string.pay_time) + item.get("payTime").getAsString());
                            normalViewHolder.itemView.setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    MMApplication.sendGoogleAnalyticsEvent("complete", ProductAction.ACTION_DETAIL, item.get("prodID").getAsString());
                                    MMHistoryDetailActivity.startActivity(MMFragment4OrderList.this, item);
                                }
                            });
                            return;
                        case 9:
                            normalViewHolder.mTextViewSubTitle.setText(MMFragment4OrderList.this.getString(C0308R.string.returned_money) + item.get("returnPrice").getAsString() + MMFragment4OrderList.this.getString(C0308R.string.dolor));
                            normalViewHolder.mTextViewTime.setText(MMFragment4OrderList.this.getString(C0308R.string.returned_time) + item.get("returnTime").getAsString());
                            normalViewHolder.itemView.setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    MMApplication.sendGoogleAnalyticsEvent("return", ProductAction.ACTION_DETAIL, item.get("prodID").getAsString());
                                    MMHistoryDetailActivity.startActivity(MMFragment4OrderList.this, item);
                                }
                            });
                            return;
                        default:
                            return;
                    }
                case 11:
                    textView = headerViewHolder.mTextViewEdit;
                    if (this.IS_UNPAID_EDIT) {
                        string = MMFragment4OrderList.this.getString(C0308R.string.finish);
                    } else {
                        string = MMFragment4OrderList.this.getString(C0308R.string.i_want_to_cancel);
                    }
                    textView.setText(string);
                    headerViewHolder.mTextViewEdit.setOnClickListener(new C02761());
                    return;
                case 22:
                    textView = headerViewHolder.mTextViewEdit;
                    if (this.IS_UNCHANGED_EDIT) {
                        string = MMFragment4OrderList.this.getString(C0308R.string.finish);
                    } else {
                        string = MMFragment4OrderList.this.getString(C0308R.string.i_want_to_return);
                    }
                    textView.setText(string);
                    headerViewHolder.mTextViewEdit.setOnClickListener(new C02772());
                    return;
                case 33:
                    headerViewHolder.mTextViewEdit.setOnClickListener(new C02783());
                    return;
                case 44:
                    headerViewHolder.mTextViewEdit.setVisibility(8);
                    return;
                case 55:
                    headerViewHolder.mTextViewEdit.setOnClickListener(new C02794());
                    return;
                default:
                    return;
            }
        }

        public int getItemCount() {
            return this.mJsonArrayOfferProducts.size();
        }

        public void setOfferProducts(JsonArray offerProducts) {
            this.mJsonArrayOfferProducts = offerProducts;
            notifyDataSetChanged();
        }
    }

    class C09282 extends MMCustomCallBack {
        C09282() {
        }

        public void onResponse() {
        }

        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            JsonObject retnObject = responseJsonObject.get("retnObject").getAsJsonObject();
            JsonArray unPaid = retnObject.get("unPaid").getAsJsonArray();
            JsonArray unExchanged = retnObject.get("unExchanged").getAsJsonArray();
            JsonArray expired = retnObject.get("expired").getAsJsonArray();
            JsonArray returned = retnObject.get("returned").getAsJsonArray();
            JsonArray finished = retnObject.get("finished").getAsJsonArray();
            JsonArray jsonArray = new JsonArray();
            JsonElement unpaidHeader = new JsonObject();
            unpaidHeader.addProperty("ITEM_TYPE_TAG", Integer.valueOf(11));
            unpaidHeader.addProperty("ITEM_COUNT", Integer.valueOf(unPaid.size()));
            JsonElement unchangedHeader = new JsonObject();
            unchangedHeader.addProperty("ITEM_TYPE_TAG", Integer.valueOf(22));
            unchangedHeader.addProperty("ITEM_COUNT", Integer.valueOf(unExchanged.size()));
            JsonElement expiredHeader = new JsonObject();
            expiredHeader.addProperty("ITEM_TYPE_TAG", Integer.valueOf(44));
            expiredHeader.addProperty("ITEM_COUNT", Integer.valueOf(expired.size()));
            JsonElement finishedHeader = new JsonObject();
            finishedHeader.addProperty("ITEM_TYPE_TAG", Integer.valueOf(33));
            finishedHeader.addProperty("ITEM_COUNT", Integer.valueOf(finished.size()));
            JsonElement returnedHeader = new JsonObject();
            returnedHeader.addProperty("ITEM_TYPE_TAG", Integer.valueOf(55));
            returnedHeader.addProperty("ITEM_COUNT", Integer.valueOf(returned.size()));
            Iterator it = unPaid.iterator();
            while (it.hasNext()) {
                JsonElement item = (JsonElement) it.next();
                item.getAsJsonObject().addProperty("ITEM_TYPE_TAG", Integer.valueOf(0));
                item.getAsJsonObject().addProperty("ITEM_HEADER", Integer.valueOf(11));
            }
            it = unExchanged.iterator();
            while (it.hasNext()) {
                item = (JsonElement) it.next();
                item.getAsJsonObject().addProperty("ITEM_TYPE_TAG", Integer.valueOf(0));
                item.getAsJsonObject().addProperty("ITEM_HEADER", Integer.valueOf(22));
            }
            it = expired.iterator();
            while (it.hasNext()) {
                item = (JsonElement) it.next();
                item.getAsJsonObject().addProperty("ITEM_TYPE_TAG", Integer.valueOf(0));
                item.getAsJsonObject().addProperty("ITEM_HEADER", Integer.valueOf(44));
            }
            it = returned.iterator();
            while (it.hasNext()) {
                item = (JsonElement) it.next();
                item.getAsJsonObject().addProperty("ITEM_TYPE_TAG", Integer.valueOf(0));
                item.getAsJsonObject().addProperty("ITEM_HEADER", Integer.valueOf(44));
            }
            it = finished.iterator();
            while (it.hasNext()) {
                item = (JsonElement) it.next();
                item.getAsJsonObject().addProperty("ITEM_TYPE_TAG", Integer.valueOf(0));
                item.getAsJsonObject().addProperty("ITEM_HEADER", Integer.valueOf(33));
            }
            jsonArray.add(unpaidHeader);
            jsonArray.addAll(unPaid);
            jsonArray.add(unchangedHeader);
            jsonArray.addAll(unExchanged);
            jsonArray.add(expiredHeader);
            jsonArray.addAll(expired);
            jsonArray.add(finishedHeader);
            jsonArray.addAll(finished);
            jsonArray.add(returnedHeader);
            jsonArray.addAll(returned);
            Logger.json(jsonArray.toString());
            MMFragment4OrderList.this.mNormalRecyclerViewAdapter.setOfferProducts(jsonArray);
        }

        public boolean onSuccessFailure(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            return false;
        }

        public boolean onFailureQQ(Call<JsonObject> call, Throwable t) {
            return false;
        }

        public void onFinish() {
            MMFragment4OrderList.this.mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (this.mFragmentView == null) {
            this.mFragmentView = inflater.inflate(C0308R.layout.fragment_4_order_list, null);
            ButterKnife.bind((Object) this, this.mFragmentView);
            this.mCachedAnimator = this.mRecyclerView.getItemAnimator();
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, 1);
            this.mNormalRecyclerViewAdapter = new NormalRecyclerViewAdapter(getActivity());
            this.mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(this.mNormalRecyclerViewAdapter);
            this.mRecyclerView.addItemDecoration(new MMItemDecorationLinearLayoutColorDivider(getResources(), C0308R.color.mm_gray_cccccc, 1, 10, 1));
            this.mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
            this.mRecyclerView.setAdapter(this.mHeaderAndFooterRecyclerViewAdapter);
            this.mRecyclerView.setItemAnimator(this.mCachedAnimator);
            if (this.mCachedAnimator instanceof SimpleItemAnimator) {
                ((SimpleItemAnimator) this.mCachedAnimator).setSupportsChangeAnimations(true);
            }
            this.mSwipeRefreshLayout.setOnRefreshListener(new C07151());
            this.mSwipeRefreshLayout.setEnabled(true);
        }
        if (this.mFragmentView.getParent() != null) {
            ((ViewGroup) this.mFragmentView.getParent()).removeAllViews();
        }
        return this.mFragmentView;
    }

    private void getOrderList() {
        this.mNormalRecyclerViewAdapter.cancelEdit();
        this.mSwipeRefreshLayout.setRefreshing(true);
        if (MMFavor.getInstance().account.getToken() == null || MMFavor.getInstance().account.getToken().equals("")) {
            startActivityForResult(new Intent(getActivity(), MMRegistActivity.class), 0);
        } else {
            MMAPI.getInstance().getOrderList(new C09282());
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
        getOrderList();
    }
}
