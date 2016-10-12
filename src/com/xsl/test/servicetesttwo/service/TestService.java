package com.xsl.test.servicetesttwo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TestService extends Service {
	private static final String actionControl="com.xsl.test.servicetesttwo.service.doService";
	private static final String actionTransferUpdate="com.xsl.test.servicetesttwo.service.transferUpdate";

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		
		System.out.println("@@@: "+intent.getStringExtra("postMsg"));
		
		final Intent postIntent=new Intent();
		final String transferData=intent.getStringExtra("postMsg");
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true){
					
					try {
						Thread.sleep(300);
						postIntent.setAction(actionTransferUpdate);
						postIntent.putExtra("postMsg",transferData);
						sendBroadcast(postIntent);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			
		}).start();
		
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

}
