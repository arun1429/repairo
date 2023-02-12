package com.app.repairo.app.activity.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.repairo.app.R;
import com.app.repairo.app.apis.ApiClient;
import com.app.repairo.app.apis.ApiInterface;
import com.app.repairo.app.custom.OkDialog;
import com.app.repairo.app.custom.ProgressBarHandler;
import com.app.repairo.app.model.login.LoginResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginActivity extends AppCompatActivity {
    private EditText editMobile;
    private ImageView imgOk;
    private LinearLayout button;
    private String token =  "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        editMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
              if(s.toString().length() == 10){
                  imgOk.setVisibility(View.VISIBLE);
              }else{
                  imgOk.setVisibility(View.GONE);
              }
                // TODO Auto-generated method stub
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = editMobile.getText().toString().toLowerCase();
                //startActivity(new Intent(LoginActivity.this, VerifyOtpActivity.class).putExtra("phoneNumber","9889988989"));
                if (mobile.trim().equals("")) {
                    OkDialog.INSTANCE.show(LoginActivity.this, "Please enter mobile number");
                    return;
                }
                if (mobile.length()<10) {
                    OkDialog.INSTANCE.show(LoginActivity.this, "Please enter valid number");
                    return;
                }
                loginApisCall();
            }
        });


    }

    private void loginApisCall() {
        System.out.println(token);
        final RequestBody rPhoneNumber = RequestBody.create(MediaType.parse("text/plain"),editMobile.getText().toString().trim() );
        final ProgressBarHandler progressBarHandler = new ProgressBarHandler(LoginActivity.this);
        progressBarHandler.show();
        ApiInterface apiInterface = ApiClient.getRetrofitClientForAuth(LoginActivity.this).create(ApiInterface.class);
        Observable<LoginResponse> observable = apiInterface.loginApisCall(rPhoneNumber);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        Log.e("TAG", "loginResponse : " + loginResponse);
                         progressBarHandler.hide();
                        if (loginResponse.getSuccess() ==1) {
                            startActivity(new Intent(LoginActivity.this, VerifyOtpActivity.class).putExtra("phoneNumber",editMobile.getText().toString()));


                        } else {
                            Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
//                        progressBar.setVisibility(View.GONE);
                        progressBarHandler.hide();
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                          progressBarHandler.hide();
                        System.out.println("");
                    }
                });
    }


    private void initViews() {
              editMobile = (EditText) findViewById(R.id.editMobile);
        imgOk = (ImageView) findViewById(R.id.imgOk);
       button = findViewById(R.id.button);
    }


}