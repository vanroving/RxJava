package com.guoxin.githubdemo.fragment;


import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;
import com.guoxin.githubdemo.R;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TwoFragment extends Fragment {
    @Bind(R.id.webview)WebView contentview=null;
    @Bind(R.id.button)
    Button bt1;
    @Bind(R.id.button2)
    Button bt2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=View.inflate(getContext(), R.layout.fragment_two,null);
        ButterKnife.bind(this, view);
        contentview.getSettings().setJavaScriptEnabled(true);
        contentview.loadUrl("file:///android_asset/web.html");
        contentview.addJavascriptInterface(getContext(), "android");
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

        return view;
    }
    @JavascriptInterface
    public void start(){
                Toast.makeText(getContext(), "show", Toast.LENGTH_SHORT).show();

    }
    @JavascriptInterface
    public void startFunction(String text){
        Toast.makeText(getContext(),text,0).show();
    }

}
