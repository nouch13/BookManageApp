package com.github.nouch13.bookmanageapp

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CompoundBarcodeView

import java.util.List

class Read_QRCode : AppCompatActivity() {

    private var mBarcodeView : CompoundBarcodeView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_readqr)

        mBarcodeView = findViewById(R.id.barcodeView) as CompoundBarcodeView

        mBarcodeView.decodeSingle(object: BarcodeCallback {
            public override fun barcodeResult(barcodeResult:BarcodeResult) {
                val textView = findViewById(R.id.textView) as TextView
                textView.setText(barcodeResult.getText())
            }
            // override fun possibleResultPoints(list:List<ResultPoint>) {}
        })
    }

    }

}
