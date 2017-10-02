package com.android.alovia.projectqrcode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

public class Generatecode extends AppCompatActivity {
    EditText Getnumber1, Getnumber2, Getnumber3, Getnumber4,Getnumber5;
    private Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generatecode);

        final EditText Getnumber6 = (EditText) findViewById(R.id.etGen_number);
        final EditText Getnumber7 = (EditText) findViewById(R.id.etGen_date);
        final EditText Getnumber8 = (EditText) findViewById(R.id.etGen_locate);
        final EditText Getnumber9 = (EditText) findViewById(R.id.etGen_detail);
        final EditText Getnumber10 = (EditText) findViewById(R.id.etGen_status);
        final Button btnAdddata = (Button) this.findViewById(R.id.btnGen_Add);
        btnAdddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Gnumber = Getnumber6.getText().toString();
                final String Gdate = Getnumber7.getText().toString();
                final String Glocate = Getnumber8.getText().toString();
                final String Gdetail = Getnumber9.getText().toString();
                final String Gstatus = Getnumber10.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(Generatecode.this);
                                builder.setMessage("เพิ่มข้อมูล สำเร็จ")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Generatecode.this);
                                builder.setMessage("เพิ่มข้อมูล ไม่สำเร็จ")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Backgroundgencode backgroundgencode = new Backgroundgencode(Gnumber, Gdate, Glocate, Gdetail, Gstatus, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Generatecode.this);
                queue.add(backgroundgencode);
            }
        });

        final Context context = this;
         Getnumber1 = (EditText) this.findViewById(R.id.etGen_number);
         Getnumber2 = (EditText) this.findViewById(R.id.etGen_date);
         Getnumber3 = (EditText) this.findViewById(R.id.etGen_locate);
         Getnumber4 = (EditText) this.findViewById(R.id.etGen_detail);
         Getnumber5 = (EditText) this.findViewById(R.id.etGen_status);
         button1 = (Button) this.findViewById(R.id.btnGen_Genqrcode);
         button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text20r = Getnumber1.getText().toString();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text20r, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    Intent intent = new Intent(context, AdminArea.class);
                    intent.putExtra("pic",bitmap);
                    context.startActivity(intent);
                }catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void on_Gback(View view){
        Intent intent22233 = new Intent(Generatecode.this,AdminArea.class);
        startActivity(intent22233);
        finish();
    }

}

