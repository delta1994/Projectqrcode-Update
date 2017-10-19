package com.android.alovia.projectqrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class Editdata2 extends AppCompatActivity {
    private static Bitmap myBitmapAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata2);

        final TextView T1 = (TextView) findViewById(R.id.textVEU1);
        final TextView T2 = (TextView) findViewById(R.id.textVEU2);
        final TextView T3 = (TextView) findViewById(R.id.textVEU3);
        final TextView T4 = (TextView) findViewById(R.id.textVEU4);
        final TextView T5 = (TextView) findViewById(R.id.textVEU5);
        final ImageView I1 = (ImageView) findViewById(R.id.imgE2_img2);

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

    }

    public void on_EUback(View view){
        Intent intent = new  Intent(Editdata2.this,UserArea.class);
        startActivity(intent);
        finish();
    }
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
