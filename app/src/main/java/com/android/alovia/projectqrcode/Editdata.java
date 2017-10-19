package com.android.alovia.projectqrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Editdata extends AppCompatActivity {
    private static Bitmap myBitmapAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata);

        final TextView T1 = (TextView) findViewById(R.id.tvET_tv112);
        final EditText T2 = (EditText) findViewById(R.id.etET_Ename21);
        final EditText T3 = (EditText) findViewById(R.id.etET_location21);
        final EditText T4 = (EditText) findViewById(R.id.etET_Eprice21);
        final EditText T5 = (EditText) findViewById(R.id.etET_Edate21);
        final ImageView I1 = (ImageView) findViewById(R.id.imgEE_img11);
        final Button btnE_Edit = (Button) findViewById(R.id.btnE_edit21);


        Intent intent = getIntent();
        final String Number = intent.getStringExtra("number");
        final String Name = intent.getStringExtra("name");
        final String Location = intent.getStringExtra("location");
        final String Price = intent.getStringExtra("price");
        final String Date = intent.getStringExtra("date");
        final String Image = intent.getStringExtra("img");
        myBitmapAgain = decodeBase64(Image);

        T1.setText(Number);
        T2.setText(Name);
        T3.setText(Location);
        T4.setText(Price);
        T5.setText(Date);
        I1.setImageBitmap(myBitmapAgain);


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

                                AlertDialog.Builder builder = new AlertDialog.Builder(Editdata.this);
                                builder.setMessage("แก้ไข สำเร็จ")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();

                                Intent intent = new Intent(Editdata.this, AdminArea.class);
                                Editdata.this.startActivity(intent);

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

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}


