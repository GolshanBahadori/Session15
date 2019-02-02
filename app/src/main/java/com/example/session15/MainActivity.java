package com.example.session15;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_TEST = 10001;
    //Instance variable
    // میساریم تا توی بقیه متدها دسترسی داشته باشیم بهش
    private TextView noInternetConnectionTv;
    //
    private ConnectivityBroadcastReceiver connectivityBroadcastReceiver;
    //
    private LocalBroadcastReceiverTest localBroadcastReceiverTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //قدم ششم
        //ابتدا TextView ای که مربوط به کانکشن اینترنت هست و میگیریم
        noInternetConnectionTv = findViewById(R.id.tv_main_noInternetConnection);
        //قدم هشتم
        //در روش subscribe داخل فایل Manifest حتی اگه اپلیکشن باز نباشه event ها دریافت نمیشند
        //اما تو روش کد فقط زمانیBroadcast  سابسکرایب subscribe میکنه که کامپوننتی که داخلش هستیم زنده باشه
        //اکتیویتی کی زنده هست وقتی متد onCreate ش call شده باشه چه زمانی از بین رفته زمانی که متد onDestroy ش call شده باشه
        //پس داخل متد onCreate اکتیویتی متد registerReceiver رو تعریف میکنیم
        // این متد بصورت پیش فرض دو پارامتر ورودی دارد
        // یکی BroadcastReceiver ی که میخوایم روش رجیستر کنیم میگیریم یکی intent-filter ی که میخوایم بهش گوش بدیم
        //
        connectivityBroadcastReceiver = new ConnectivityBroadcastReceiver();
        registerReceiver(connectivityBroadcastReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

        //
        localBroadcastReceiverTest = new LocalBroadcastReceiverTest();
        LocalBroadcastManager.getInstance(this).registerReceiver(localBroadcastReceiverTest, new IntentFilter("com.example.session15.TEST"));

        Button startActivityForResultBtn = findViewById(R.id.btn_main_startActivityForResultTest);
        startActivityForResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivityForResult(intent, REQUEST_CODE_TEST);
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TEST) {
            if (resultCode == RESULT_OK) {
                String firstName = data.getStringExtra(ResultActivity.EXTERA_KEY_FIRSTNAME);
                String lastName = data.getStringExtra(ResultActivity.EXTERA_KEY_LASTNAME);
                Toast.makeText(this, firstName + " " + lastName, Toast.LENGTH_LONG).show();
            }
        }
    }

    //قدم هفتم
    // یه inner کلاس میسازیم که کلاس BroadcastReceiver رو extend میکنه
    public class ConnectivityBroadcastReceiver extends BroadcastReceiver {

        // و متد onReceiver ش رو ایمپلمنت میکنیم
        @Override
        public void onReceive(Context context, Intent intent) {

            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                noInternetConnectionTv.setVisibility(View.GONE);
            } else noInternetConnectionTv.setVisibility(View.VISIBLE);
        }
    }

    //
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(connectivityBroadcastReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(localBroadcastReceiverTest);
    }

    private static final String TAG = "MainActivity";

    public class LocalBroadcastReceiverTest extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i(TAG, "onReceive: ");
            String message = intent.getStringExtra("message");
            Log.i(TAG, "onReceive: ");
        }
    }
}
