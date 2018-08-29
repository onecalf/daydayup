package com.onecalf.hard.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.onecalf.hard.R;

/**
 * 测试插件化的开始界面
 */
public class PluginTestActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_plugin_test_layout);
    }
}
