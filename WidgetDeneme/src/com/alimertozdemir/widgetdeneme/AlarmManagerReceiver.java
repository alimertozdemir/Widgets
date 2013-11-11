package com.alimertozdemir.widgetdeneme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.RemoteViews;

public class AlarmManagerReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
		//Acquire the lock
		wl.acquire();

		//You can do the processing here update the widget/remote views.
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.main_layout);
		String time = Utility.getCurrentTime("hh:mm:ss a");
		remoteViews.setTextViewText(R.id.currency, time);
		//Toast.makeText(context, time, Toast.LENGTH_LONG).show();
		ComponentName thiswidget = new ComponentName(context, Widget.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.updateAppWidget(thiswidget, remoteViews);
		//Release the lock
		wl.release();
		
	}
	
	public void setOnetimeTimer(Context context){
		AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, Widget.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
	}
	

}
