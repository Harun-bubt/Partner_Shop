package com.example.partnershop;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partnershop.Model.Service;

import java.net.MalformedURLException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ImageView settingsImageView;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeAllViews();
        settingsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSettingsMenuClicked();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onLoginClicked();

            }
        });
    }

    public void initializeAllViews() {
        settingsImageView = findViewById(R.id.imageViewSetting);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void onSettingsMenuClicked() {
        View view = getLayoutInflater().inflate(R.layout.settings_password_screen, null);
        EditText passwordField = (EditText) view.findViewById(R.id.passwordField);
        passwordField.setHintTextColor(getResources().getColor(R.color.black));
        Button submitButton = (Button) view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitClicked(submitButton);
            }
        });
        final Dialog dialog = new LoginActivity.C02323(this, view);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.requestWindowFeature(1);
        submitButton.setTag(dialog);
        dialog.show();
    }
    private void onLoginClicked() {
        View view = getLayoutInflater().inflate(R.layout.settings_password_screen, null);
        EditText passwordField = (EditText) view.findViewById(R.id.passwordField);
        passwordField.setHint("enter access code");
        passwordField.setHintTextColor(getResources().getColor(R.color.black));
        Button submitButton = (Button) view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClicked(submitButton);
            }
        });
        final Dialog dialog = new LoginActivity.C02323(this, view);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.requestWindowFeature(1);
        submitButton.setTag(dialog);
        dialog.show();
    }

    class C02323 extends Dialog {
        final /* synthetic */ View val$view;

        C02323(Context x0, View view) {
            super(x0);
            this.val$view = view;

        }

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(this.val$view);
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.height = -2;
            params.width = -1;
            getWindow().setAttributes(params);

        }
    }

    private void onSubmitClicked(Button b) {


        Dialog dialog = (Dialog) b.getTag();
        EditText passwordField = (EditText) dialog.findViewById(R.id.passwordField);
        String password = passwordField.getText().toString();
        if (password == null || password.trim().isEmpty()) {
            Toast.makeText(this, "You forgot to enter the password", Toast.LENGTH_SHORT).show();
            passwordField.requestFocus();
        } else if (password.equals(Constants.PASSWORD)) {
            dialog.dismiss();
            startActivity(new Intent(this, SettingsActivity.class));

        } else {
            Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
            passwordField.requestFocus();
        }
    }
    private void onLoginClicked(Button b) {


        Dialog dialog = (Dialog) b.getTag();
        EditText passwordField = (EditText) dialog.findViewById(R.id.passwordField);
        String accessId = passwordField.getText().toString();
        if (accessId == null || accessId.trim().isEmpty()) {
            Toast.makeText(this, "You forgot to enter the access code", Toast.LENGTH_SHORT).show();
            passwordField.requestFocus();
        }
        String partnerId = StoreSettings.getUid();
        if(partnerId.equals(null) || partnerId.equals(""))
        {
            Toast.makeText(this, "Enter the partnershopid in setting screen", Toast.LENGTH_SHORT).show();
        }else
        {
            try {
                PartnerShopServer partnerShopServer = PartnerShopServer.retrofit.create(PartnerShopServer.class);
                Call<Service> call = partnerShopServer.login(StoreSettings.getUrl(),"verifyaccesscode", Integer.valueOf(partnerId), Integer.valueOf(accessId));
                call.enqueue(new Callback<Service>() {
                    @Override
                    public void onResponse(Call<Service> call, Response<Service> response) {
                        Service service = response.body();
                        if(service == null){
                            Toast.makeText(GlobalContext.getAppContext(), "url invalid", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        com.example.partnershop.Model.Response response1 = service.getResponse();
                        if (response1.getId().equals("1")) {
                            Toast.makeText(GlobalContext.getAppContext(), response1.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(GlobalContext.getAppContext(), response1.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Service> call, Throwable t) {
                        Toast.makeText(GlobalContext.getAppContext(), "Url Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                Toast.makeText(GlobalContext.getAppContext(), "Url Failed", Toast.LENGTH_SHORT).show();
            }
        }


    }


}
