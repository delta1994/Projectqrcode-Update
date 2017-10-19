package com.android.alovia.projectqrcode;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONException;
import org.json.JSONObject;

public class Backgroundscan2 extends AppCompatActivity {

    Button scanner;
    TextView result;
    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgroundscan2);
        scanner = (Button) findViewById(R.id.btnU_Scan);
        result = (TextView) findViewById(R.id.tvUresult);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Backgroundscan2.this, Scanqrcode2.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null){
                final Barcode barcode = data.getParcelableExtra("barcode2");
                final Button btnSearch = (Button) findViewById(R.id.btnU_Search);
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

                                                Intent intent = new Intent(Backgroundscan2.this, Editdata2.class);

                                                intent.putExtra("number", getText2);
                                                intent.putExtra("name", getText3);
                                                intent.putExtra("location", getText4);
                                                intent.putExtra("price", getText5);
                                                intent.putExtra("date", getText6);
                                                intent.putExtra("img", getText7);
                                                Backgroundscan2.this.startActivity(intent);

                                                AlertDialog.Builder builder = new AlertDialog.Builder(Backgroundscan2.this);
                                                builder.setMessage("ค้าหา สำเร็จ")
                                                        .setNegativeButton("OK", null)
                                                        .create()
                                                        .show();
                                            }
                                            else {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(Backgroundscan2.this);
                                                builder.setMessage("ค้นหา ไม่สำเร็จ")
                                                        .setNegativeButton("Retry", null)
                                                        .create()
                                                        .show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                Backgroundsearch2 backgroundsearch2 = new Backgroundsearch2(getText, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(Backgroundscan2.this);
                                queue.add(backgroundsearch2);
                            }
                        });
                    }
                });
            }
        }
    }
    public void on_BBback(View view){
        Intent intent32 = new Intent(Backgroundscan2.this, UserArea.class);
        startActivity(intent32);
        finish();
    }
}
