package com.android.alovia.projectqrcode;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        final EditText etUsername = (EditText) findViewById(R.id.etLU_username);
        final EditText etPassword = (EditText) findViewById(R.id.etLU_password);
        final Button btnULogin = (Button) findViewById(R.id.btnLU_login);

        btnULogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success){
                                String name = jsonResponse.getString("name");
                                String surname = jsonResponse.getString("surname");
                                String email = jsonResponse.getString("email");

                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginUser.this);
                                builder.setMessage("ล็อคอิน สำเร็จ")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(LoginUser.this, UserArea.class);

                                intent.putExtra("name", name);
                                intent.putExtra("username", username);
                                intent.putExtra("surname", surname);
                                intent.putExtra("email", email);

                                LoginUser.this.startActivity(intent);
                                finish();
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginUser.this);
                                builder.setMessage("ล็อคอิน ไม่สำเร็จ")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginUserRequest loginUserRequest = new LoginUserRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginUser.this);
                queue.add(loginUserRequest);
            }
        });
    }

    public void on_UUUback(View view){
        Intent intent555 = new  Intent(LoginUser.this,Login.class);
        startActivity(intent555);
        finish();
    }
}
