package com.agarwalapps.licagent.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.agarwalapps.licagent.R;
import com.agarwalapps.licagent.model.firebase.WriteRequest;
import com.agarwalapps.licagent.model.pojo.User;
import com.agarwalapps.licagent.presenter.LoginActivityPresenter;
import com.agarwalapps.licagent.utils.SharedPref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private boolean sendotp = false;
    private EditText mobileNo, otp;
    private Button loginButton;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private LoginActivityPresenter loginActivityPresenter = new LoginActivityPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();
        callPhoneVerification();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:
                if(!sendotp){
                    setSendotp();
                    startPhoneNumberVerification(mobileNo.getText().toString());
                }
                else
                    verifyPhoneNumberWithCode(mVerificationId, otp.getText().toString());
                break;
            case R.id.terms_and_condition_text:
                break;
        }
    }

    private void initializeViews(){
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        ((TextView) findViewById(R.id.terms_and_condition_text)).setOnClickListener(this);
        mobileNo = (EditText) findViewById(R.id.mobile_edittext);
        otp = (EditText) findViewById(R.id.otp_edittxt);
    }

    private void setSendotp(){
        loginButton.setText(getString(R.string.verify));
        otp.setVisibility(View.VISIBLE);
        mobileNo.setVisibility(View.GONE);

    }

    private void callPhoneVerification(){
        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                loginActivityPresenter.signInWithPhoneAuthCredential(LoginActivity.this, credential, mAuth);
                loginActivityPresenter.saveLoginToSharedPref(LoginActivity.this);
                loginActivityPresenter.goToHomeActivity(LoginActivity.this);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException)
                    loginActivityPresenter.showToast(LoginActivity.this, "invalid number");
                else if (e instanceof FirebaseTooManyRequestsException)
                    loginActivityPresenter.showToast(LoginActivity.this, "quota excedded");
                else
                    loginActivityPresenter.showToast(LoginActivity.this, "something went wrong");

            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
    }

    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,60, TimeUnit.SECONDS,this, mCallbacks);
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        loginActivityPresenter.signInWithPhoneAuthCredential(LoginActivity.this, credential, mAuth);
    }

    private void resendVerificationCode(String phoneNumber, PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,60,TimeUnit.SECONDS,this, mCallbacks, token);
    }

    private void signOut() {
        mAuth.signOut();
    }
}
