package com.digicon_valley.servicesthread;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;


public class MyServices extends Service {

    final class MyThreadClass implements Runnable{

        int service_id;

        MyThreadClass(int service_id){
            this.service_id=service_id;
        }

        @Override
        public void run() {

            int i=0;
            synchronized(this){

                while (i<10){
                    try{
                        wait(1500);
                        i++;
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                stopSelf(service_id);
            }

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this,"Service Started...",Toast.LENGTH_SHORT).show();

        Thread thread=new Thread(new MyThreadClass(startId));
        thread.start();
        return START_STICKY;
    }


    @Override
    public void onDestroy() {

        Toast.makeText(this,"Service Destroy...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
