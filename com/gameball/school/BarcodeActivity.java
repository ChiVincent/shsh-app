package com.gameball.school;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import com.orhanobut.logger.Logger;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.EnumMap;
import java.util.Map;

public class BarcodeActivity extends Activity {
    private static String BARCODE = "BARCODE";
    public static int REQUEST_CODE_PAY_BARCODE = 845;
    private static String TAG = "TAG";
    @BindView(2131689600)
    ImageView mImageViewBarcodeFirst;
    @BindView(2131689603)
    ImageView mImageViewBarcodeSecond;
    JsonObject mJsonObjectProduct;
    @BindView(2131689601)
    TextView mJustifyTextViewFirstBarcode;
    @BindView(2131689604)
    TextView mJustifyTextViewSecondBarcode;
    @BindView(2131689599)
    TextView mTextViewFirstBarcodeType;
    @BindView(2131689602)
    TextView mTextViewSecondBarcodeType;

    public static void startActivity(Context context, JsonObject product, String tag) {
        Intent intent = new Intent(context, BarcodeActivity.class);
        intent.putExtra(BARCODE, product.toString());
        intent.putExtra(TAG, tag);
        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_PAY_BARCODE);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_barcode);
        ButterKnife.bind((Activity) this);
        this.mJsonObjectProduct = new JsonParser().parse(getIntent().getStringExtra(BARCODE)).getAsJsonObject();
        Logger.json(this.mJsonObjectProduct.toString());
        this.mImageViewBarcodeFirst.setImageBitmap(getBarcode(this.mJsonObjectProduct.get("transCode1").getAsString()));
        this.mImageViewBarcodeSecond.setImageBitmap(getBarcode(this.mJsonObjectProduct.get("transCode2").getAsString()));
        this.mJustifyTextViewFirstBarcode.setText(this.mJsonObjectProduct.get("transCode1").getAsString());
        this.mJustifyTextViewSecondBarcode.setText(this.mJsonObjectProduct.get("transCode2").getAsString());
        this.mTextViewFirstBarcodeType.setText(this.mTextViewFirstBarcodeType.getText() + "【" + getIntent().getStringExtra(TAG) + "】");
        this.mTextViewSecondBarcodeType.setText(this.mTextViewSecondBarcodeType.getText() + "【" + getIntent().getStringExtra(TAG) + "】");
    }

    public void onBackPressed() {
        setResult(-1);
        super.onBackPressed();
    }

    public void cancel(View view) {
        onBackPressed();
    }

    public void ok(View view) {
    }

    public Bitmap getBarcode(String QRCodeContent) {
        try {
            return getQrCodeBitmapOrThrow(QRCodeContent);
        } catch (WriterException e) {
            e.printStackTrace();
            Map<EncodeHintType, Object> hints = new EnumMap(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, HttpRequest.CHARSET_UTF8);
            MultiFormatWriter writer = new MultiFormatWriter();
            try {
                hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
                BitMatrix result = writer.encode(QRCodeContent, BarcodeFormat.CODE_39, 400, Callback.DEFAULT_DRAG_ANIMATION_DURATION);
                Logger.m19d("result.getWidth(): " + result.getWidth());
                Logger.m19d("result.getHeight(): " + result.getHeight());
                Bitmap bitmap = Bitmap.createBitmap(400, Callback.DEFAULT_DRAG_ANIMATION_DURATION, Config.ARGB_8888);
                for (int y = 0; y < Callback.DEFAULT_DRAG_ANIMATION_DURATION; y++) {
                    for (int x = 0; x < 400; x++) {
                        bitmap.setPixel(x, y, result.get(x, y) ? ViewCompat.MEASURED_STATE_MASK : 0);
                    }
                }
                return bitmap;
            } catch (WriterException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    private static Bitmap getQrCodeBitmapOrThrow(String qrCodeDataString) throws WriterException {
        int x;
        MultiFormatWriter writer = new MultiFormatWriter();
        QRCode code = Encoder.encode(qrCodeDataString, ErrorCorrectionLevel.L);
        BitMatrix result = writer.encode(qrCodeDataString, BarcodeFormat.CODE_39, code.getMatrix().getHeight(), code.getMatrix().getWidth());
        int width = result.getWidth();
        int height = result.getHeight();
        Logger.m21e("width: " + width, new Object[0]);
        Logger.m21e("height: " + height, new Object[0]);
        int[] pixels = new int[(width * height)];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (x = 0; x < width; x++) {
                if (result.get(x, y)) {
                    pixels[((width * height) - 1) - (offset + x)] = ViewCompat.MEASURED_STATE_MASK;
                } else {
                    pixels[((width * height) - 1) - (offset + x)] = 0;
                }
            }
        }
        int[] topLeft = result.getTopLeftOnBit();
        int codeMargin = topLeft[0];
        int topMargin = topLeft[1];
        for (x = codeMargin; x < width; x++) {
            if (!result.get(x, topMargin)) {
                int targetSize = x - codeMargin;
                break;
            }
        }
        return Bitmap.createBitmap(pixels, width, height, Config.ARGB_8888);
    }
}
