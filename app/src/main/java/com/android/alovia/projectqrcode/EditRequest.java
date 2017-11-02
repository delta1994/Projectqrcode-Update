package com.android.alovia.projectqrcode;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alovia on 7/9/2560.
 */

public class EditRequest extends StringRequest {

    private static final String EDIT_REQUEST_URL = "http://rattaphum.rmutsv.ac.th/durable/Edit.php";
    private Map<String, String> params;
    public EditRequest(String enumber, String ename, String elocation, String eprice, String edate, Response.Listener<String> Listener){
        super(Request.Method.POST, EDIT_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put("number", enumber);
        params.put("name", ename);
        params.put("location", elocation);
        params.put("price", eprice);
        params.put("date", edate);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }

}


