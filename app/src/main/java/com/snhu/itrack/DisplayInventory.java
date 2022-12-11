package com.snhu.itrack;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayInventory extends Activity {

    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb ;

    TextView name ;
    TextView count;

    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_inventory);
        name = findViewById(R.id.name);
        count = findViewById(R.id.count);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = (Cursor) mydb.getAllItems();
                id_To_Update = Value;
                rs.moveToFirst();

                @SuppressLint("Range") String nam = rs.getString(rs.getColumnIndex(DBHelper.INVENTORY_COLUMN_NAME));
                @SuppressLint("Range") String coun = rs.getString(rs.getColumnIndex(DBHelper.INVENTORY_COLUMN_COUNT));

                if (!rs.isClosed())  {
                    rs.close();
                }

                Button b = findViewById(R.id.add);
                b.setVisibility(View.INVISIBLE);

                name.setText(nam);
                name.setFocusable(false);
                name.setClickable(false);

                count.setText(coun);
                count.setFocusable(false);
                count.setClickable(false);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R.menu.display_inventory, menu);
            } else{
                getMenuInflater().inflate(R.menu.main_menu, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {

            case R.id.Edit_Inventory:

                Button b = findViewById(R.id.subtract);
                b.setVisibility(View.VISIBLE);

                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                count.setEnabled(true);
                count.setFocusableInTouchMode(true);
                count.setClickable(true);

                return true;

//            case R.id.Delete_Inventory:
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//                builder.setMessage("Are you sure you want to delete this inventory item?")
//                        .setPositiveButton("YES", (dialog, id) -> {
//                            mydb.deleteItem(id_To_Update);
//
//                            Toast.makeText(getApplicationContext(), "Deleted Successfully",
//                                    Toast.LENGTH_SHORT).show();
//
//                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//
//                            startActivity(intent);
//                        })
//
//                        .setNegativeButton("NO", (dialog, id) -> {
//                            // User cancelled the dialog
//                        });
//
//                AlertDialog d = builder.create();
//                d.setTitle("Are you sure");
//                d.show();
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);

        }
        return  true;
    }

    public void run(View view) {

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                if(mydb.updateItem(id_To_Update, name.getText().toString(), count.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            } else{
                if(mydb.insertItem(name.getText().toString(), count.getText().toString())){
                    Toast.makeText(getApplicationContext(), "done",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
