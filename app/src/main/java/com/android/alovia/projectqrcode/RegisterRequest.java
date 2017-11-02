package com.android.alovia.projectqrcode;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alovia on 7/9/2560.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://rattaphum.rmutsv.ac.th/durable/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String STusername, String STpassword, String STname, String STsurname, String STemail, Response.Listener<String> Listener){
        super(Method.POST, REGISTER_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put("username", STusername);
        params.put("password", STpassword);
        params.put("name", STname);
        params.put("surname", STsurname);
        params.put("email", STemail);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }

}

