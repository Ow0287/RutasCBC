package com.misena.oscar.rutascbc;

import com.activeandroid.ActiveAndroid;

/**
 * Created by oscar on 15/08/17.
 */

public class Application extends com.activeandroid.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
