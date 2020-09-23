package com.example.healtech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.SQLTransactionRollbackException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar mToolbar;
    private DatabaseReference databaseReference;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == (R.id.action_signout)) {
            mAuth.signOut();
            Toast.makeText(this, "Oturum kapatıldı.", Toast.LENGTH_LONG).show();
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("HEALTECH Test Sayfası");

        mAuth = FirebaseAuth.getInstance();

        depresyonTestiSend();
        strTestiSend();
        anksiyeteTestiSend();
    }

    public void depresyonTestiSend() {
        final RadioGroup depRdGroup_1 = (RadioGroup) findViewById(R.id.depsorubirradiogrup);
        final RadioGroup depRdGroup_2 = (RadioGroup) findViewById(R.id.depsoruikiradiogrup);
        final RadioGroup depRdGroup_3 = (RadioGroup) findViewById(R.id.depsoruucradiogrup);
        final RadioGroup depRdGroup_4 = (RadioGroup) findViewById(R.id.depsorudortradiogrup);
        final RadioGroup depRdGroup_5 = (RadioGroup) findViewById(R.id.depsorubesradiogrup);
        final RadioGroup depRdGroup_6 = (RadioGroup) findViewById(R.id.depsorualtiradiogrup);
        final RadioGroup depRdGroup_7 = (RadioGroup) findViewById(R.id.depsoruyediradiogrup);

        Button depGonderButonu = (Button) findViewById(R.id.depgonderbuton);

        depGonderButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int depansId_1 = depRdGroup_1.getCheckedRadioButtonId();
                RadioButton depansRdButton_1 = (RadioButton) findViewById(depansId_1);
                int depansId_2 = depRdGroup_2.getCheckedRadioButtonId();
                RadioButton depansRdButton_2 = (RadioButton) findViewById(depansId_2);
                int depansId_3 = depRdGroup_3.getCheckedRadioButtonId();
                RadioButton depansRdButton_3 = (RadioButton) findViewById(depansId_3);
                int depansId_4 = depRdGroup_4.getCheckedRadioButtonId();
                RadioButton depansRdButton_4 = (RadioButton) findViewById(depansId_4);
                int depansId_5 = depRdGroup_5.getCheckedRadioButtonId();
                RadioButton depansRdButton_5 = (RadioButton) findViewById(depansId_5);
                int depansId_6 = depRdGroup_6.getCheckedRadioButtonId();
                RadioButton depansRdButton_6 = (RadioButton) findViewById(depansId_6);
                int depansId_7 = depRdGroup_7.getCheckedRadioButtonId();
                RadioButton depansRdButton_7 = (RadioButton) findViewById(depansId_7);

                String depCevap_1 = depansRdButton_1.getText().toString();
                String depCevap_2 = depansRdButton_2.getText().toString();
                String depCevap_3 = depansRdButton_3.getText().toString();
                String depCevap_4 = depansRdButton_4.getText().toString();
                String depCevap_5 = depansRdButton_5.getText().toString();
                String depCevap_6 = depansRdButton_6.getText().toString();
                String depCevap_7 = depansRdButton_7.getText().toString();
                String user_id = mAuth.getCurrentUser().getUid();



                databaseReference = FirebaseDatabase.getInstance().getReference().child(user_id).child("Depresyon Testi Cevapları");
                HashMap<String, String> depans = new HashMap<>();
                depans.put("Cevap 1: ", depCevap_1);
                depans.put("Cevap 2: ", depCevap_2);
                depans.put("Cevap 3: ", depCevap_3);
                depans.put("Cevap 4: ", depCevap_4);
                depans.put("Cevap 5: ", depCevap_5);
                depans.put("Cevap 6: ", depCevap_6);
                depans.put("Cevap 7: ", depCevap_7);

                databaseReference.setValue(depans).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Kayıt başarılı, sonraki test ile devam ediniz.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Başarısız" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void strTestiSend() {

        final RadioGroup strRdGroup_1 = (RadioGroup) findViewById(R.id.stsorubirradiogrup);
        final RadioGroup strRdGroup_2 = (RadioGroup) findViewById(R.id.stsoruikiradiogrup);
        final RadioGroup strRdGroup_3 = (RadioGroup) findViewById(R.id.stsoruucradiogrup);
        final RadioGroup strRdGroup_4 = (RadioGroup) findViewById(R.id.stsorudortradiogrup);
        final RadioGroup strRdGroup_5 = (RadioGroup) findViewById(R.id.stsorubesradiogrup);
        final RadioGroup strRdGroup_6 = (RadioGroup) findViewById(R.id.stsorualtiradiogrup);
        final RadioGroup strRdGroup_7 = (RadioGroup) findViewById(R.id.stsoruyediradiogrup);

        Button strGonderButonu = (Button) findViewById(R.id.strgonderbuton);

        strGonderButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int stransId_1 = strRdGroup_1.getCheckedRadioButtonId();
                RadioButton stransRdButton_1 = (RadioButton) findViewById(stransId_1);
                int stransId_2 = strRdGroup_2.getCheckedRadioButtonId();
                RadioButton stransRdButton_2 = (RadioButton) findViewById(stransId_2);
                int stransId_3 = strRdGroup_3.getCheckedRadioButtonId();
                RadioButton stransRdButton_3 = (RadioButton) findViewById(stransId_3);
                int stransId_4 = strRdGroup_4.getCheckedRadioButtonId();
                RadioButton stransRdButton_4 = (RadioButton) findViewById(stransId_4);
                int stransId_5 = strRdGroup_5.getCheckedRadioButtonId();
                RadioButton stransRdButton_5 = (RadioButton) findViewById(stransId_5);
                int stransId_6 = strRdGroup_6.getCheckedRadioButtonId();
                RadioButton stransRdButton_6 = (RadioButton) findViewById(stransId_6);
                int stransId_7 = strRdGroup_7.getCheckedRadioButtonId();
                RadioButton stransRdButton_7 = (RadioButton) findViewById(stransId_7);


                String strCevap_1 = stransRdButton_1.getText().toString();
                String strCevap_2 = stransRdButton_2.getText().toString();
                String strCevap_3 = stransRdButton_3.getText().toString();
                String strCevap_4 = stransRdButton_4.getText().toString();
                String strCevap_5 = stransRdButton_5.getText().toString();
                String strCevap_6 = stransRdButton_6.getText().toString();
                String strCevap_7 = stransRdButton_7.getText().toString();



                String user_id = mAuth.getCurrentUser().getUid();
                databaseReference = FirebaseDatabase.getInstance().getReference().child(user_id).child("Stres Testi Cevapları");
                HashMap<String, String> strans = new HashMap<>();
                strans.put("Cevap 1: ", strCevap_1);
                strans.put("Cevap 2: ", strCevap_2);
                strans.put("Cevap 3: ", strCevap_3);
                strans.put("Cevap 4: ", strCevap_4);
                strans.put("Cevap 5: ", strCevap_5);
                strans.put("Cevap 6: ", strCevap_6);
                strans.put("Cevap 7: ", strCevap_7);

                databaseReference.setValue(strans).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Kayıt başarılı sonraki test ile devam ediniz.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Kayıt başarısız," + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }


    public void anksiyeteTestiSend() {

        final RadioGroup ankRdGroup_1 = (RadioGroup) findViewById(R.id.anksorubirradiogrup);
        final RadioGroup ankRdGroup_2 = (RadioGroup) findViewById(R.id.anksoruikiradiogrup);
        final RadioGroup ankRdGroup_3 = (RadioGroup) findViewById(R.id.anksoruucradiogrup);
        final RadioGroup ankRdGroup_4 = (RadioGroup) findViewById(R.id.anksorudortradiogrup);
        final RadioGroup ankRdGroup_5 = (RadioGroup) findViewById(R.id.anksorubesradiogrup);
        final RadioGroup ankRdGroup_6 = (RadioGroup) findViewById(R.id.anksorualtiradiogrup);
        final RadioGroup ankRdGroup_7 = (RadioGroup) findViewById(R.id.anksoruyediradiogrup);

        Button ankGonderButon = (Button) findViewById(R.id.ankgonderbuton);

        ankGonderButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ankansId_1 = ankRdGroup_1.getCheckedRadioButtonId();
                RadioButton ankansRdButton_1 = (RadioButton) findViewById(ankansId_1);
                int ankansId_2 = ankRdGroup_2.getCheckedRadioButtonId();
                RadioButton ankansRdButton_2 = (RadioButton) findViewById(ankansId_2);
                int ankansId_3 = ankRdGroup_3.getCheckedRadioButtonId();
                RadioButton ankansRdButton_3 = (RadioButton) findViewById(ankansId_3);
                int ankansId_4 = ankRdGroup_4.getCheckedRadioButtonId();
                RadioButton ankansRdButton_4 = (RadioButton) findViewById(ankansId_4);
                int ankansId_5 = ankRdGroup_5.getCheckedRadioButtonId();
                RadioButton ankansRdButton_5 = (RadioButton) findViewById(ankansId_5);
                int ankansId_6 = ankRdGroup_6.getCheckedRadioButtonId();
                RadioButton ankansRdButton_6 = (RadioButton) findViewById(ankansId_6);
                int ankansId_7 = ankRdGroup_7.getCheckedRadioButtonId();
                RadioButton ankansRdButton_7 = (RadioButton) findViewById(ankansId_7);

                String ankCevap_1 = ankansRdButton_1.getText().toString();
                String ankCevap_2 = ankansRdButton_2.getText().toString();
                String ankCevap_3 = ankansRdButton_3.getText().toString();
                String ankCevap_4 = ankansRdButton_4.getText().toString();
                String ankCevap_5 = ankansRdButton_5.getText().toString();
                String ankCevap_6 = ankansRdButton_6.getText().toString();
                String ankCevap_7 = ankansRdButton_7.getText().toString();


                String user_id = mAuth.getCurrentUser().getUid();
                databaseReference = FirebaseDatabase.getInstance().getReference().child(user_id).child("Anksiyete Testi Cevapları");
                HashMap<String, String> ankans = new HashMap<>();
                ankans.put("Cevap 1: ", ankCevap_1);
                ankans.put("Cevap 2: ", ankCevap_2);
                ankans.put("Cevap 3: ", ankCevap_3);
                ankans.put("Cevap 4: ", ankCevap_4);
                ankans.put("Cevap 5: ", ankCevap_5);
                ankans.put("Cevap 6: ", ankCevap_6);
                ankans.put("Cevap 7: ", ankCevap_7);

                databaseReference.setValue(ankans).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            mAuth.signOut();
                            Toast.makeText(MainActivity.this, "Tüm testler tamamlandı.", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(MainActivity.this, "Başarısız," + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}
