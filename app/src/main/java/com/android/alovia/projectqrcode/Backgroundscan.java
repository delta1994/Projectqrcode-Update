package com.android.alovia.projectqrcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONException;
import org.json.JSONObject;

public class Backgroundscan extends AppCompatActivity {
    Button scanner;
    TextView result;
    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgroundscan);
        scanner = (Button) findViewById(R.id.btn_Scan);
        result = (TextView) findViewById(R.id.tvresult);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Backgroundscan.this, Scanqrcode.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null){
                final Barcode barcode = data.getParcelableExtra("barcode");
                final Button btnSearch = (Button) findViewById(R.id.btnB_Search);
                result.post(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(barcode.displayValue);
                        btnSearch.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final String getText = result.getText().toString();
                                Response.Listener<String> responseListener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonResponse = new JSONObject(response);
                                            boolean success = jsonResponse.getBoolean("success");
                                            if (success){
                                                String getText2 = jsonResponse.getString("data_number");
                                                String getText3 = jsonResponse.getString("data_name");
                                                String getText4 = jsonResponse.getString("data_location");
                                                String getText5 = jsonResponse.getString("data_price");
                                                String getText6 = jsonResponse.getString("data_date");
                                                String getText7 = jsonResponse.getString("data_img");

                                                Intent intent = new Intent(Backgroundscan.this, Editdata.class);
                                                intent.putExtra("number", getText2);
                                                intent.putExtra("name", getText3);
                                                intent.putExtra("location", getText4);
                                                intent.putExtra("price", getText5);
                                                intent.putExtra("date", getText6);
                                                intent.putExtra("img", getText7);

                                                Backgroundscan.this.startActivity(intent);

                                                AlertDialog.Builder builder = new AlertDialog.Builder(Backgroundscan.this);
                                                builder.setMessage("ค้นหาสำเร็จ")
                                                        .setNegativeButton("OK", null)
                                                        .create()
                                                        .show();
                                            }
                                            else {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(Backgroundscan.this);
                                                builder.setMessage("ค้นหาไม่สำเร็จ")
                                                        .setNegativeButton("Retry", null)
                                                        .create()
                                                        .show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                Backgroundsearch backgroundsearch = new Backgroundsearch(getText, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(Backgroundscan.this);
                                queue.add(backgroundsearch);
                            }
                        });
                    }
                });
            }
        }
    }
    public void on_Bback(View view){
        Intent intent32 = new Intent(Backgroundscan.this, AdminArea.class);
        startActivity(intent32);
        finish();
    }
}
