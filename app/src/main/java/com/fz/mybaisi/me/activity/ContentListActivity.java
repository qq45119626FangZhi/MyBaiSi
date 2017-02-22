package com.fz.mybaisi.me.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.fz.mybaisi.R;
import com.fz.mybaisi.utils.MyConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 内容贡献者H5页面
 */
public class ContentListActivity extends AppCompatActivity {


    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_list);
        ButterKnife.bind(this);

        setWebView();

    }

    private void setWebView() {
        //设置参数
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//设置直接js
        settings.setUseWideViewPort(true);//双击变大变小

        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);

        //设置监听
        webView.setWebViewClient(new WebViewClient() {
            //高版本
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            //加载完成
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

        //请求网页数据的路径
//        webView.loadUrl("http://mp.weixin.qq.com/s/Cf3DrW2lnlb-w4wYaxOEZg");
        webView.loadUrl(MyConstants.ME_CONTENT_LIST);
//        webView.loadUrl("http://m.budejie.com/user/credit.html");
    }


}
