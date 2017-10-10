package com.example.thispc.demo_demso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mtxtView1;
    TextView mtxtView2;
    private Button btnStart;
    Thread mthread=null;
    private boolean isRunning=true;
    MyAsyncTask mytt;
    int start = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.bnt_start);

        }
        private void doStart()
        {
            //truyền this (chính là MainActivity hiện tại) qua Child Thread
            mytt =new MyAsyncTask(this);
            //Kích hoạt Tiến trình
            //khi gọi hàm này thì onPreExecute của mytt sẽ thực thi trước
            mytt.execute();
        }
    public synchronized void Start (View view) throws InterruptedException{
        doStart();
//        mtxtView1=(TextView) findViewById(R.id.txt_view);
//        mtxtView2=(TextView) findViewById(R.id.txt_view2);
//
//        if(mthread==null){
//            isRunning=false;
//            mthread= new Thread(myrun);
//            mthread.start();
//
//        }else{
//
//            isRunning=!isRunning;
//            mthread.start();
//        }


    }
    Myrunnable myrun=new Myrunnable();

    public class Myrunnable implements Runnable{

            @Override
            public void run() {
                for(int i=start;i<Integer.MAX_VALUE;i++){

                    if(!isRunning) {
                        final int finalI = i;
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("NHAN", "" + finalI);
                                mtxtView1.setText(String.valueOf(finalI));
                            }
                        });

                    }else{
                        return;
                    }

                    start=i;


                }
            }

    }

}
