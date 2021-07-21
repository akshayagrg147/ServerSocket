package com.meetsuccess.socketcalling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.webkit.JavascriptInterface;

public class MainActivity3 extends AppCompatActivity {

    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        webview = (WebView)findViewById(R.id.webview);
        webView();
    }

    //Metodo llamar el webview
    private void webView(){
        //Habilitar JavaScript (Videos youtube)
       // webview.getSettings().setJavaScriptEnabled(true);

        //Handling Page Navigation
      //  webview.setWebViewClient(new MyWebViewClient());
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new JavascriptInterface(), "Android");

        //Load a URL on WebView
        String filePath = "file:android_asset/call.html";
        webview.loadUrl(filePath);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(webview, url);
                Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();

                callJavascriptFunction("javascript:init()");


            }
        });
       // webview.loadUrl("https://play.google.com/store/apps/details?id=com.Aanisha.mylotteryprize");
    }

    private void callJavascriptFunction(String functionString) {
     //   webview.post { webview.evaluateJavascript(functionString, null) }
        webview.evaluateJavascript(functionString, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                Toast.makeText(MainActivity3.this, "called"+s, Toast.LENGTH_SHORT).show();// {foo:"bar"}
            }
        });
    }

    // Metodo Navigating web page history
    @Override public void onBackPressed() {
        if(webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    // Subclase WebViewClient() para Handling Page Navigation
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("https://play.google.com/store/apps/details?id=com.Aanisha.mylotteryprize")) { //Force to open the url in WEBVIEW
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
    class JavascriptInterface {

        @android.webkit.JavascriptInterface
        public void onPeerConnected() {

            Toast.makeText(MainActivity3.this, "connected", Toast.LENGTH_SHORT).show();
        }

    }

}