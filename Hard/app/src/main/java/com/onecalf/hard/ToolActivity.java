package com.onecalf.hard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.widget.EditText;

import com.onecalf.hard.tool.PointLengthFilter;

public class ToolActivity extends Activity{
    private EditText mEditText;

    private PointLengthFilter mFilter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tool_layout);

        initView();
        initData();
    }


    private void initView() {
        mEditText = findViewById(R.id.edit_text);

    }

    private void initData() {
        //保留小数点后几位
        mFilter = new PointLengthFilter(3);
        mEditText.setFilters(new InputFilter[]{mFilter});
    }

}
