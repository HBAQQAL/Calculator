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

import java.util.HashMap;
import java.util.Map;

public class LandScape extends AppCompatActivity {

  private EditText editText;
  private String Expression = "";

  private Map<String, String> operationMap;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.paysage);
    editText = findViewById(R.id.edtResult);
    setNumberButtonListeners();
    setOperationButtonListeners();
    setScientificOperationButtonListeners();
    setDeleteButtonListener();
    operationMap = new HashMap<>();
    operationMap.put("\u00B2", "^2");
    operationMap.put("\u00B3", "^3");
    operationMap.put("\u00B4", "^4");
    operationMap.put("\u00F7", "/");
    operationMap.put("\u00D7", "*");
    operationMap.put("!", "!");
    operationMap.put("\u221A", "sqrt");
    operationMap.put("\u221An", "root");
    operationMap.put("e", "exp");
    operationMap.put("ln", "ln");
    operationMap.put("log", "log");
    operationMap.put("sin", "sin");
    operationMap.put("cos", "cos");
    operationMap.put("tan", "tan");
    operationMap.put("\u03C0", "pi");
    operationMap.put("+", "+");
    operationMap.put("-", "-");
    operationMap.put(")", ")");
    operationMap.put("(", "(");
    operationMap.put("%", "%");

    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
      // end the current activity
      finish();

    }
  }

  // Function to handle number button click
  // Function to set onClickListener for number buttons
  private void setNumberButtonListeners() {
    int[] numberButtonIds = new int[] {
        R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
        R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9
    };

    for (int id : numberButtonIds) {
      Button button = findViewById(id);
      button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          handleNumberButtonClick(v);
        }
      });
    }
  }

  // Function to handle number button click
  private void handleNumberButtonClick(View v) {
    String buttonText = ((Button) v).getText().toString();
    editText.append(buttonText);
    Expression += buttonText;
  }

  // Function to handle arithmetic operation button click
  // Function to set onClickListener for operation buttons
  private void setOperationButtonListeners() {
    int[] operationButtonIds = new int[] {
        R.id.btn_clear, R.id.btn_divide, R.id.btn_multiply, R.id.btn_subtract, R.id.btn_add, R.id.btn_equals
    };

    for (int id : operationButtonIds) {
      Button button = findViewById(id);
      button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          handleOperationButtonClick(v);
        }
      });
    }
  }

  // Function to handle operation button click
  private void handleOperationButtonClick(View v) {
    String buttonText = ((Button) v).getText().toString();
    EditText edtResult = findViewById(R.id.edtResult);

    switch (buttonText) {
      case "C":
        edtResult.setText(""); // Clear the EditText
        Expression = "";
        break;
      case "=":
        double result = evaluateExpression(Expression);
        edtResult.setText(String.valueOf(result)); // Evaluate the expression and set the result to the EditText
        Expression = String.valueOf(result);
        break;
      default:
        // Append the operation to the EditText
        edtResult.append(buttonText);
        Expression += operationMap.get(buttonText);

        break;
    }
  }

  // Function to evaluate an expression
  private double evaluateExpression(String expression) {
    Expression expr = new Expression(Expression);
    String result = String.valueOf(expr.calculate());
    return Double.parseDouble(result);
  }

  // Function to handle scientific operation button click
  // Function to set onClickListener for scientific operation buttons
  private void setScientificOperationButtonListeners() {
    int[] scientificOperationButtonIds = new int[] {
        R.id.btn_x2, R.id.btn_x3, R.id.btn_x4, R.id.btn_factorial, R.id.btn_square_root, R.id.btn_nth_root,
        R.id.btn_e, R.id.btn_ln, R.id.btn_log, R.id.btn_sin, R.id.btn_cos, R.id.btn_tan,
        R.id.btn_open_parenthesis, R.id.btn_close_parenthesis, R.id.btn_pi
    };

    for (int id : scientificOperationButtonIds) {
      Button button = findViewById(id);
      button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          handleScientificOperationButtonClick(v);
        }
      });
    }
  }

  // Function to handle scientific operation button click
  private void handleScientificOperationButtonClick(View v) {
    String buttonText = ((Button) v).getText().toString();
    EditText edtResult = findViewById(R.id.edtResult);
    edtResult.append(buttonText);

    Expression += operationMap.get(buttonText);

  }

  // Function to set onClickListener for delete button
  private void setDeleteButtonListener() {
    Button button = findViewById(R.id.btn_backspace);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        handleDeleteButtonClick();
      }
    });
  }

  // Function to handle delete button click
  private void handleDeleteButtonClick() {
    EditText edtResult = findViewById(R.id.edtResult);
    String currentText = edtResult.getText().toString();

    // Check if the EditText is not empty
    if (!currentText.isEmpty()) {
      // Remove the last character
      String newText = currentText.substring(0, currentText.length() - 1);
      edtResult.setText(newText);
      Expression = Expression.substring(0, Expression.length() - 1);
      edtResult.setText(Expression);
    }
  }

}
