package com.tubes.tugasbesarakb.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tubes.tugasbesarakb.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements OnMapReadyCallback, LocationListener{

    private GoogleMap mMap;
    private FragmentHomeBinding binding;

    private DatabaseReference reference;
    private LocationManager manager;

    private final int MIN_TIME = 1000; // 1 detik
    private final int MIN_DISTANCE = 1; // 1 meter

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        reference = FirebaseDatabase.getInstance().getReference().child("Maps");
        FirebaseDatabase.getInstance().getReference().setValue("This is tracker app");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
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
        mMap.addMarker(new MarkerOptions().position(lokasiku).title("Marker in Lembang"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasiku,17));
        googleMap.addMarker(options);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if(location != null){
            saveLocation(location);
        }else{

        }
    }

    private void saveLocation(Location location) {
        reference.setValue(location);
    }

    @Override
    public void onStatusChanged(String provider,int status, Bundle extras){

    }

    @Override
    public void onProviderEnabled(String provider){

    }

    @Override
    public void onProviderDisabled(String provider){

    }
}