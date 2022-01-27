package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;


import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class HelpWebView extends AppCompatActivity {
    WebView navegador;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_help_web_view);

        navegador = (WebView) findViewById(R.id.help_wv);
        navegador.setWebViewClient(new WebViewClient());
        navegador.getSettings().setJavaScriptEnabled(true);
        navegador.getSettings().setBuiltInZoomControls(true);
        navegador.getSettings().setDisplayZoomControls(false);

        navegador.loadUrl("file:///android_asset/web/help.html");
    }
}