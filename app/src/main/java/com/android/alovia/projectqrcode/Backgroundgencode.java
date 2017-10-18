package com.android.alovia.projectqrcode;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alovia on 7/9/2560.
 */

public class Backgroundgencode extends StringRequest{
    private static final String REGISTER_REQUEST_URL = "http://jonhslim.pe.hu//pretest/Adddata.php";
    private Map<String, String> params;
    public Backgroundgencode(String Gnumber, String Gname, String Glocation, String Gprice, String Gdate,String myBase64Image, Response.Listener<String> Listener){
        super(Request.Method.POST, REGISTER_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put("getnumber", Gnumber);
        params.put("getname", Gname);
        params.put("getlocation", Glocation);
        params.put("getprice", Gprice);
        params.put("getdate", Gdate);
        params.put("getimg", myBase64Image);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }

}

