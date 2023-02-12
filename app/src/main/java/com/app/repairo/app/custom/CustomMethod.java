package com.app.repairo.app.custom;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.app.repairo.app.model.login.LoginResponse;
import com.app.repairo.app.utils.Preferences;

import java.io.IOException;

import static com.app.repairo.app.utils.ConstantValues.KEY_LOGIN_DATA;

public class CustomMethod {
    static  Context context;
    public CustomMethod(Context context) {
        this.context=context;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
