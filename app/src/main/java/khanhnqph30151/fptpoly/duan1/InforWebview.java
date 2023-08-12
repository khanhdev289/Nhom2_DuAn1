package khanhnqph30151.fptpoly.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InforWebview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_webview);


        WebView myWebview = findViewById(R.id.webview);
        myWebview.loadUrl("https://www.facebook.com/jirai.bee/");
        myWebview.setWebViewClient(new WebViewClient());
    }
}