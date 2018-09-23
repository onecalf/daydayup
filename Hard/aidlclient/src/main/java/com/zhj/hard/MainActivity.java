package com.zhj.hard;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.onecalf.hard.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    Button btnCalc;
    TextView tvResult;

    IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalc = findViewById(R.id.btn_calc);
        tvResult = findViewById(R.id.tv_result);

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.onecalf.hard","com.onecalf.hard.IRemoteService"));
        bindService(intent,conn, Context.BIND_AUTO_CREATE);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int result = iMyAidlInterface.sum(10,34);
                    tvResult.setText("result=" + result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
