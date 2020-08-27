package com.alaaapps.jo3t;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class RestaurantFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    private static final String TAG = "LocationFragment";
    private static final LatLng TUNISIA = new LatLng(34.010148, 9.287359);
    FloatingActionButton fab;
    GoogleMap googleMap;
    double latitude;
    double longitude;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_restaurant, container, false);
        final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fab = v.findViewById(R.id.fab);

        return v;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(getActivity(),R.raw.map);
        googleMap.setMapStyle(mapStyleOptions);
        getPos();
        if(latitude!=0.0d && longitude!=0.0d ){
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 15));
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPos();
                if(latitude!=0.0d && longitude!=0.0d ){
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 15));
                }
            }
        });
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void getPos() {
        if(getActivity()!=null){
            Log.d("Activity","Not null");
        }
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if(lm!=null){
            Log.d("Location Manager","Not null");
        }

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat
                    .requestPermissions(
                            getActivity(),
                            new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                            1);
            return ;
        }
        List<String> providers = lm.getProviders(true);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        for (String provider : providers) {
            Location l = lm.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (location == null || l.getAccuracy() < location.getAccuracy()) {
                // Found best last known location: %s", l);
                location = l;
            }
        }
        if(location!=null){
            Log.d("Location","Not null");
        }else{
            Log.d("Location","null");
        }

        if(location !=null){
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
        else {
            Toast
                    .makeText(getActivity(),
                            "Unknown error!",
                            Toast.LENGTH_SHORT)
                    .show();
            new HomeFragment();
        }
        Log.d("POS", "Lat= " + latitude + " Long= " + longitude);

    }


}
