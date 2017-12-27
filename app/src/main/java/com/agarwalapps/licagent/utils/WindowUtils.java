package com.agarwalapps.licagent.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.agarwalapps.licagent.utils.constants.AppConstants;


/**
 * Created by sakshiagarwal on 04/04/17.
 */

public class WindowUtils {
    
    public static void openActivity(Activity activity, Class newactivityclass, boolean finishold, Bundle bundle){
        Intent intent = new Intent(activity, newactivityclass);
        if(bundle!=null){
            intent.putExtra(AppConstants.INTENT_KEY_BUNDLE, bundle);
        }
        activity.startActivity(intent);
        if(finishold){
            activity.finish();
        }
    }

    public static void showToast(Activity activity, String message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
