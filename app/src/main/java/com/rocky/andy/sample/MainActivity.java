package com.rocky.andy.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.rocky.andy.library.LanguageTools;

import static com.rocky.andy.library.Language.LANGUAGE_CN;
import static com.rocky.andy.library.Language.LANGUAGE_JP;
import static com.rocky.andy.library.Language.LANGUAGE_KR;
import static com.rocky.andy.library.Language.LANGUAGE_TW;
import static com.rocky.andy.library.Language.LANGUAGE_US;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.cn_btn).setOnClickListener(this);
        findViewById(R.id.tw_btn).setOnClickListener(this);
        findViewById(R.id.us_btn).setOnClickListener(this);
        findViewById(R.id.kr_btn).setOnClickListener(this);
        findViewById(R.id.jp_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int language;
        switch (v.getId()){
            case R.id.cn_btn:
                language = LANGUAGE_CN.value;
                break;
            case R.id.tw_btn:
                language = LANGUAGE_TW.value;
                break;
            case R.id.us_btn:
                language = LANGUAGE_US.value;
                break;
            case R.id.kr_btn:
                language = LANGUAGE_KR.value;
                break;
            case R.id.jp_btn:
                language = LANGUAGE_JP.value;
                break;
            default:
                language = LANGUAGE_CN.value;
        }
        LanguageTools.getInstance().changeAppLanguage(language,this);
    }
}
