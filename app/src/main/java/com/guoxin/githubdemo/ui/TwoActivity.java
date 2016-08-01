package com.guoxin.githubdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.guoxin.githubdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TwoActivity extends AppCompatActivity {

    @Bind(R.id.webview)
    WebView contentview;
    @Bind(R.id.button)
    Button bt1;
    @Bind(R.id.button2)
    Button bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_two);
        ButterKnife.bind(this);
        contentview.getSettings().setJavaScriptEnabled(true);
        contentview.loadUrl("file:///android_asset/web.html");
        contentview.addJavascriptInterface(this, "android");
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                contentview.loadUrl("javascript:javacalljs()");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentview.loadUrl("javascript:javacalljswith(' asdfsdfsdf')");
            }
        });

    }
    @JavascriptInterface
    public void start() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TwoActivity.this, "show", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @JavascriptInterface
    public void startFunction(String text){
        Toast.makeText(TwoActivity.this,text, Toast.LENGTH_SHORT).show();
    }

}
