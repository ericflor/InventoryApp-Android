package com.snhu.itrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "MESSAGE";

    private ListView list;

    DBHelper mydb;

    EditText addProductName, addProductQuanity;

    Button addInventory;

    private ArrayList<String> data = new ArrayList<>();
    private ArrayList<String> data1 = new ArrayList<>();
    private ArrayList<String> data2 = new ArrayList<>();
    private ArrayList<String> data3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addProductName = findViewById(R.id.enterName);
        addProductQuanity = findViewById(R.id.enterQuantity);

        addInventory = findViewById(R.id.addButton);
        addInventory.setOnClickListener(view -> add());

        mydb = new DBHelper(this);

        ArrayList array_list = (ArrayList) mydb.getAllItems();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.activity_list_item, array_list);

        list = findViewById(R.id.listView1);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
            // TODO Auto-generated method stub
            int id_To_Search = arg2 + 1;

            Bundle dataBundle = new Bundle();
            dataBundle.putInt("id", id_To_Search);

            Intent intent = new Intent(getApplicationContext(),DisplayInventory.class);

            intent.putExtras(dataBundle);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.item1:Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(),DisplayInventory.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }

    public void add(){



    }
}