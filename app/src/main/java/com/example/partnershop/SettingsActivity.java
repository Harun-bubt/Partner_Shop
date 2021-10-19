package com.example.partnershop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    EditText urlEditText;
    EditText uidEditText;
    Button btnSave;
    ImageView backArrow;
    Spinner cameraSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initiliazeAll();
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();
                if(url == null || url.trim().isEmpty())
                {
                    urlEditText.setError("url required");
                    urlEditText.requestFocus();
                }
                int cameraToUse = cameraSpinner.getSelectedItemPosition();
                String camera;
                if(cameraToUse==0)
                {
                    camera = Constants.BACK;
                }else {
                     camera = Constants.FRONT;
                }
                String uid = uidEditText.getText().toString();
                if(uid == null || uid.trim().isEmpty())
                {
                    uidEditText.setError("userId required");
                    uidEditText.requestFocus();
                }

                if(StoreSettings.storeAllInfo(url,camera,uid)==1)
                {
                    Toast.makeText(getApplicationContext(), "Saved successfully", Toast.LENGTH_SHORT).show();

                   finish();
                }else
                {
                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                }





            }
        });



    }

    public void initiliazeAll()
    {
        urlEditText = findViewById(R.id.apiURLField);
        uidEditText = findViewById(R.id.userIDField);
        btnSave = findViewById(R.id.saveButton);
        backArrow = findViewById(R.id.backArrowImageView);
       cameraSpinner = (Spinner) findViewById(R.id.cameraToUseForScanField);
       cameraSpinner.setAdapter(new StringAdapter(this, new String[]{"Back", "Front"}));


        if(StoreSettings.getUrl().equals("") || StoreSettings.getUrl().equals(null))
        {
            urlEditText.setText(Constants.BASEURL);
        }else
        {
            urlEditText.setText(StoreSettings.getUrl());
        }
        if(!StoreSettings.getCamera().equals("") || !StoreSettings.getCamera().equals(null))
        {
            if(StoreSettings.getCamera().equals(Constants.BACK))
            {
                cameraSpinner.setSelection(0);
            }else
            {
                cameraSpinner.setSelection(1);
            }

        }else
        {
            cameraSpinner.setSelection(0);
        }
        if(!StoreSettings.getUid().equals("") || !StoreSettings.getUid().equals(null))
        {
            uidEditText.setText(StoreSettings.getUid());
        }

    }

}