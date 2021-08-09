//package com.example.myapplication.ui.map;
//
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//
//import com.example.myapplication.R;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import kotlin.Metadata;
//import kotlin.TypeCastException;
//import kotlin.jvm.internal.Intrinsics;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//@Metadata(
//        mv = {1, 1, 16},
//        bv = {1, 0, 3},
//        k = 1,
//        d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0005H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\f"},
//        d2 = {"Lcom/example/tester/MapsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "()V", "mMap", "Lcom/google/android/gms/maps/GoogleMap;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMapReady", "googleMap", "app_debug"}
//)
//public final class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
//    private GoogleMap mMap;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        this.setContentView(R.layout.activity_maps);
//        Fragment var10000 = this.getSupportFragmentManager().findFragmentById(R.id.map);
//        if (var10000 == null) {
//            throw new TypeCastException("null cannot be cast to non-null type com.google.android.gms.maps.SupportMapFragment");
//        } else {
//            SupportMapFragment mapFragment = (SupportMapFragment)var10000;
//            mapFragment.getMapAsync((OnMapReadyCallback)this);
//        }
//    }
//
//    public void onMapReady(@NotNull GoogleMap googleMap) {
//        Intrinsics.checkParameterIsNotNull(googleMap, "googleMap");
//        this.mMap = googleMap;
//        LatLng sydney = new LatLng(-34.0D, 151.0D);
//        GoogleMap var10000 = this.mMap;
//        if (var10000 == null) {
//            Intrinsics.throwUninitializedPropertyAccessException("mMap");
//        }
//
//        var10000.addMarker((new MarkerOptions()).position(sydney).title("Marker in LA"));
//        var10000 = this.mMap;
//        if (var10000 == null) {
//            Intrinsics.throwUninitializedPropertyAccessException("mMap");
//        }
//
//        var10000.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
//}