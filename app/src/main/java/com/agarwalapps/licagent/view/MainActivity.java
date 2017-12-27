package com.agarwalapps.licagent.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.agarwalapps.licagent.R;
import com.agarwalapps.licagent.presenter.MainActivityPresenter;
import com.agarwalapps.licagent.utils.SharedPref;

public class MainActivity extends AppCompatActivity{

    MainActivityPresenter mainActivityPresenter = new MainActivityPresenter();

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityPresenter.goToNextActivity(this);
    }
}

