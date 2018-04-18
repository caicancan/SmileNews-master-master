package com.dandy.smilenews;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.dandy.smilenews.base.BaseActivity;
import com.dandy.smilenews.config.Config;
import com.squareup.picasso.Picasso;

/**
 * Created by Dandy on 2016/11/3.
 */

public class NewsDetailActivity extends BaseActivity {
    private Toolbar mToolBar;
    private ImageView mNewsImg;
    private WebView mNewsWeb;
    private String mImgUrl,mContentUrl;
    private Bundle bundle;
    private CollapsingToolbarLayout mToolBarLayout;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_detail);
        bundle=getIntent().getExtras();
        initViews();
        iniData();
    }

    private void initViews() {
        mToolBar=findView(R.id.toolbar);
        mNewsImg=findView(R.id.news_img);
        mNewsWeb=findView(R.id.news_web);
        mToolBarLayout=findView(R.id.toolbar_layout);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.e("smile","ee"+bundle.getInt(Config.KEY_TYPE));
        mToolBarLayout.setTitle(bundle.getString(Config.KEY_TYPE));

        mToolBarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));//设置扩展时title文字颜色
        mToolBarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
    }

    private void iniData() {
        mImgUrl=bundle.getString(Config.KEY_IMG_URL);
        mContentUrl=bundle.getString(Config.KEY_CONTENT_URL);
        Picasso.with(getApplicationContext()).load(mImgUrl).into(mNewsImg);

        WebSettings webSettings = mNewsWeb.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        mNewsWeb.loadUrl(mContentUrl);
    }

}
