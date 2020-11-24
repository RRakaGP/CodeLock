package com.example.codelock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class setPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pass);
    }

    public void set(View view) {
        EditText ip;
        EditText irp;
        //EditText phone;


        ip = findViewById(R.id.editText4);
        ip.requestFocus();
        irp = findViewById(R.id.editText5);
        //phone = findViewById(R.id.editText2);

        String pass = ip.getText().toString();
        String rpass = irp.getText().toString();
        //String phonen = phone.getText().toString();

        /*if(phonen.length()==10){
            SharedPreferences set = getSharedPreferences("NUM",0);
            SharedPreferences.Editor editor = set.edit();
            editor.putString("NUMBER",phonen);
            editor.apply();
        }*/

        if(pass.equals(rpass) ){
            if(pass.length()!=4){
                Toast.makeText(getApplicationContext(),"Set a 4 digit Password",Toast.LENGTH_LONG).show();
            }
            else {
                //setting password
                SharedPreferences set = getSharedPreferences("PREF",0);
                SharedPreferences.Editor editor = set.edit();
                editor.putString("password",pass);
                editor.apply();

                //redirecting to main app
                Intent intent = new Intent(setPassActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Invalid Number",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}

