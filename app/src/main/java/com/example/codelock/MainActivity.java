package com.example.codelock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.hanks.passcodeview.PasscodeView;

public class MainActivity extends AppCompatActivity {

    PasscodeView passcodeview;
    static public String password="";
    int count = 1;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passcodeview = findViewById(R.id.passcode_view);

        SharedPreferences set = getSharedPreferences("PREF", 0);
        password = set.getString("password","");

        passcodeview.setLocalPasscode(password);
        if(passcodeview.getLocalPasscode().equals("")){
            Intent intent1 = new Intent(MainActivity.this,setPassActivity.class);
            startActivity(intent1);
        }
        else {
            passcodeview.setLocalPasscode(password)
                 .setListener(new PasscodeView.PasscodeViewListener() {
                  @Override
                  public void onFail() {
                       /*not working !!!!!!!!!!!!!!!!!!!!!!!!!!!
                      count += 1;
                      if(count == 3) {
                          count = 0;
                          Toast.makeText(getApplicationContext(), "Alert msg sent to", Toast.LENGTH_LONG).show();
                          //Intent intent2 = new Intent(MainActivity.this, SMSActivity.class);
                          //startActivity(intent2);
                      }*/

                  }
                  @Override
                  public void onSuccess(String number) {
                        Intent intent3 = new Intent(MainActivity.this, ToDoActivity.class);
                           startActivity(intent3);
                  }

            });

        }
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!hasFocus) {
            this.finishAffinity();
        }
    }
}
