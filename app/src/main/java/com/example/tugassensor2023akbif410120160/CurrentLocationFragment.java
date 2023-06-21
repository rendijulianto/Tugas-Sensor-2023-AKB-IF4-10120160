package com.example.tugassensor2023akbif410120160;
/**
 * NIM: 10120160
 * Nama : Rendi Julianto
 * Kelas : IF-4
 */
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugassensor2023akbif410120160.models.ProfileModel;
import com.example.tugassensor2023akbif410120160.services.ProfileService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentLocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentLocationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FusedLocationProviderClient client;
    private TextView text_country_subdivision,
            text_municipality,text_municipality_subdivision,text_country,
            text_freeform_address,text_coordinate;

    public CurrentLocationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentLocationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentLocationFragment newInstance(String param1, String param2) {
        CurrentLocationFragment fragment = new CurrentLocationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        client = LocationServices.getFusedLocationProviderClient(getContext());
        getCurrentLocation();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_current_location, container, false);
        text_municipality = rootView.findViewById(R.id.text_municipality);
        text_municipality_subdivision = rootView.findViewById(R.id.text_municipality_subdivision);
        text_freeform_address = rootView.findViewById(R.id.text_freeform_address);
        text_coordinate = rootView.findViewById(R.id.text_coordinate);
        text_country = rootView.findViewById(R.id.text_country);
        text_country_subdivision = rootView.findViewById(R.id.text_country_subdivision);

        return rootView;
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){

                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                    final ProfileService profileService = new ProfileService(getContext());
                    profileService.getDetailProfile(location.getLatitude(),location.getLongitude(), new ProfileService.VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(getContext(), "ERROR" + message, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(ProfileModel profileModel) {
                            text_country_subdivision.setText(profileModel.getCountrySubdivision());
                            text_municipality.setText(profileModel.getMunicipality());
                            text_municipality_subdivision.setText(profileModel.getMunicipalitySubdivision());
                            text_country.setText(profileModel.getCountry());
                            text_freeform_address.setText(profileModel.getFreeformAddress());
                            text_coordinate.setText("Lat : "+ location.getLatitude() + " | Long : " + location.getLongitude());
                        }
                    });
                }
            }
        });
    }
}