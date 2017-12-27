package com.agarwalapps.licagent.utils;

import android.content.Context;

import com.agarwalapps.licagent.utils.constants.AppConstants;

/**
 * Created by sakshi on 19/12/17.
 */

public class SharedPref {

    public void saveUserLogIn(Context context, Boolean login) {
        context.getSharedPreferences(AppConstants.SHARED_PREFRENCES_USER_LOGGED_IN, Context.MODE_PRIVATE)
                .edit().putBoolean(AppConstants.SHARED_PREFRENCES_USER_LOGGED_IN, login).apply();
    }

    public Boolean fetchUserLogIn(Context context) {
        return context.getSharedPreferences(AppConstants.SHARED_PREFRENCES_USER_LOGGED_IN, Context.MODE_PRIVATE)
                .getBoolean(AppConstants.SHARED_PREFRENCES_USER_LOGGED_IN, false);
    }
}
