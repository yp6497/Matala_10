package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText ed;
    TextView t;
    String st, s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed = findViewById(R.id.ed);
        t = findViewById(R.id.t);

        try {
            FileInputStream fis = openFileInput("test.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            st = br.readLine();
            while (st != null) {
                sb.append(st + '\n');
                st = br.readLine();
            }
            s = sb.toString(); //
            isr.close();
            t.setText(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveB(View view) {

        try {
            FileOutputStream fos = openFileOutput("test.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            st = s + ed.getText().toString();
            s = st;
            bw.write(st);
            bw.close();
            t.setText(st);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetB(View view) {

        ed.setText("");
        t.setText("");
    }

    public void exitB(View view) {

        try {
            FileOutputStream fos = openFileOutput("test.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            st = st + ed.getText().toString();
            //s=s+st;
            bw.write(st);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        finish();
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String st = item.getTitle().toString();
        if (st.endsWith("Credits")) {
            Intent si = new Intent(this, creditsActivity.class);
            startActivity(si);
        }
        return true;
    }

     */
}