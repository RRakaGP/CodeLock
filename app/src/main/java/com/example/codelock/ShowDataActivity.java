package com.example.codelock;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ShowDataActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    DBController controller = new DBController(this);
    SQLiteDatabase db ;
    private ArrayList<String> Note ;
    private ArrayAdapter<String> passAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Note = new ArrayList<>();
        lv = findViewById(R.id.lstvw);
        lv.setOnItemLongClickListener(this);
    }
    @Override
    protected void onResume() {
        displayData();
        super.onResume();
    }
    private void displayData() {
        db = controller.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  UserDetails",null);
        Note.clear();

        if (cursor.moveToFirst()) {
            do {
                Note.add(cursor.getString(cursor.getColumnIndex("NOTES")));

            } while (cursor.moveToNext());
        }
        //code to set adapter to populate list
        passAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Note);
        lv.setAdapter(passAdapter);

        cursor.close();
    }


    //    code on pressing navigation button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ShowDataActivity.this,ToDoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!hasFocus) {
            this.finishAffinity();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        db=controller.getWritableDatabase();
        db.delete("UserDetails", "NOTES" + " = ?", new String[]{Note.get(position)});
        Toast.makeText(getApplicationContext(),"Selected Note is removed",Toast.LENGTH_LONG).show();
        Note.remove(position);
        passAdapter.notifyDataSetChanged();
        return true;
    }
}

