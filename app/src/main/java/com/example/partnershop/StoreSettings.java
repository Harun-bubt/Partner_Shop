package com.example.partnershop;

import android.content.Context;
import android.content.SharedPreferences;

import java.net.MalformedURLException;
import java.net.URL;

public class StoreSettings {

    public  static  SharedPreferences sharedPreferences;
    public  static SharedPreferences.Editor editor;

    public static int storeAllInfo(String url,String camera,String uid)
    {
       try {
           sharedPreferences = GlobalContext.getAppContext().getSharedPreferences(Constants.SETTINGS, Context.MODE_PRIVATE);
           editor = sharedPreferences.edit();
           editor.putString(Constants.URL,url);
           editor.putString(Constants.CAMERA,camera);
           editor.putString(Constants.USERID,uid);
           editor.apply();
           return 1;
       }catch (Exception e)
       {
           return  0;
       }


    }
    public static String getUrl()
    {
        sharedPreferences = GlobalContext.getAppContext().getSharedPreferences(Constants.SETTINGS, Context.MODE_PRIVATE);
        String url = sharedPreferences.getString(Constants.URL,"");
        return url;
    }
    public static String getCamera()
    {
        sharedPreferences = GlobalContext.getAppContext().getSharedPreferences(Constants.SETTINGS, Context.MODE_PRIVATE);
        String camera = sharedPreferences.getString(Constants.CAMERA,"");
        return camera;
    }

    public static String getUid()
    {
        sharedPreferences = GlobalContext.getAppContext().getSharedPreferences(Constants.SETTINGS, Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString(Constants.USERID,"");
        return uid;
    }
    public static String getBaseUrl()  {
        sharedPreferences = GlobalContext.getAppContext().getSharedPreferences(Constants.SETTINGS, Context.MODE_PRIVATE);
        String url2 = sharedPreferences.getString(Constants.URL,"");
        String url=null;
        String path = null;
        for(int i =0; i < url2.length();i++)
        {
            int j = i+4;
            if(j < url2.length())
            {
                if(url2.charAt(i)=='a'&&url2.charAt(i+1)=='p'&&url2.charAt(i+2)=='i')
                {
                    url = url2.substring(0,j);
                    path = url2.substring(j,url2.length());
                    break;
                }
            }
        }

        return url;
    }
    public static String getPathUrl() {
        sharedPreferences = GlobalContext.getAppContext().getSharedPreferences(Constants.SETTINGS, Context.MODE_PRIVATE);
        String url2 = sharedPreferences.getString(Constants.URL,"");
        String url=null;
        String path = null;
        for(int i =0; i < url2.length();i++)
        {
            int j = i+4;
            if(j < url2.length())
            {
                if(url2.charAt(i)=='a'&&url2.charAt(i+1)=='p'&&url2.charAt(i+2)=='i')
                {
                    url = url2.substring(0,j);
                    path = url2.substring(j,url2.length());
                    break;
                }
            }
        }

        return path;

    }


}
