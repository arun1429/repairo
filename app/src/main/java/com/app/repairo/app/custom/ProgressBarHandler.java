package com.app.repairo.app.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.app.repairo.app.R;

public class ProgressBarHandler {


    AlertDialog dialog;
    public ProgressBarHandler(Context context) {

        dialog = new AlertDialog.Builder(context).create();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.progress_bar_layout, null);
        dialog.setView(layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
    }

    public void show() {
        dialog.show();
    }

    public void hide() {
        try {
            dialog.dismiss();
        }
        catch (Exception e){

        }

    }


  /*  AlertDialog dialog;
    FragmentManager manager;
    Context context;
    public ProgressBarHandler(Context context) {
        this.context = context;
        manager = ((AppCompatActivity) context).getSupportFragmentManager();
        dialog = new AlertDialog.Builder(context).create();
        View layout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.progress_bar_layout, null);
      *//*  SpinKitView spinKitView=layout.findViewById(R.id.spin_kit);
//        ProgressBar progressBar = (ProgressBar)layout.findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        spinKitView.setIndeterminateDrawable(doubleBounce);
*//*
//        FadingCircle fadingCircle = new FadingCircle();
//        spinKitView.setIndeterminateDrawable(fadingCircle);

//        Circle circle = new Circle();
//        spinKitView.setIndeterminateDrawable(circle);

//        materialProgressBar = new CatLoadingView(context);
        this.dialog.requestWindowFeature(1);
        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        lWindowParams.copyFrom(this.dialog.getWindow().getAttributes());
        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT; // this is where the magic happens
        lWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        this.dialog.setView(layout);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.getWindow().setAttributes(lWindowParams);
        this.dialog.setCancelable(false);




    }
    public void show() {
        dialog.show();
    }

    public void hide() {
//    materialProgressBar.getUserVisibleHint()
//        mProgressBar.setVisibility(View.INVISIBLE);
//        manager = ((AppCompatActivity) context).getSupportFragmentManager();
        if ( ((AppCompatActivity) context).getSupportFragmentManager()!=null && dialog.isShowing()) {
//            materialProgressBar.dismiss();
            dialog.dismiss();
        }
//        progressBar.hide();
//        materialProgressBar.setVisibility(View.GONE);
    }
    public static void showAlert(Context context, String head, String msg,
                                 String postiveBtnName,
                                 DialogInterface.OnClickListener positiveBtnListner,
                                 String negativeBtnName,
                                 DialogInterface.OnClickListener negativeBtnListner,
                                 boolean... cancelable) {
        android.app.AlertDialog d;
        boolean canBeClosed = (cancelable == null || cancelable.length == 0 || cancelable[0]);

        if (negativeBtnListner == null) {
            d = new android.app.AlertDialog.Builder(context).setMessage(msg)
                    .setTitle(head)
                    .setPositiveButton(postiveBtnName, positiveBtnListner)
                    .setCancelable(canBeClosed)
                    .create();
        } else {
            d = new android.app.AlertDialog.Builder(context).setMessage(msg)
                    .setTitle(head)
                    .setPositiveButton(postiveBtnName, positiveBtnListner)
                    .setNegativeButton(negativeBtnName, negativeBtnListner)
                    .setCancelable(canBeClosed)
                    .create();
        }
        d.show();
    }*/
}