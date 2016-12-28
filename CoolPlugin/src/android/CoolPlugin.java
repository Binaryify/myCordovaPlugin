package com.matd.coolplugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import woyou.aidlservice.jiuiv5.ICallback;
import woyou.aidlservice.jiuiv5.IWoyouService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;




public class CoolPlugin extends CordovaPlugin {

	public static final String TAG = "Cool Plugin";

	public CoolPlugin() {
	}

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		Log.v(TAG, "Init CoolPlugin");
    //启动服务
    final Activity act = cordova.getActivity(); 
    Intent intent = new Intent();
    intent.setPackage("woyou.aidlservice.jiuiv5");
    intent.setAction("woyou.aidlservice.jiuiv5.IWoyouService");
    act.startService(intent);//Start printer service
    act.bindService(intent, connService, Context.BIND_AUTO_CREATE);


    final int duration = Toast.LENGTH_SHORT;
    Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(),"服务启动成功", duration);
    toast.show();
		BluetoothManager.turnOnBluetooth();
    
   
	}

	public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final int duration = Toast.LENGTH_SHORT;
    final String ACTION_A = "toast";
    final String ACTION_B = "print";
    final String content = "action:"+action+" 打印内容:"+args.getString(0);
		// Shows a toast
		Log.v(TAG, "CoolPlugin received:" + action);
    
		cordova.getActivity().runOnUiThread(new Runnable() {
			public void run() {
        
        if(ACTION_A.equals(action)){
          Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), content, duration);
				  toast.show();
         }else{
           Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), content, duration);
          toast.show();
         }
				
			}
		});
		return true;
	}

  private IWoyouService woyouService;

	private ServiceConnection connService = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			woyouService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			woyouService = IWoyouService.Stub.asInterface(service);
		}
	};

  	ICallback callback = new ICallback.Stub() {

		@Override
		public void onRunResult(boolean success) throws RemoteException {
		}

		@Override
		public void onReturnString(final String value) throws RemoteException {
		}

		@Override
		public void onRaiseException(int code, final String msg)
				throws RemoteException {
		}
	};





}





