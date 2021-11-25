package com.yangke.app.java;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * author : yangke on 2021/3/17
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 主Activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText amountEdit = findViewById(R.id.editTextNumber);
        TextView amount10 = findViewById(R.id.textView);
        TextView amount20 = findViewById(R.id.textView2);
        TextView amount30 = findViewById(R.id.textView3);
        TextView amount40 = findViewById(R.id.textView4);

        amountEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    updateAmount(0);
                    return;
                }

                double amount = Double.parseDouble(s.toString());
                updateAmount(amount);
            }

            private void updateAmount(double amount) {
                amount10.setText("现金账户：" + amount * 0.1);
                amount20.setText("保障账户：" + amount * 0.2);
                amount30.setText("养老账号：" + amount * 0.4);
                amount40.setText("投资账户：" + amount * 0.3);
            }
        });
    }
}