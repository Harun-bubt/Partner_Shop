package com.example.partnershop;

import android.widget.Toast;

import com.example.partnershop.Model.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
   public static PartnerShopServer partnerShopServer;

    /*public static void doLogin(String shopId,String code){
        PartnerShopServer partnerShopServer;
        partnerShopServer = PartnerShopServer.retrofit.create(PartnerShopServer.class);
        Call<Service> call= partnerShopServer.login(shopId,code);
        call.enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {
                Service service = response.body();
                com.example.partnershop.Model.Response response1 = service.getResponse();
                if(response1.getId().equals("1"))
                {
                    Toast.makeText(GlobalContext.getAppContext(), "Success", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(GlobalContext.getAppContext(), "Not Success", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {
                Toast.makeText(GlobalContext.getAppContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }*/


}
