package tech.jameswharton.pwcompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity {
    EditText etItemName;
    EditText etDesc;
    Spinner spnItemType;
    Button btnCancel, btnSave;

    String name;
    String desc;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Get references to widgets
        etItemName = findViewById(R.id.etNewItemName);
        etDesc = findViewById(R.id.etNewItemDesc);
        spnItemType = findViewById(R.id.spnNewItemType);
        btnSave = findViewById(R.id.btnSaveNewItem);
        btnCancel = findViewById(R.id.btnCancelNewItem);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper helper = new MyDBHelper(AddActivity.this);

                helper.addItem(etItemName.getText().toString().trim(),
                        etDesc.getText().toString().trim(),
                        Integer.toString(spnItemType.getSelectedItemPosition()));

                btnSave.setEnabled(false);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        etItemName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnSave.setEnabled(true);
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}