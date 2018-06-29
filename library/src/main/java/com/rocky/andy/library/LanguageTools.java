package com.rocky.andy.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import java.util.Locale;
import static com.rocky.andy.library.Language.LANGUAGE_CN;
import static com.rocky.andy.library.Language.LANGUAGE_JP;
import static com.rocky.andy.library.Language.LANGUAGE_KR;
import static com.rocky.andy.library.Language.LANGUAGE_TW;
import static com.rocky.andy.library.Language.LANGUAGE_US;

/**
 * Created by Andy.Ren on 2018/6/21.
 * 语言工具
 */
public class LanguageTools {

    private static LanguageTools mInstance;
    private Context mContext;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mSpEditor;

    public static LanguageTools getInstance(){
        if(mInstance==null){
            synchronized (LanguageTools.class){
                if(mInstance == null){
                    mInstance = new LanguageTools();
                }
            }
        }
        return mInstance;
    }

    /**
     * 在Application中初始化,获取保存的语言更新配置,默认是中文(简体)
     * @param context
     */
    public void init(Context context){
        this.mContext = context;
        this.mPreferences = context.getSharedPreferences("language", Context.MODE_PRIVATE);
        this.mSpEditor = mPreferences.edit();

        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(switchLocale());
        }else{
            configuration.locale = switchLocale();
        }
        resources.updateConfiguration(configuration,displayMetrics);
    }

    private void saveLanguage(int language) {
        mSpEditor.putInt("language", language).commit();
    }

    private int getSaveLanguage() {
        return mPreferences.getInt("language", LANGUAGE_CN.value);
    }

    /**
     * 根据保存的语言选择Locale
     * @return
     */
    private Locale switchLocale(){
        Locale locale = null;
        int language = getSaveLanguage();
        if(language == LANGUAGE_CN.value){
            locale = Locale.SIMPLIFIED_CHINESE;
        }else if(language == LANGUAGE_TW.value){
            locale = Locale.TRADITIONAL_CHINESE;
        }else if(language == LANGUAGE_US.value){
            locale = Locale.US;
        }else if(language == LANGUAGE_KR.value){
            locale = Locale.KOREA;
        }else if(language == LANGUAGE_JP.value){
            locale = Locale.JAPAN;
        }
        return locale;
    }

    /**
     * 切换语言
     * @param language
     * @param activity
     */
    public void changeAppLanguage(final int language, final Activity activity){
        saveLanguage(language);
        Resources resources = mContext.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLocale(switchLocale());
        }else{
            configuration.locale = switchLocale();
        }
        resources.updateConfiguration(configuration, displayMetrics);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, activity.getClass());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                activity.overridePendingTransition(R.anim.animo_alph_open, R.anim.animo_alph_close);
            }
        }, 500);
    }

    /**
     * 判断是否与系统语言环境一样
     * @return
     */
    public boolean isAppLanguageEqualsSystem() {
        Configuration configuration = mContext.getResources().getConfiguration();
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = configuration.getLocales().get(0);
        } else {
            locale = configuration.locale;
        }
        return locale.equals(switchLocale());
    }
}
