package tech.jameswharton.pwcompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.shawnlin.numberpicker.NumberPicker;
import android.widget.TextView;

import java.util.Random;

public class SimpleRoller extends AppCompatActivity {
    TextView d4, d6, d8, d10, d12, d20, d100;
    NumberPicker npAmount, npAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_roller);

        // Attach widgets to their respective layout versions
        d4 = findViewById(R.id.tvD4);
        d6 = findViewById(R.id.tvD6);
        d8 = findViewById(R.id.tvD8);
        d10 = findViewById(R.id.tvD10);
        d12 = findViewById(R.id.tvD12);
        d20 = findViewById(R.id.tvD20);
        d100 = findViewById(R.id.tvD100);
        npAmount = (NumberPicker) findViewById(R.id.npAmount);
        npAdd = (NumberPicker) findViewById(R.id.npD10);

        // Set On Click Listeners. All of these call the same function,
        // just with different values for the amount of sides.
        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollTheDice(4);
            }
        });

        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollTheDice(6);
            }
        });

        d8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollTheDice(8);
            }
        });

        d10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollTheDice(10);
            }
        });

        d12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollTheDice(12);
            }
        });

        d20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollTheDice(20);
            }
        });

        d100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollTheDice(100);
            }
        });
    }

    private void rollTheDice(int d) {
        Random rand = new Random(); //instance of random class
        //generate random values from 0-24

        int amount = npAmount.getValue();
        int add = npAdd.getValue();
        int rollTotal = 0;
        String rollString = "";

        for (int lcv = 0; lcv < amount; lcv++){
            int int_random = rand.nextInt(d) + 1;
            rollTotal += int_random;

            rollString += Integer.toString(int_random);
            if (lcv == amount -1){
                if (add != 0) {
                    rollTotal += add;
                    rollString += " + " + add;
                }
            }
            else {
                rollString += ",";
            }

            rollString += " ";
        }



        Intent intent = new Intent(this, RollComplete.class);
        intent.putExtra("amount", Integer.toString(amount));
        intent.putExtra("add", Integer.toString(add));
        intent.putExtra("rollString", rollString);
        intent.putExtra("sides", d);
        intent.putExtra("total", rollTotal);
        startActivity(intent);


    }
}