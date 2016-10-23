package com.example.k13006kk.beaconroomrecognition;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.k13006kk.mylibrary.BeaconHolder;
import com.example.k13006kk.mylibrary.DBaccess;
import com.example.k13006kk.mylibrary.UserColumns;

public class MainActivity extends Activity {

    static BeaconHolder beaconinfo = BeaconHolder.getInstance();
    DBaccess dBaccess = new DBaccess();
    public String[] stringArray = {" "," "," "," "};
    public String[] backup = {" "," "," "," "};
    public String[] room = {" "," "," "," "," "," "};
    Handler _handler = new Handler();


    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //バックグラウンド処理のためのServiceの開始
        startService(new Intent(MainActivity.this, Background.class));

        _handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                beaconinfoView();

                _handler.postDelayed(this, 100);

            }
        }, 0);

        final ContentResolver resolver = getContentResolver();
        for (int i = 0; i < dBaccess.monitoring(resolver).length; i++) {
            room[i] = dBaccess.monitoring(resolver)[i];
        }

        tv5 = (TextView) findViewById(R.id.toumei);
        tv5.setText(room[1]);

        tv6 = (TextView) findViewById(R.id.heyamei);
        tv6.setText(room[2]);

        tv7 = (TextView) findViewById(R.id.heyaban);
        tv7.setText(room[3]);

        tv8 = (TextView) findViewById(R.id.nichiji);
        tv8.setText(room[4]);
    }

    ContentObserver mContentObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            // 変更された時の処理を書く
            //tv5 = (TextView) findViewById(R.id.state);
            //tv5.setText("入室　");
            //tv5 = (TextView) findViewById(R.id.state);
            // オレンジ色
            //tv5.setTextColor(0xffff8c00);
            //room = dBaccess.monitoring(resolver);
            final ContentResolver resolver = getContentResolver();
            for (int i = 0; i < dBaccess.monitoring(resolver).length; i++) {
                room[i] = dBaccess.monitoring(resolver)[i];
            }

            tv5 = (TextView) findViewById(R.id.toumei);
            tv5.setText(room[1]);

            tv6 = (TextView) findViewById(R.id.heyamei);
            tv6.setText(room[2]);

            tv7 = (TextView) findViewById(R.id.heyaban);
            tv7.setText(room[3]);

            tv8 = (TextView) findViewById(R.id.nichiji);
            tv8.setText(room[4]);
        }
    };

    public void beaconinfoView(){

        for (int i = 0; i < beaconinfo.getTestString().length; i++) {
            stringArray[i] = beaconinfo.getTestString()[i];
        }
        //stringArray = beaconinfo.getTestString();
        //Log.d("scan2",stringArray[0]+","+stringArray[1]+","+stringArray[2]+","+stringArray[3]);
        //Log.d("scan2",backup[0]+","+backup[1]+","+backup[2]+","+backup[3]);

        if(!"A".equals(stringArray[0])) {
            tv1 = (TextView) findViewById(R.id.uuid);
            tv1.setText(stringArray[0]);

            tv2 = (TextView) findViewById(R.id.major);
            tv2.setText(stringArray[1]);

            tv3 = (TextView) findViewById(R.id.minor);
            tv3.setText(stringArray[2]);

            tv4 = (TextView) findViewById(R.id.rssi);
            tv4.setText(stringArray[3]);

            for (int i = 0; i < stringArray.length; i++) {
                backup[i] = stringArray[i];
            }

        }else if("A".equals(stringArray[0])){
            tv1 = (TextView) findViewById(R.id.uuid);
            tv1.setText(backup[0]);

            tv2 = (TextView) findViewById(R.id.major);
            tv2.setText(backup[1]);

            tv3 = (TextView) findViewById(R.id.minor);
            tv3.setText(backup[2]);

            tv4 = (TextView) findViewById(R.id.rssi);
            tv4.setText(backup[3]);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getContentResolver().registerContentObserver(UserColumns.CONTENT_URI,true,mContentObserver);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getContentResolver().unregisterContentObserver(mContentObserver);
    }
}
