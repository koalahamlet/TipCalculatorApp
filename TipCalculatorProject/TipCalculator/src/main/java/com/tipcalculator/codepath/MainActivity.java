package com.tipcalculator.codepath;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    // I forget why things have to be done up here.
    public double multiplier = 0.1;
    private EditText etAmount;
    private EditText etOther;
    private TextView tvTip;
    private RadioGroup rgPGTips;
    private RadioButton rb10;
    private RadioButton rb15;
    private RadioButton rb20;
    private RadioButton rbOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = (EditText) findViewById(R.id.etAmount);
        etOther = (EditText) findViewById(R.id.etOther);
        tvTip = (TextView) findViewById(R.id.tvTip);
        rgPGTips = (RadioGroup) findViewById(R.id.radioGroupTip);
        rb10 = (RadioButton) findViewById(R.id.b10);
        rb15 = (RadioButton) findViewById(R.id.b15);
        rb20 = (RadioButton) findViewById(R.id.b20);
        rbOther = (RadioButton) findViewById(R.id.bOther);

        rgPGTips.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rb10.isChecked()){
                    etOther.setVisibility(View.INVISIBLE);
                    multiplier = 0.1;
                    if (etAmount.getText().length()!=0){
                        calculateTip();
                    }
                }else if(rb15.isChecked()){
                    etOther.setVisibility(View.INVISIBLE);
                    multiplier = 0.15;
                    if (etAmount.getText().length()!=0){
                        calculateTip();
                    }
                }else if(rb20.isChecked()){
                    etOther.setVisibility(View.INVISIBLE);
                    multiplier = 0.20;
                    if (etAmount.getText().length()!=0){
                        calculateTip();
                    }
                }else if(rbOther.isChecked()){
                    etOther.setVisibility(View.VISIBLE);
                    //something will be made visible
                }


            }
        });

        etAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etAmount.getText().length()!=0){
                    calculateTip();
                }else{
                    tvTip.setText("0.00");
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                //Nothing

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Nothing

            }
        });

        etOther.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

               if (etOther.getText().length()!=0 && etAmount.getText().length()!=0 ){
                   String sAmount = etOther.getText().toString();
                   multiplier = Double.parseDouble(sAmount)*0.01;
                   calculateTip();
               }else{
                   tvTip.setText("0.00");
               }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                //Nothing

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Nothing

            }
        });

    }

    private void calculateTip() {

            Double tip = multiplier*Double.parseDouble(etAmount.getText().toString());
            tip = Double.valueOf(Math.round(tip * 100));
            tip = tip/100;
            tvTip.setText(tip.toString());

    }


}
