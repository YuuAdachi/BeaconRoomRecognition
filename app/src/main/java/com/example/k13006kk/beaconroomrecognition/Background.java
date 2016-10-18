package com.example.k13006kk.beaconroomrecognition;

import android.app.Service;
import android.bluetooth.BluetoothManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.example.k13006kk.mylibrary.BeaconApplication;

/**
 * バックグラウンドで部屋認識をするクラス
 */

public class Background extends Service {

    Context context;

    // 部屋情報DBへのアドレス
    String url = "http://192.168.54.167:60000/beacon_load.php";//http://192.168.100.211/beacon_load.php

    @Override
    public void onCreate() {
        super.onCreate();

        ContentResolver resolver = getContentResolver();
        this.context = new Background();
        BeaconApplication beaconApplication = new BeaconApplication();
        final BluetoothManager bluetoothManager2 = (BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);

        int scan1 = 3000;//基本は4.6秒間隔//2600
        int scan2 = 2000;//基本は0.4秒間スキャン//800

        // ビーコン受信＆部屋認識開始
        beaconApplication.BeaconScan(resolver, bluetoothManager2, scan1,scan2,url);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
