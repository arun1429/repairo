package com.app.repairo.app.activity.logreg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.app.repairo.app.R;
import com.app.repairo.app.activity.dashboard.HomeActivity;
import com.app.repairo.app.apis.ApiClient;
import com.app.repairo.app.apis.ApiInterface;
import com.app.repairo.app.custom.OkDialog;
import com.app.repairo.app.custom.ProgressBarHandler;
import com.app.repairo.app.model.login.LoginResponse;
import com.app.repairo.app.model.verifymobile.OtpVerifyPost;
import com.app.repairo.app.model.verifymobile.OtpVerifyRes;
import com.app.repairo.app.utils.OtpEditText;
import com.app.repairo.app.utils.Preferences;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class VerifyOtpActivity extends AppCompatActivity {
    private LinearLayout tvResend,tvChangeNumber;
    private LinearLayout button;
    private String token =  "";
    String  mobileNo="";
    OtpEditText et_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        initViews();
        getFirebaseToken();
        mobileNo = getIntent().getStringExtra("phoneNumber");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobileNo.trim().equals("")) {
                    OkDialog.INSTANCE.show(VerifyOtpActivity.this, "Mobile Number is invalid.");
                    return;
                }else  if (et_otp.getText().toString().trim().equals("")) {
                    OkDialog.INSTANCE.show(VerifyOtpActivity.this, "Please enter your verification code.");
                    return;
                }
                verifyOtpApis();
            }
        });
        tvResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendVerifyOtpApis();
            }
        });
        tvChangeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void verifyOtpApis() {
        final OtpVerifyPost otpVerifyPost = new OtpVerifyPost();
        otpVerifyPost.setMobile(mobileNo);
        otpVerifyPost.setOtp(et_otp.getText().toString());
        final ProgressBarHandler progressBarHandler = new ProgressBarHandler(VerifyOtpActivity.this);
        progressBarHandler.show();
        ApiInterface apiInterface= ApiClient.getRetrofitClientForAuth(VerifyOtpActivity.this).create(ApiInterface.class);
        Observable<OtpVerifyRes> observable=apiInterface.verifyOtpApisCall(otpVerifyPost);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OtpVerifyRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OtpVerifyRes otpVerifyRes) {
                        progressBarHandler.hide();
                        if (otpVerifyRes.getSuccess() == 1){
                            Toast.makeText(VerifyOtpActivity.this, otpVerifyRes.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(VerifyOtpActivity.this, HomeActivity.class);
                            Preferences.saveBoolean(VerifyOtpActivity.this, "isLogin", true);
                            Preferences.saveString(VerifyOtpActivity.this, "id", otpVerifyRes.getData().getId());
                            Preferences.saveString(VerifyOtpActivity.this, "mobile", otpVerifyRes.getData().getMobile());
                            Preferences.saveString(VerifyOtpActivity.this, "email", otpVerifyRes.getData().getEmail());
                            Preferences.saveString(VerifyOtpActivity.this, "created_ad", otpVerifyRes.getData().getCreated_at());
                            Preferences.saveString(VerifyOtpActivity.this, "name", otpVerifyRes.getData().getName());
                            startActivity(intent);
                            finishAffinity();
                        }
                        else {
                            Log.e("TAG", "otpVerifyRes.getMessage() : "+otpVerifyRes.getMessage());
                            Toast.makeText(VerifyOtpActivity.this, otpVerifyRes.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBarHandler.hide();
                        Toast.makeText(VerifyOtpActivity.this, "Invalid OTP. Please enter correct OTP.", Toast.LENGTH_SHORT).show();
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        progressBarHandler.hide();
                        System.out.println("");
                    }
                });
    }
    private void resendVerifyOtpApis() {
        final RequestBody rPhoneNumber = RequestBody.create(MediaType.parse("text/plain"),mobileNo );
        final ProgressBarHandler progressBarHandler = new ProgressBarHandler(VerifyOtpActivity.this);
        progressBarHandler.show();
        ApiInterface apiInterface= ApiClient.getRetrofitClientForAuth(VerifyOtpActivity.this).create(ApiInterface.class);
        Observable<LoginResponse> observable=apiInterface.loginApisCall(rPhoneNumber);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponse mobResponse) {
                        progressBarHandler.hide();
                        if (mobResponse.getSuccess() ==1){
                            et_otp.setText("");
                            Toast.makeText(VerifyOtpActivity.this, mobResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(VerifyOtpActivity.this, mobResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
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
        tvChangeNumber = findViewById(R.id.tvChangeNumber);
        et_otp = findViewById(R.id.et_otp);
        tvResend = findViewById(R.id.tvResend);
        button = findViewById(R.id.button);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(VerifyOtpActivity.this, LoginActivity.class);
        startActivity(intent);
        finishAffinity();
    }
    private void getFirebaseToken() {

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful())
                    return;
                token=task.getResult().getToken();
            }
        });
    }
}