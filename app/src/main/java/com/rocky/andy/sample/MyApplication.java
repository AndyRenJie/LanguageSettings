package com.rocky.andy.sample;

import android.app.Application;

import com.rocky.andy.library.LanguageTools;

/**
 * Created by Andy.Ren on 2018/6/21.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        LanguageTools.getInstance().init(this);
    }
}
