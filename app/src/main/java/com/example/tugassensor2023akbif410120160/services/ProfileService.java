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
import com.example.tugassensor2023akbif410120160.models.ProfileModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileService {
    public static final String API_KEY = "MdBXtKVQoS1cGT91KqQ0QtGTOzxpqyUt";
    public static final String HTTPS_API_TOMTOM = " https://api.tomtom.com/search/2/reverseGeocode/";

    Context context;
    double positionLat;
    double positionLong;

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(ProfileModel profileModel);
    }

    public ProfileService(Context context) {
        this.context = context;
    }

    public void getDetailProfile(double positionLat, double positionLong, ProfileService.VolleyResponseListener volleyResponseListener) {
        String url = HTTPS_API_TOMTOM+positionLat+","+positionLong+"?radius=100&key="+API_KEY;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ProfileModel profileModel = new ProfileModel();
                    JSONArray addresses = response.getJSONArray("addresses");
                    JSONObject address = (JSONObject) addresses.get(0);
                    JSONObject addressDetail = (JSONObject) address.get("address");
                    profileModel.setCountrySubdivision(addressDetail.getString("countrySubdivision"));
                    profileModel.setMunicipality(addressDetail.getString("municipality"));
                    profileModel.setMunicipalitySubdivision(addressDetail.getString("municipalitySubdivision"));
                    profileModel.setCountry(addressDetail.getString("country"));
                    profileModel.setFreeformAddress(addressDetail.getString("freeformAddress"));
                    volleyResponseListener.onResponse(profileModel);
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
