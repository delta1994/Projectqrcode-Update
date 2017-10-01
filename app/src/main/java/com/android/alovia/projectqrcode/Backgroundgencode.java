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
    private static final String REGISTER_REQUEST_URL = "http://jonhslim.pe.hu/pretest/Adddata.php";
    private Map<String, String> params;
    public Backgroundgencode(String Gnumber, String Gdate, String Glocate, String Gdetail, String Gstatus, Response.Listener<String> Listener){
        super(Request.Method.POST, REGISTER_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put("getnumber", Gnumber);
        params.put("getname", Gdate);
        params.put("getlocation", Glocate);
        params.put("getprice", Gdetail);
        params.put("getdate", Gstatus);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }

}

