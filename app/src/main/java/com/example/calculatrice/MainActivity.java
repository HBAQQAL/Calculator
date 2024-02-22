package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private String Expression = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edtResult);
        View.OnClickListener btnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                editText.append(b.getText().toString());
                switch (b.getText().toString()) {
                    case "+":
                        Expression += "+";
                        break;
                    case "-":
                        Expression += "-";
                        break;
                    case "×":
                        Expression += "*";
                        break;
                    case "÷":
                        Expression += "/";
                        break;
                    default:
                        Expression += b.getText().toString();
                        break;
                }
            }
        };
        Button btnEqual = findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Expression expr = new Expression(Expression);
                String result = String.valueOf(expr.calculate());
                editText.setText(result);
                Expression = result;
            }
        });
        Button btnC = findViewById(R.id.btnC);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                Expression = "";
            }
        });
        int[] numberButtonIds = { R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot, R.id.btnDot, R.id.btnMod };
        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(btnClickListener);
        }
        int[] opButtonIds = { R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv };
        for (int id : opButtonIds) {
            findViewById(id).setOnClickListener(btnClickListener);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            Intent intent = new Intent(MainActivity.this, LandScape.class);
            startActivity(intent);
        }

    }

}