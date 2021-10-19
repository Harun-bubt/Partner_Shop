package com.example.partnershop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Locale;

import javax.security.auth.callback.Callback;

public class PaymentActivity extends AppCompatActivity implements Callback {
    EditText codeEditText;
    ImageButton scannerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        codeEditText = findViewById(R.id.codeField);
        scannerButton = findViewById(R.id.scanButton);
        scannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivityForResult(new Intent(PaymentActivity.this, QRCodeScanner.class), 1);
                    return;
                } catch (Throwable t) {

                    return;
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == -1) {
            String code = data.getStringExtra(Constants.SCANNED_CODE);
            if (code != null) {
                codeEditText.setText(code);
            }
        }
    }
}