package com.github.nouch13.bookmanageapp;

import android.content.ClipData;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.widget.Toast;
import android.view.Gravity;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Read_QRCode extends AppCompatActivity {

    private CompoundBarcodeView mBarcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readqr);

        final String isbnParrtern = "^978";
        final Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        final Pattern p = Pattern.compile(isbnParrtern);

        mBarcodeView = (CompoundBarcodeView)findViewById(R.id.barcodeView);
        mBarcodeView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult barcodeResult) {

                // クリップボード変数を定義する
                ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                Matcher mc = p.matcher(barcodeResult.getText());
                if (mc.find()){
                    ClipData clipData = ClipData.newPlainText("label", barcodeResult.getText());
                    clipboardManager.setPrimaryClip(clipData);
                    toastMake("Barcode data has been to clipboard.",0,250);
                    v.vibrate(200); // QR読み取り時にVibration
                    finish(); // QRを読んたので，ブラウザに戻る
                }else{
                    // ISBNコードが判るバーコードではない
                    // 1段目のバーコードを読み取るようにして下さい
                    toastMake("This barcode doesn't have relation to isbn.",0,250);
                }
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> list) {}
        });
    }

    private void toastMake(String message, int x, int y){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER|Gravity.BOTTOM, x, y);
        toast.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBarcodeView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBarcodeView.pause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            finish();
            return true;
        }
        return false;
    }

}
