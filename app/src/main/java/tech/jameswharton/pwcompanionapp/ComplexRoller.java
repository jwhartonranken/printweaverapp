package tech.jameswharton.pwcompanionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.shawnlin.numberpicker.NumberPicker;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class ComplexRoller extends AppCompatActivity {
    NumberPicker npD10, npOB;
    SwitchCompat swAdvantage, swDisadvantage, swCombat, swExploding;
    Button btnRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_roller);

        // Attach Widgets
        npD10 = findViewById(R.id.npD10);
        npOB = findViewById(R.id.npOB);
        swAdvantage = findViewById(R.id.swAdvantage);
        swDisadvantage = findViewById(R.id.swDisadvantage);
        swCombat = findViewById(R.id.swCombat);
        swExploding = findViewById(R.id.swExploding);
        btnRoll = findViewById(R.id.btnRollComplex);

        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollTheDiceComplex();
            }
        });
    }

    private void rollTheDiceComplex() {
        // Establish variables
        Random rand = new Random(); //instance of random class
        int successes = 0;
        int criticals = 0;
        String rollTotalComplex = "";
        boolean isSuccess = false;
        String resultString;

        // Grab values from numberpickers
        int obstacle = npOB.getValue();
        int dicePool = npD10.getValue();

        // Grab booleans from switches
        boolean atAdvantage = swAdvantage.isChecked();
        boolean atDisadvantage = swDisadvantage.isChecked();
        boolean isCombat = swCombat.isChecked();
        boolean isExploding = swExploding.isChecked();

        if (dicePool == 0) { isExploding = true; dicePool = 1; atDisadvantage = true;}

        int targetNumber = 6;

        if (atAdvantage && atDisadvantage){ }
        else if (atAdvantage) { targetNumber = 5; }
        else if (atDisadvantage) {targetNumber = 7;}

        // Main Loop
        for (int lcv = 0; lcv < dicePool; lcv++){
            int currentRoll = rand.nextInt(10) + 1;

            if (currentRoll >= targetNumber) {
                successes++;
                isSuccess = true;
            }

            if (currentRoll == 10 && isExploding) {
                dicePool++;
                criticals++;
            }
            else if (currentRoll == 10) {
                criticals++;
            }

            rollTotalComplex += Integer.toString(currentRoll);

            if (lcv == dicePool - 1){
            }
            else {
                rollTotalComplex += ", ";
            }

        }

        int damage = successes - obstacle;

        // Find out the result
        if(successes == obstacle && isCombat) {
            resultString = "Parry!";
        }
        else if (successes >= obstacle && criticals >= obstacle && isCombat) {
            resultString = "CRITICAL HIT! \r\n" + Integer.toString(damage) + " damage.\r\nApply effects.";
        }
        else if (successes >= obstacle && isCombat) {
            resultString = Integer.toString(damage) + " damage";
        }
        else if (successes >= obstacle) {
            resultString = "Success";
        }
        else if (isCombat) {
            resultString = "Miss";
        }
        else {
            resultString = "Failure";
        }

        String headline = Integer.toString(npD10.getValue()) + "D vs OB " + Integer.toString(obstacle) + ": \r\n" + Integer.toString(successes) + " successes";
        Intent intent = new Intent(this, RollComplete.class);
        intent.putExtra("d10s", Integer.toString(npD10.getValue()));
        intent.putExtra("obstacle", Integer.toString(obstacle));
        intent.putExtra("resultString", resultString);
        intent.putExtra("rollTotalComplex", rollTotalComplex);
        intent.putExtra("headline", headline);
        startActivity(intent);
    }
}