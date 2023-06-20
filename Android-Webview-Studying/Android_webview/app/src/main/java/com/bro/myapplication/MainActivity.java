package com.bro.myapplication;

import android.app.Activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;


public class MainActivity extends Activity {

    private WebView mWebView;
    private Context mContext;


    private class AndroidBridge {
        @JavascriptInterface
        public void CallAndroid() {
            Toast.makeText(getApplicationContext(), "웹에서 클릭했어요", Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void bluetoothStateCheck() {
            BluetoothAdapter adapter;
            try {
                adapter = BluetoothAdapter.getDefaultAdapter();
                //TODO 블루투스 기능을 사용할 수 있는 모바일 기기 인지 확인 실시
                if (adapter != null) {

                    //TODO 블루투스 기능이 활성화 상태인지 확인 실시
                    if (ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        if (!adapter.isEnabled()) {
                            adapter.enable(); //TODO 블루투스 기능 비활성화 실시
                        }
                        else{
                            adapter.disable(); //TODO 블루투스 기능 활성화 실시
                        }

                        return;
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "블루투스 미지원 기기", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mContext = this;
        mWebView = (WebView) findViewById(R.id.webview);
        WebSettings ws = mWebView.getSettings();

        ws.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new AndroidBridge(), "BRIDGE"); // 자바스크립트 인터페이스

        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        String url = "http://192.168.219.172:8080/";
        mWebView.loadUrl(url);

    }
}