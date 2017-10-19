package com.android.alovia.projectqrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UserArea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final ImageView imageView = (ImageView) findViewById(R.id.img_View2);
        Bitmap bitmap = getIntent().getParcelableExtra("pic2");
        imageView.setImageBitmap(bitmap);
    }

    public void btnA_Scanqrcode(View view){
        Intent intent = new Intent(UserArea.this, Backgroundscan2.class);
        startActivity(intent);
        finish();
    }

    public void on_Uback(View view){
        Intent intent = new Intent(UserArea.this, Login.class);
        startActivity(intent);
        finish();
    }

}
