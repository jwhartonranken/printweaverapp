package tech.jameswharton.pwcompanionapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText etItemName;
    EditText etDesc;
    Spinner spnItemType;
    Button btnCancel, btnUpdate, btnDelete;

    String name;
    String desc;
    String type;
    String oldDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etItemName = findViewById(R.id.etNameUpdate);
        etDesc = findViewById(R.id.etDescUpdate);
        spnItemType = findViewById(R.id.spnItemTypeUpdate);
        btnCancel = findViewById(R.id.btnCancelUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        getAndSetIntentData();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper myDB = new MyDBHelper(UpdateActivity.this);
                name = etItemName.getText().toString().trim();
                desc = etDesc.getText().toString().trim();
                type = Integer.toString(spnItemType.getSelectedItemPosition());
                myDB.updateData(name, desc, type, oldDesc);

                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
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
            oldDesc = getIntent().getStringExtra("desc");
            // Set Data
            etItemName.setText(name);
            etDesc.setText(desc);
            spnItemType.setSelection(Integer.parseInt(type));

        }
        else {
            Toast.makeText(this, "No Data for Update", Toast.LENGTH_LONG).show();
        }
    }

    public void confirmDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete " + name + " ?");

        builder.setMessage("Are you sure you want to delete " + name + " ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {

                MyDBHelper myDB = new MyDBHelper(UpdateActivity.this);

                myDB.deleteOneRecord(desc);

                finish();

            }

        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {

            }

        });

        builder.create().show();

    }

    @Override
    public void onBackPressed() {

    }
}