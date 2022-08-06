package com.tubes.tugasbesarakb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tubes.tugasbesarakb.databinding.FragmentMapsBinding;

public class MapsFragment extends Fragment implements LocationListener {

    private FragmentMapsBinding binding;
    private DatabaseReference reference;
    private LocationManager manager;

    private final int MIN_TIME = 1000; // 1 detik
    private final int MIN_DISTANCE = 1; // 1 meter



    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */



        @Override
        public void onMapReady(GoogleMap googleMap) {

            LatLng lokasiku = new LatLng(-6.839608884201539, 107.6052857179903);
            LatLng wisata1 = new LatLng(-6.833047981365829, 107.60561271093461);
            LatLng wisata2 = new LatLng(-6.848631426564443, 107.62595848133417);
            LatLng wisata3 = new LatLng(-6.843667437340559, 107.62429282954626);
            LatLng wisata4 = new LatLng(-6.841345966300139, 107.62287263311094);
            googleMap.setMapType(googleMap.MAP_TYPE_HYBRID);
            MarkerOptions options = new MarkerOptions().position(lokasiku).title("Lokasi Saat Ini");
            googleMap.addMarker(new MarkerOptions().position(wisata1).title("FarmHouse"));
            googleMap.addMarker(new MarkerOptions().position(wisata2).title("Dago Dream Park"));
            googleMap.addMarker(new MarkerOptions().position(wisata3).title("Sarae Hills"));
            googleMap.addMarker(new MarkerOptions().position(wisata4).title("D`Dieulands"));
            googleMap.addMarker(new MarkerOptions().position(lokasiku).title("Lokasi Saat ini"));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasiku,17));
            googleMap.addMarker(options);


            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference lok = database.getReference("CurrentLocation");
            lok.setValue(lokasiku);
            DatabaseReference wis1 = database.getReference("Farm House");
            wis1.setValue(wisata1);
            DatabaseReference wis2 = database.getReference("Dago Dream Park");
            wis2.setValue(wisata2);
            DatabaseReference wis3 = database.getReference("Sarae Hills");
            wis3.setValue(wisata3);
            DatabaseReference wis4 = database.getReference("D`Dieulands");
            wis4.setValue(wisata4);




        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}