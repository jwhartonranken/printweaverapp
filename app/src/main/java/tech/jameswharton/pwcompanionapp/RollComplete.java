package tech.jameswharton.pwcompanionapp;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class RollComplete extends AppCompatActivity {
    ConstraintLayout mConstraintLayout;
    TextView tvDieType, tvBigResult, tvArrayOfRolls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roll_complete);

        tvDieType = findViewById(R.id.tvDieType);
        tvBigResult = findViewById(R.id.tvBigResult);
        tvArrayOfRolls = findViewById(R.id.tvArrayOfRolls);
        mConstraintLayout = findViewById(R.id.rollResultConstraint);

        // If you click anywhere on this screen, it closes
        mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (getIntent().getStringExtra("resultString") != null &&
                getIntent().getStringExtra("rollTotalComplex")  != null &&
                getIntent().getStringExtra("headline") != null) {

            String headline = getIntent().getStringExtra("headline");
            String resultString = getIntent().getStringExtra("resultString");
            String rollTotalComplex = getIntent().getStringExtra("rollTotalComplex");

            // Set text for #D vs #OB
            tvDieType.setText(headline);

            // Set text for total number
            tvBigResult.setText(resultString);

            // Insert the result array
            tvArrayOfRolls.setText(rollTotalComplex);
        }

        // Test to ensure the extras were inserted
        if (getIntent().getStringExtra("rollString") != null &&
            getIntent().getStringExtra("add")  != null &&
            getIntent().getStringExtra("amount") != null) {

            // Get extras
            String rollResult = getIntent().getStringExtra("rollString");
            String add = getIntent().getStringExtra("add");
            String amount = getIntent().getStringExtra("amount");
            int sides = getIntent().getIntExtra("sides", 0);
            int total = getIntent().getIntExtra("total", 0);

            // Check for length of result, as if it's too long then it won't fit onto
            // string.
            if (rollResult.length() >= 300 && sides > 8) {
                tvArrayOfRolls.setTextSize(20);
            }
            // Set text for #d + #
            String temp = amount + "d" + Integer.toString(sides);
            if (Integer.parseInt(add) > 0) {
                temp += " + " + add;
            }
            tvDieType.setText(temp);

            // Set text for total number
            temp = Integer.toString(total);
            tvBigResult.setText(temp);

            // Insert the result array
            tvArrayOfRolls.setText(rollResult);
        }



    }
}
