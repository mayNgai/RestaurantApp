package com.may.stream.restaurant.until;

import android.app.Activity;

/**
 * Created by may on 1/5/2018.
 */

public class ApplicationController {
    private static ApplicationController instance = null;
    protected static Activity _activity;

    public static ApplicationController getInstance() {
        if (null == instance) {
            instance = new ApplicationController();
        }
        return instance;
    }

    public static Activity getAppActivity() {
        return _activity;

    }

    public static void setAppActivity(Activity a) {
        _activity = a;

    }
}
