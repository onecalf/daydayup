package com.onecalf.hard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import com.butterknife.ButterKnife;
import com.butterknife.annotation.BindView;
import com.onecalf.hard.util.StatusUtil;

public class TestActivity extends Activity {
    @BindView(R.id.btn_hello)
    Button btnHello;

    @BindView(R.id.btn_hello1)
    Button getBtnHello1;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test);
        StatusUtil.setStatusBarColor(this,getResources().getColor(R.color.colorGray2));
        ButterKnife.bind(this);
    }
}
