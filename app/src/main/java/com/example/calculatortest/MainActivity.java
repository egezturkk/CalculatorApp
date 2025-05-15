package com.example.calculatortest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String operation = "";
    double number1;
    double number2;
    double answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView result = findViewById(R.id.result);
        TextView display = findViewById(R.id.display);

        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);
        Button num0 = findViewById(R.id.num0);

        Button plus = findViewById(R.id.plus);
        Button equals = findViewById(R.id.equals);
        Button divide = findViewById(R.id.divide);
        Button minus = findViewById(R.id.minus);
        Button multiply = findViewById(R.id.multiply);
        Button backspace = findViewById(R.id.backspace);
        Button sin = findViewById(R.id.sin);
        Button cos = findViewById(R.id.cos);
        Button square_root = findViewById(R.id.square_root);
        Button power2 = findViewById(R.id.power2);
        Button log = findViewById(R.id.log);
        Button clear = findViewById(R.id.clear);
        Button dot = findViewById(R.id.dot);
        Button negative = findViewById(R.id.negative);

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);

        ArrayList<Button> operators = new ArrayList<>();
        operators.add(minus);
        operators.add(plus);
        operators.add(multiply);
        operators.add(divide);


        for (Button i : nums){
            i.setOnClickListener(view -> {
                if (result.getText().toString().equals("0")){
                    result.setText(i.getText().toString());
                }
                else{
                    result.setText(result.getText().toString() + i.getText().toString());
                }
            });
        }


        for (Button i : operators){
            i.setOnClickListener(view -> {
                if(operation.isEmpty()){
                    number1 = Double.parseDouble(result.getText().toString());
                    operation = i.getText().toString();
                    display.setText(result.getText().toString() + i.getText().toString());
                    result.setText("0");
                }
                else{
                    if(result.getText().toString().equals("0")){
                        String number_str = formatStr(number1);
                        display.setText(number_str + i.getText().toString());
                    }
                    else {
                        number2 = Double.parseDouble(result.getText().toString());
                        switch (operation) {
                            case "+":
                                answer = number1 + number2;
                                break;
                            case "—":
                                answer = number1 - number2;
                                break;
                            case "x":
                                answer = number1 * number2;
                                break;
                            case "÷":
                                answer = number1 / number2;
                                break;
                        }
                        operation = i.getText().toString();
                        number1 = answer;
                        display.setText(answer + operation);
                        result.setText("0");
                    }
                }
            });
        }

        backspace.setOnClickListener(view -> {
            int len = result.getText().toString().length();
            if(len == 1){
                result.setText("0");
            }
            else {
                result.setText(result.getText().toString().substring(0, len - 1));
            }
        });

        clear.setOnClickListener(view -> {
            result.setText("0");
            display.setText("");
            number1 = 0;
            number2 = 0;
            operation = "";
        });


        // sıfırla bölmede ekrana NaN yaz
        equals.setOnClickListener(view -> {
            number2 = Double.parseDouble(result.getText().toString());
            if(operation.isEmpty()){
                answer = number2;
            }
            switch (operation){
                case "+":
                    answer = number1 + number2;
                    break;
                case "—":
                    answer = number1 - number2;
                    break;
                case "x":
                    answer = number1 * number2;
                    break;
                case "÷":
                    if(number2 == 0){
                        double ZERO = 0;
                        answer = ZERO / ZERO;
                        break;
                    }
                    else {
                        answer = number1 / number2;
                        break;
                    }
            }
            String dummy_ans = formatStr(number1);
            display.setText( dummy_ans + operation+ result.getText().toString() + "=");
            String answer_str;
            operation = "";
            number1 = 0;
            number2 = 0;
            if(answer % 1 == 0){
                answer_str = String.format("%.0f", answer);
            }
            else {
                answer_str = String.valueOf(answer);
            }
            result.setText(answer_str);
            answer = 0;
        });

        dot.setOnClickListener(view -> {
            if(!result.getText().toString().contains(".")){
                result.setText(result.getText().toString() + ".");
            }
        });


        sin.setOnClickListener(view -> {
            double number_deg = Double.parseDouble(result.getText().toString());
            double number_rad = Math.toRadians(number_deg);
            double sin_ans_double = Math.sin(number_rad);
            if(Math.abs(sin_ans_double) < 1e-12){
                sin_ans_double = 0;
            }
            String sin_ans = String.valueOf(sin_ans_double);
            result.setText(sin_ans);

            display.setText(display.getText().toString() + "sin(" + number_deg + ")");
        });


        cos.setOnClickListener(view -> {
            double number_deg = Double.parseDouble(result.getText().toString());
            double number_rad = Math.toRadians(number_deg);
            double cos_ans_double = Math.cos(number_rad);
            if(Math.abs(cos_ans_double) < 1e-12){
                cos_ans_double = 0;
            }
            String cos_ans = String.valueOf(cos_ans_double);
            result.setText(cos_ans);
            display.setText(display.getText().toString() + "cos(" + number_deg + ")");
        });



        log.setOnClickListener(view -> {
            double number = Double.parseDouble(result.getText().toString());
            String log_ans = String.valueOf(Math.log10(number));
            result.setText(log_ans);
            display.setText(display.getText().toString() + "log(" + number + ")");
        });


        square_root.setOnClickListener(view -> {
            double number = Double.parseDouble(result.getText().toString());
            String sqrt_ans = String.valueOf(Math.sqrt(number));
            result.setText(sqrt_ans);
            display.setText(display.getText().toString() + "√" + number);
        });


        power2.setOnClickListener(view -> {
            double number = Double.parseDouble(result.getText().toString());
            String power_ans = String.valueOf(Math.pow(number,2));
            result.setText(power_ans);
            display.setText(String.format("%s (%s)²",display.getText().toString(), number));
        });

        negative.setOnClickListener(view -> {
            double number = Double.parseDouble(result.getText().toString());
            String number_ans;
            if (number != 0){
                number = 0 - number;
            }

            number_ans = formatStr(number);
            result.setText(number_ans);
        });

    }

    public String formatStr(Double number){

        String number_ans;
        if(number % 1 == 0){
            number_ans = String.format("%.0f", number);
        }
        else{
            number_ans = String.valueOf(number);
        }
        return number_ans;
    }
}