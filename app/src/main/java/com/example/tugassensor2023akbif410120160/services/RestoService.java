package com.example.tugassensor2023akbif410120160.services;
/**
 * NIM: 10120160
 * Nama : Rendi Julianto
 * Kelas : IF-4
 */
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tugassensor2023akbif410120160.models.RestoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestoService {
    public static final String API_KEY = "MdBXtKVQoS1cGT91KqQ0QtGTOzxpqyUt";
    public static final String HTTPS_API_TOMTOM = "https://api.tomtom.com/search/2/categorySearch/RESTAURANT.json?key=" + API_KEY;

    Context context;
    double positionLat;
    double positionLong;



    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(List<RestoModel> restoModels);
    }

    public RestoService(Context context) {
        this.context = context;
    }

    public void getResto(double positionLat, double positionLong, VolleyResponseListener volleyResponseListener) {
        List<RestoModel> restoModels = new ArrayList<>();
        String url = HTTPS_API_TOMTOM+"&lat="+positionLat+"&lon="+positionLong+"&limit=5";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray resto_list = response.getJSONArray("results");

                    for (int i = 0; i < resto_list.length(); i++) {
                        RestoModel one_resto = new RestoModel();
                        JSONObject resto_from_api = (JSONObject) resto_list.get(i);
                        JSONObject resto_poi = (JSONObject) resto_from_api.get("poi");
                        JSONObject resto_address = (JSONObject) resto_from_api.get("address");
                        JSONObject resto_position = (JSONObject) resto_from_api.get("position");
                        one_resto.setName(resto_poi.getString("name"));
                        one_resto.setAddress(resto_address.getString("freeformAddress"));
                        one_resto.setPositionLat(resto_position.getString("lat"));
                        one_resto.setPositionLong(resto_position.getString("lon"));
                        restoModels.add(one_resto);

                    }

                    volleyResponseListener.onResponse(restoModels);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

}

