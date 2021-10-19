package com.example.partnershop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodeScanner extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    SharedPreferences pref;
    QRCodeScanner instance;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        init();
    }

    private void init() {


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        try {

            this.mScannerView = new ZXingScannerView(this);

            setContentView(this.mScannerView);

            mScannerView.setResultHandler(this);
            mScannerView.setFlash(false);
            mScannerView.startCamera(0);

            System.gc();

        } catch (Throwable t) {
            String msg = (t.getMessage() == null || t.getMessage().trim().length() <= 0) ? t.toString() : t.getMessage();
        }
    }

    protected void onResume() {
        super.onResume();

    }

    protected void onPause() {
        super.onPause();

        try {
            mScannerView.stopCamera();
            mScannerView = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        try {
            mScannerView.stopCamera();
            mScannerView = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.finish();
    }


    @Override
    public void handleResult(Result result) {
        Intent intent = new Intent();
        intent.putExtra(Constants.SCANNED_CODE, result.getText());

        mScannerView.resumeCameraPreview(this);
        this.setResult(Activity.RESULT_OK, intent);
        Log.v("TAG", result.getText());
        this.finish();
    }


}
