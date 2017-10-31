package com.morladim.tools;

import android.app.Application;

import com.facebook.stetho.Stetho;

import timber.log.Timber;

/**
 * 配置了各tools类的Application
 * <br>创建时间：2017/10/31.
 *
 * @author morladim
 */
@SuppressWarnings("unused")
public abstract class BaseApplication extends Application {

    private static BaseApplication context;

    public static BaseApplication getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        SharedPreferencesUtils.init(this);
        ImageLoader.init(this);
        NetworkUtils.init(this);

        if (BuildConfig.DEBUG) {
            //chrome://inspect 会在release时依然暴露数据库
            Stetho.initializeWithDefaults(this);
            Timber.plant(new MorladimDebugTree());
        }
    }
}
