# BeaconRoomRecognition

部屋認識専用アプリです。  
BeaconRR.jarを使用しています。  
スマートフォンのBluetoothをONにして使用してください。  
**部屋情報を利用するアプリを開発する場合は、このアプリと合わせて使用してください。**   
  
現在、ビーコン情報と日時のみが表示される状態です。  
追加改善要素として、入退室した部屋の情報も表示する予定です。  
！(10/23 入室情報の表示を反映しました)

また、現状ではそれらの情報が丸見えのため、何らかの簡単な方法で隠す予定です。  
  
 
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
  
