package com.agarwalapps.licagent.presenter;

import android.app.Activity;

import com.agarwalapps.licagent.utils.SharedPref;
import com.agarwalapps.licagent.utils.WindowUtils;
import com.agarwalapps.licagent.view.HomeActivity;
import com.agarwalapps.licagent.view.LoginActivity;

/**
 * Created by sakshi on 22/12/17.
 */

public class MainActivityPresenter {

    public void goToNextActivity(Activity activity) {
        if(new SharedPref().fetchUserLogIn(activity))
            WindowUtils.openActivity(activity, HomeActivity.class, true, null);
        else
            WindowUtils.openActivity(activity, LoginActivity.class, true, null);
    }
}
