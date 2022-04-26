package tech.jameswharton.pwcompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleItemExpandedActivity extends AppCompatActivity {
    TextView tvItemName, tvItemDesc;
    Button btnReturn;
    ImageView ivItemType;
    
    String name;
    String desc;
    String type;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_expanded);

        tvItemName = findViewById(R.id.tvItemName);
        tvItemDesc = findViewById(R.id.tvItemDesc);
        ivItemType = findViewById(R.id.ivItemType);
        btnReturn = findViewById(R.id.btnReturnFromIndiv);
        getAndSetIntentData();

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getAndSetIntentData() {
        if( getIntent().hasExtra("name") &&
                getIntent().hasExtra("desc") &&
                getIntent().hasExtra("type")) {
            // Get data
            name = getIntent().getStringExtra("name");
            desc = getIntent().getStringExtra("desc");
            type = getIntent().getStringExtra("type");

            // Set Data
            tvItemName.setText(name);
            tvItemDesc.setText(desc);

            if (Integer.parseInt(type) == 0) {
                ivItemType.setImageResource(R.drawable.ring);
            }
            else {
                ivItemType.setImageResource(R.drawable.scroll);
            }

        }
        else {
            Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show();
        }
    }
}