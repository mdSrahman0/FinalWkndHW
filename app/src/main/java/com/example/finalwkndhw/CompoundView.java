package com.example.finalwkndhw;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class CompoundView extends LinearLayout implements View.OnClickListener {

    TextView tvCityName;
    TextView tvResult;
    RadioButton rbFahrenheit;
    RadioButton rbCelsius;

    public CompoundView(Context context) {
        super(context);
    }

    public CompoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CompoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private void init() {
        inflate(getContext(), R.layout.compound_layout, this);
        tvCityName = findViewById(R.id.tvCityName);
        tvResult= findViewById(R.id.tvResult);
        rbFahrenheit = findViewById(R.id.rbFahrenheit);
        rbCelsius = findViewById(R.id.rbCelsius);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbFahrenheit:
                break;
            case R.id.rbCelsius:
                break;
        }
    }
}
