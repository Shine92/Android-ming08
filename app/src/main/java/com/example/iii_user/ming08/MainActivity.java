package com.example.iii_user.ming08;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
        private TextView tv;
        private MyHandler handler;
        private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = new Timer();
        handler = new MyHandler();
        tv=(TextView)findViewById(R.id.tv);

    }
    public void test1(View view){
        MyThread1 mt1 = new MyThread1();
        mt1.start();

    }
    public void test2(View view){
        timer.schedule(new MyTask(),0,500);
    }

    @Override
    public void finish() {
        if(timer != null){
            timer.purge();
            timer.cancel();
            timer = null;
        }
        super.finish();
    }

    private  class MyTask extends TimerTask{
        int i;
        @Override
        public void run() {
            Log.i("ming"," "+i++);
        }
    }
    private class MyThread1 extends Thread{
        @Override
        public void run() {
            for(int i =0;i<10;i++){
                Log.i("ming", "" + i);

                
                Message mesg = new Message();
                mesg.what = i;
                Bundle b = new Bundle();
                b.putInt("k1",1);
                b.putString("k2", "ming");
                mesg.setData(b);
                handler.sendMessage(mesg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }
        }
    }
    private class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("ming", "Handler");
            tv.setText("ok" + msg.what);

            Bundle b = msg.getData();
            int k1 = b.getInt("k1");
            String k2 = b.getString("k2");
            Log.i("ming",k1+":"+k2);

        }
    }
}
