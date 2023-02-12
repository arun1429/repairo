package com.app.repairo.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.app.repairo.app.model.login.LoginResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class Preferences {
    public static SharedPreferences.Editor editor;
    public static SharedPreferences preferences;


    public static void writeObject(Context context, String key, LoginResponse object) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putString(key, json).commit();
    }

    public static LoginResponse readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString(key, "");
        LoginResponse object = gson.fromJson(json, LoginResponse.class);
        return object;
    }
    public static void saveString(Context context, String key, String value){
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putString(key,value).commit();
    }

    public static String getString(Context context, String key){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        String restoredText = preferences.getString(key, "");
        if (restoredText != null) {
            restoredText = preferences.getString(key, "");
            return restoredText;

        }
        else
            return "";
    }


    public static void saveBoolean(Context context, String key, boolean value) {
        editor = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key) {
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    public static void clerPref(Context context){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        preferences.edit().clear().commit();

    }
    public static void saveInt(Context context, String key, int value){
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putInt(key,value).commit();
    }
    public static void setItemInCart(Context context, ArrayList<HashMap<String,String>> list, String key){
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.commit();
    }
    public static ArrayList<HashMap<String,String>> getItemInCart(Context context, String key){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString(key, null);
        Type type = new TypeToken<ArrayList<HashMap<String,String>>>() {}.getType();
        return gson.fromJson(json, type);
    }
    public static int getInt(Context context, String key){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return preferences.getInt(key,0);
    }


    public static String getStringValue(Context context, String key)
    {
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return preferences.getString(key,"");
    }
    // page info for back click/////

    public static void saveWalletPage(Context context, String key, String value)
    {
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putString(key,value).commit();
    }

    public static String getWalletPage(Context context, String key)
    {
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return preferences.getString(key,"");
    }

    // wallet amount .....

    public static void setNewWalletAmount(Context context, String key, String value){
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putString(key,value).commit();
    }
    public static void setAddedToCart(Context context, String key, boolean value){
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putBoolean(key,value).commit();
    }


    public static boolean getAddedToCart(Context context, String key){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return preferences.getBoolean(key,false);
    }
    public static String getNewWalletAmount(Context context, String key){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return preferences.getString(key,"");
    }


     /*  public static void saveArrayList(Context context, ArrayList<ProductItems> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }


    public static ArrayList<ProductItems> getArrayList(Context context, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<ProductItems>>() {}.getType();
        return gson.fromJson(json, type);
    }
*/

    public static void clearList(Context context, String key) {
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.remove(key).commit();
    }


    public static void saveStatus(Context context, String key, boolean value){
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putBoolean(key,value).commit();
    }

    public static boolean getStatus(Context context, String key){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return preferences.getBoolean(key,true);
    }
    public static void saveOutOfStock(Context context, String key, boolean value){
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putBoolean(key,value).commit();

    }
    public static boolean getPromoOn(Context context, String key){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return preferences.getBoolean(key,true);
    }
    public static void setPromoOn(Context context, String key, boolean value) {
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putBoolean(key,value).commit();
    }

    // get array list data................

    public static void saveProductIdArrayList(Context context, ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public static ArrayList<String> getProductIdArrayList(Context context, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // save and get notifications.......

    public static void saveNotiCount(Context context, String key, int value){
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putInt(key,value).commit();
    }
    public static int getNotiCount(Context context, String key){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return preferences.getInt(key,0);
    }

    // discount amount...............
    public static void saveDiscount(Context context, String key, String value){
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putString(key,value).commit();
    }

    public static String getDiscount(Context context, String key){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return preferences.getString(key,"");
    }

    // discountPercent amount...............
    public static void saveDiscountPercent(Context context, String key, String value){
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putString(key,value).commit();
    }



    public static String getDiscountPercent(Context context, String key){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return preferences.getString(key,"");
    }

    // Auto discountPercent amount...............
    public static void saveAutoDiscount(Context context, String key, String value){
        editor=context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).edit();
        editor.putString(key,value).commit();
    }

    public static String getAutoDiscount(Context context, String key){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return preferences.getString(key,"");
    }


    public static void saveJsonArray(Context context, String jsonArray, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        // JsonArray newJsonArray = new JsonArray();
        //   newJsonArray.add(jsonArray);
      /*  Gson gson = new Gson();
        String json = gson.toJson(jsonArray);*/
        editor.putString(key, jsonArray);
        editor.apply();     // This line is IMPORTANT !!!

    }

    public static String getJsonData(Context context, String key)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        // JsonArray jsonElements = new JsonArray();
      /*  Type type = new TypeToken<JsonArray>() {}.getType();
        return gson.fromJson(json, type);*/
        return json;
       /* SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<JsonArray>() {}.getType();
        return gson.fromJson(json, type);*/
    }



    public static void saveLastOrder(Context context, String lastOrder, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, lastOrder);
        editor.apply();     // This line is IMPORTANT !!!

    }
    public static String getLastOrder(Context context, String key){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return preferences.getString(key,"");
    }

    public static void clearPerf(Context context){
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        preferences.edit().clear().commit();
    }
}
