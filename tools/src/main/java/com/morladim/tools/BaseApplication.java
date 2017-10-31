package com.morladim.tools;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * 配置了各tools类的Application
 * <br>创建时间：2017/10/31.
 *
 * @author morladim
 */
@SuppressWarnings("unused")
public class BaseApplication extends Application {

    private static BaseApplication context;

    public static BaseApplication getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        context = this;

        SharedPreferencesUtils.init(this);
        ImageLoader.init(this);
        NetworkUtils.init(this);
        //chrome://inspect
        Stetho.initializeWithDefaults(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new MorladimDebugTree());
        }
    }
}
