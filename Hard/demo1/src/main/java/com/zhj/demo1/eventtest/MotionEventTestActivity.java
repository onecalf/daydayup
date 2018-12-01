package com.zhj.demo1.eventtest;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.zhj.demo1.R;

public class MotionEventTestActivity extends Activity {

    static final String TAG = "ITSActivity";

    TextView tv;
   

    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     }
 }
