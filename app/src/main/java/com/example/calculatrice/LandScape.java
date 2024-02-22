package com.example.calculatrice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;

public class LandScape extends AppCompatActivity {

  private EditText editText;
  private String Expression = "";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.paysage);
    EditText editText = findViewById(R.id.edtResult);

    View.OnClickListener nbrbtnClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Button b = (Button) view;
        editText.append(b.getText().toString());
        Expression += b.getText().toString();
        }
    };
//
    int[] numberButtonIds = new int[] {
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
            R.id.btn_dot
    };
    int[] operationButtonIds = new int[] {
            R.id.btn_clear, R.id.btn_divide, R.id.btn_multiply, R.id.btn_subtract, R.id.btn_add, R.id.btn_equals
    };
    int[] scientificOperationButtonIds = new int[] {
            R.id.btn_x2, R.id.btn_x3, R.id.btn_x4, R.id.btn_factorial, R.id.btn_square_root, R.id.btn_nth_root,
            R.id.btn_e, R.id.btn_ln, R.id.btn_log, R.id.btn_sin, R.id.btn_cos, R.id.btn_tan,
            R.id.btn_open_parenthesis, R.id.btn_close_parenthesis, R.id.btn_pi
    };
  }
  // Function to handle number button click
  private void handleNumberButtonClick(View v) {
    String buttonText = ((Button) v).getText().toString();
    EditText edtResult = findViewById(R.id.edtResult);
    edtResult.append(buttonText);
  }


}
