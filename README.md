# BeaconRoomRecognition

部屋認識専用アプリです。  
BeaconRR.jarを使用しています。  
スマートフォンのBluetoothをONにして使用してください。  
**部屋情報を利用するアプリを開発する場合は、このアプリと合わせて使用してください。**   
  
現在、ビーコン情報と日時のみが表示される状態です。  
追加改善要素として、入退室した部屋の情報も表示する予定です。  
！(10/23 入室情報の表示を反映しました)  

また、現状ではそれらの情報が丸見えのため、何らかの簡単な方法で隠す予定です。  
良いアプリ名が思いついた方は連絡下さい。  
  
 
***  
## ビーコンのスキャン  
バックグラウンドで動作させてください。  
  
*受信開始コード*  
**`beaconApplication.BeaconScan(resolver, bluetoothManager, scan1,scan2,url);`**  
  
・`resolver`  
　　`ContentResolver resolver = getContentResolver();`  
　　としておいてください。  
・`bluetoothManager`  
　　`BluetoothManager bluetoothManager = (BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);`  
　　としておいてください。  
・`scan1`  
　　受信間隔です。ミリ秒で指定してください。  
・`scan2`  
　　受信時間です。ミリ秒で指定してください。  
　 （推奨は`scan1=3000, scan2=2000`ですが決まりは無いです。）  
・`url`  
　　部屋情報データベースへのアドレスです。  
　　適宜変更して使用してください。  
　　研究室内からは　`http://192.168.100.211/beacon_load.php`  
　　愛工大内のwifiからは　`http://192.168.54.167:60000/beacon_load.php`  
  
## データベースの監視、情報の取得  
このアプリではデータベースの監視、情報取得の両方を行っています。  

*データベースからの情報取得*  

    final ContentResolver resolver = getContentResolver();  
    for (int i = 0; i < dBaccess.monitoring(resolver).length; i++) {  
        room[i] = dBaccess.monitoring(resolver)[i];  
    }
上記のコードでデータベースから情報を取得しています。  
データベースには**0:キー番号 1:棟名 2:部屋名 3:部屋番号 4:日時**の順番で入っています。  

*データベースの監視*  

    ContentObserver mContentObserver = new ContentObserver(new Handler()) {  
      @Override  
      public void onChange(boolean selfChange) {  
        super.onChange(selfChange);  
        // 変更された時の処理を書く  
      }  
    };  
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
上記のコードでデータベースの監視をこ行っています。  
コメントの場所に変更されたときの処理を書いてください。  
