package tech.jameswharton.pwcompanionapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import tech.jameswharton.pwcompanionapp.MyDBHelper;

public class MagicItemsActivity extends AppCompatActivity {
    FloatingActionButton fabAdd;
    RecyclerView recyclerView;
    ArrayList<String> item_name;
    ArrayList<String> item_desc;
    ArrayList<String> item_type;

    MyDBHelper helper;
    MyAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDelete();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_items);

        fabAdd = findViewById(R.id.fabAddItem);
        recyclerView = findViewById(R.id.rvMagicItems);

        item_name = new ArrayList<>();
        item_desc = new ArrayList<>();
        item_type = new ArrayList<>();

        helper = new MyDBHelper(MagicItemsActivity.this);

        storeData();

        adapter = new MyAdapter(MagicItemsActivity.this,

                this,

                item_name, item_desc,

                item_type);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(MagicItemsActivity.this));

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MagicItemsActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    public void confirmDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete all records?");
        builder.setMessage("Are you sure you want to delete all records?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                MyDBHelper myDB = new MyDBHelper(MagicItemsActivity.this);

                // Delete All Records
                myDB.deleteAllRecords();

                //Refresh Activity
                Intent intent = new Intent(MagicItemsActivity.this, MagicItemsActivity.class);
                startActivity(intent);
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

    public void storeData()
    {
        Cursor cursor = helper.readData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data To Read",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                item_name.add(cursor.getString(0));
                item_desc.add(cursor.getString(1));
                item_type.add(cursor.getString(2));
            }
        }
    }
}