package com.android.alovia.projectqrcode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.io.IOException;

import static android.R.attr.bitmap;
import static com.android.alovia.projectqrcode.R.id.imageView;

public class Generatecode extends AppCompatActivity {
    private static String myBase64Image;
    EditText Getnumber1, Getnumber2, Getnumber3, Getnumber4,Getnumber5;
    private Button button1;
    private int LOAD_IMAGE_RESULTS = 1;
    public  Bitmap bitmapasd, bitmapasdnew, resized;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generatecode);

        final EditText Getnumber6 = (EditText) findViewById(R.id.etGen_number);
        final EditText Getnumber7 = (EditText) findViewById(R.id.etGen_date);
        final EditText Getnumber8 = (EditText) findViewById(R.id.etGen_locate);
        final EditText Getnumber9 = (EditText) findViewById(R.id.etGen_detail);
        final EditText Getnumber10 = (EditText) findViewById(R.id.etGen_status);
        final Button btnAdddata = (Button) findViewById(R.id.btnGen_Add);
        final Button btngetimg = (Button) findViewById(R.id.btnG_Getimg);
        imageView =(ImageView)findViewById(R.id.imgG_img232);
        btngetimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, LOAD_IMAGE_RESULTS);
            }
        });
        btnAdddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Gnumber = Getnumber6.getText().toString();
                final String Gname = Getnumber7.getText().toString();
                final String Glocation = Getnumber8.getText().toString();
                final String Gprice = Getnumber9.getText().toString();
                final String Gdate = Getnumber10.getText().toString();
                final String Gimg = myBase64Image;
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(Generatecode.this);
                                builder.setMessage("เพิ่มเข้าฐานข้อมูล สำเร็จ")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Generatecode.this);
                                builder.setMessage("เพิ่มเข้าฐานข้อมูล ไม่สำเร็จ")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Backgroundgencode backgroundgencode = new Backgroundgencode(Gnumber, Gname, Glocation, Gprice, Gdate, Gimg, responseListener);
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
        @Override
        protected void onActivityResult(int reqCode, int resultCode, Intent data) {
            super.onActivityResult(reqCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    bitmapasd = BitmapFactory.decodeStream(imageStream);
                    bitmapasdnew = bitmapasd;
                    resized = Bitmap.createScaledBitmap(bitmapasdnew, 200, 200, true);
                    imageView.setImageBitmap(resized);
                    callencode(resized);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(Generatecode.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(Generatecode.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
            }
        }
public static void callencode(Bitmap bitmap){
    myBase64Image = encodeToBase64(bitmap, Bitmap.CompressFormat.JPEG, 100);
}
   public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }
}

