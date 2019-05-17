package com.tw.baselibs.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.blankj.utilcode.util.Utils;
import com.tw.baselibs.cache.CacheManager;

public class BaseApp extends Application {
    private static BaseApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        CacheManager.init(this);
        Utils.init(this);
        //注册监听每个activity的生命周期,便于堆栈式管理
        registerActivityLifecycleCallbacks(mCallbacks);
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static BaseApp getInstance() {
        return mInstance;
    }


    private ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            AppManager.getInstance().addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            AppManager.getInstance().removeActivity(activity);
        }
    };
}
