package com.agarwalapps.licagent.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.agarwalapps.licagent.utils.SharedPref;
import com.agarwalapps.licagent.utils.WindowUtils;
import com.agarwalapps.licagent.view.HomeActivity;
import com.agarwalapps.licagent.view.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

import java.util.concurrent.Executor;

/**
 * Created by sakshi on 18/12/17.
 */

public class LoginActivityPresenter {

    public void signInWithPhoneAuthCredential(final Activity activity, PhoneAuthCredential credential, FirebaseAuth mAuth) {
        mAuth.signInWithCredential(credential).addOnCompleteListener((Executor) activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();
                }
                else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                        showToast(activity, "invalid number");
            }
        });
    }

    public void saveLoginToSharedPref(Activity activity){
        new SharedPref().saveUserLogIn(activity, true);
    }

    public void goToHomeActivity(Activity activity){
        WindowUtils.openActivity(activity, HomeActivity.class, true, null);
    }

    public void showToast(Activity activity, String message) {
        WindowUtils.showToast(activity, message);
    }
}
