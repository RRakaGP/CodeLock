package com.example.codelock;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class ToDoActivity extends AppCompatActivity implements View.OnClickListener{

    DBController db =new DBController(this);
    SQLiteDatabase database;
    EditText passText ;
    Button addb , showb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        addb = findViewById(R.id.button);
        showb = findViewById(R.id.button3);
        passText = findViewById(R.id.editText);
        addb.setOnClickListener(this);
        showb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.button)
        {
            if(!passText.getText().toString().equals("")) {
                database = db.getWritableDatabase();
                database.execSQL("INSERT INTO UserDetails(NOTES)VALUES('" + passText.getText().toString() + "')");
                Toast.makeText(this, "Note is saved To Your Database", Toast.LENGTH_LONG).show();
                passText.setText("");
            }
            else{
                Toast.makeText(this, "Enter Something to Note ", Toast.LENGTH_LONG).show();
            }
        }
        else  if(v.getId()==R.id.button3)
        {
            Intent intent1=new Intent(ToDoActivity.this,ShowDataActivity.class);
            startActivity(intent1);

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent2 = new Intent(ToDoActivity.this,MainActivity.class);
        startActivity(intent2);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!hasFocus) {
            this.finishAffinity();
        }
    }

}
