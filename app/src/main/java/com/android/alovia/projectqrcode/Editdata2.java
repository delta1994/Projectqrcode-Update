package com.android.alovia.projectqrcode;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Editdata2 extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata2);

        final TextView T1 = (TextView) findViewById(R.id.textVEU1);
        final TextView T2 = (TextView) findViewById(R.id.textVEU2);
        final TextView T3 = (TextView) findViewById(R.id.textVEU3);
        final TextView T4 = (TextView) findViewById(R.id.textVEU4);
        final TextView T5 = (TextView) findViewById(R.id.textVEU5);

        Intent intent = getIntent();
        final String Number = intent.getStringExtra("number");
        final String Name = intent.getStringExtra("name");
        final String Location = intent.getStringExtra("location");
        final String Price = intent.getStringExtra("price");
        final String Date = intent.getStringExtra("date");

        T1.setText(Number);
        T2.setText(Name);
        T3.setText(Location);
        T4.setText(Price);
        T5.setText(Date);

    }

    public void on_EUback(View view){
        Intent intent = new  Intent(Editdata2.this,UserArea.class);
        startActivity(intent);
        finish();
    }
}
