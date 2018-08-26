package com.onecalf.hard;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.onecalf.hard.widget.StepView;
import com.onecalf.hard.widget.SwitchButton;

public class CustomViewActivity extends Activity{
    TextView tvHello;
    SwitchButton switchButton;
    StepView stepView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_custom_layout);
        tvHello = findViewById(R.id.tv_hello);
        switchButton = findViewById(R.id.switchButton);
        stepView = findViewById(R.id.step_view);

//        tvHello.setTextSize(16);
        tvHello.setTextSize(TypedValue.COMPLEX_UNIT_PX, 46);

        switchButton.setSwitchOnClickedListener(new SwitchButton.OnSwitchClickedListener() {
            @Override
            public void onSwitchClicked(boolean isOpen) {
                Toast.makeText(CustomViewActivity.this,"isOpen=" + isOpen,Toast.LENGTH_SHORT).show();
            }
        });

        startStepView();


    }

    private void startStepView() {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,80);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                stepView.setCurrentStep(value);
            }
        });
        valueAnimator.start();
    }
}
