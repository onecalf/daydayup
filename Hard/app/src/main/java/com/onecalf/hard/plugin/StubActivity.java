package com.onecalf.hard.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.onecalf.hard.R;

public class StubActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_stub_layout);

    }
}
