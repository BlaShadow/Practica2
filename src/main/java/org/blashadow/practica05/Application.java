package org.blashadow.practica05;

import com.activeandroid.ActiveAndroid;

/**
 * Created by blashadow on 3/23/15.
 */
public class Application extends com.activeandroid.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
