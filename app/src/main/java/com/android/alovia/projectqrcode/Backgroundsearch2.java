package com.android.alovia.projectqrcode;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marksman on 1/10/2560.
 */

public class Backgroundsearch2 extends StringRequest {

    private static final String SEARCH_REQUEST_URL = "http://rattaphum.rmutsv.ac.th/durable/Search2.php";
    private Map<String, String> params;

    public Backgroundsearch2(String getText, Response.Listener<String> Listener) {
        super(Request.Method.POST, SEARCH_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put("getText", getText);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
