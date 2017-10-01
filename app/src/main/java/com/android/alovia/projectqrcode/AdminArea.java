package com.android.alovia.projectqrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AdminArea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_area);

        final ImageView imageView = (ImageView) findViewById(R.id.img_View);
        Bitmap bitmap = getIntent().getParcelableExtra("pic");
        imageView.setImageBitmap(bitmap);


    }

    public void on_Register(View view){
        Intent intent = new Intent(AdminArea.this, Register.class);
        startActivity(intent);
        finish();
    }

    public void btnA_genqrcode(View view){
        Intent intent = new Intent(AdminArea.this, Generatecode.class);
        startActivity(intent);
        finish();
    }

    public void btnA_Scanqrcode(View view){
        Intent intent = new Intent(AdminArea.this, Backgroundscan.class);
        startActivity(intent);
        finish();
    }

    public void on_Aback(View view){
        Intent intent = new Intent(AdminArea.this, Login.class);
        startActivity(intent);
        finish();
    }
}
