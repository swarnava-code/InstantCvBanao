package sclab.doc.instantcvbanao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView browser=findViewById(R.id.browser);

        browser.loadUrl("https://youtu.be/pAoNiMK1iRc");
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setWebViewClient(new WebViewClient());

    }

    @Override
    public void onBackPressed() {
        Intent j = new Intent(this,MainActivity.class);
        startActivity(j);
        finish();
    }

    public void onClickNav(View view) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    public void refresh(View view){
        Intent j = new Intent(this,WebActivity.class);
        startActivity(j);
    }
}
