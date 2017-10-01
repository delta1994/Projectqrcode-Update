package com.android.alovia.projectqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Editdata extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata);

        final TextView T1 = (TextView) findViewById(R.id.textVEA1);
        final EditText T2 = (EditText) findViewById(R.id.etE_name);
        final EditText T3 = (EditText) findViewById(R.id.etE_location);
        final EditText T4 = (EditText) findViewById(R.id.etE_price);
        final EditText T5 = (EditText) findViewById(R.id.etE_date);
        final Button btnE_Edit = (Button) findViewById(R.id.btnE_edit);


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


        btnE_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String enumber = T1.getText().toString();
                final String ename = T2.getText().toString();
                final String elocation = T3.getText().toString();
                final String eprice = T4.getText().toString();
                final String edate = T5.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(Editdata.this, AdminArea.class);

                                Editdata.this.startActivity(intent);

                                AlertDialog.Builder builder = new AlertDialog.Builder(Editdata.this);
                                builder.setMessage("แก้ไข สำเร็จ")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Editdata.this);
                                builder.setMessage("แก้ไข ไม่สำเร็จ")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                EditRequest editRequest = new EditRequest(enumber, ename, elocation, eprice, edate, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Editdata.this);
                queue.add(editRequest);
            }
        });
    }
    public void on_Eback(View view){
        Intent intent231345 = new Intent(Editdata.this, AdminArea.class);
        startActivity(intent231345);
        finish();
    }
}


