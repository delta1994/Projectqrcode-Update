package com.android.alovia.projectqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button btnLAdmin = findViewById(R.id.btnL_Admin);
        final Button btnLlogin = findViewById(R.id.btnLaa_User);
        btnLlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent user = new Intent(Login.this, LoginUser.class);
                startActivity(user);
                finish();
            }
        });

        btnLAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent admin = new Intent(Login.this, LoginAdmin.class);
                startActivity(admin);
                finish();
            }
        });


    }
}
