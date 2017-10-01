package com.android.alovia.projectqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etUsername = (EditText) findViewById(R.id.etR_username);
        final EditText etPassword = (EditText) findViewById(R.id.etR_password);
        final EditText etName = (EditText) findViewById(R.id.etR_name);
        final EditText etSurname = (EditText) findViewById(R.id.etR_sername);
        final EditText etEmail = (EditText) findViewById(R.id.etR_email);
        final Button btnRegister = (Button) findViewById(R.id.btnR_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String STname = etName.getText().toString();
                final String STusername = etUsername.getText().toString();
                final String STpassword = etPassword.getText().toString();
                final String STsurname = etSurname.getText().toString();
                final String STemail = etEmail.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage("ลงทะเบียน สำเร็จ")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(Register.this, Login.class);
                                Register.this.startActivity(intent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage("ลงทะเบียน ไม่สำเร็จ")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                RegisterRequest registerRequest = new RegisterRequest(STusername, STpassword, STname, STsurname, STemail, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Register.this);
                queue.add(registerRequest);
            }
        });

    }
    public void on_Rback(View view){
        Intent intent21112 = new Intent(Register.this, AdminArea.class);
        startActivity(intent21112);
        finish();
    }
}