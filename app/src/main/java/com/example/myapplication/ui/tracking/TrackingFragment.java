package com.example.myapplication.ui.tracking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.database.locationDatabase;
import com.example.myapplication.ui.map.MapFragment;
import com.example.myapplication.ui.map.userLocation;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.lang.Math.sqrt;

public class TrackingFragment extends Fragment {
    TextView calendarDate;
    TextView visitedLocation;
    private locationDatabase lDb;
    List<userLocation> userLocations;

    public TrackingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_tracking, container, false);
        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                showBottomSheetDialog(year, month, dayOfMonth);
            }
        });

        lDb = locationDatabase.getInstance(getContext());
        dbOperationsGetLocations db = new dbOperationsGetLocations("","");
        Thread t = new Thread(db);
        t.start();

        return view;
    }

    private void showBottomSheetDialog(int year, int monthNum, int dayOfMonth) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.bottom_sheet);

        calendarDate = bottomSheetDialog.findViewById(R.id.calendar_date);
        visitedLocation = bottomSheetDialog.findViewById(R.id.visited_location);

        String month;
        switch(monthNum) {
            case 0:
                month = "January";
                break;
            case 1:
                month = "February";
                break;
            case 2:
                month = "March";
                break;
            case 3:
                month = "April";
                break;
            case 4:
                month = "May";
                break;
            case 5:
                month = "June";
                break;
            case 6:
                month = "July";
                break;
            case 7:
                month = "August";
                break;
            case 8:
                month = "September";
                break;
            case 9:
                month = "October";
                break;
            case 10:
                month = "November";
                break;
            case 11:
                month = "December";
                break;
            default:
                month = "Unknown";
        }

        String setDate = String.format("%s %s %s", dayOfMonth, month, year);
        calendarDate.setText(setDate);
        String date = String.format("%d-%02d-%02d", year, monthNum + 1, dayOfMonth);
        String city = getCityFromCoordinates(parseLocationString(date));
        if (city != null) {
            visitedLocation.setText(city);
        } else {
            visitedLocation.setText("No places located");
        }

        bottomSheetDialog.show();
    }

    String parseLocationString(String date) {
        for (int i = 0; i < userLocations.size(); i++) {
            if (userLocations.get(i).getDate().equals(date)) {
                return userLocations.get(i).getLocation();
            }
        }

        return null;
    }

    String getCityFromCoordinates(String coordinates) {
        if (coordinates == null) {
            return null;
        }
        String[] coords = coordinates.split(",");
        String city = null;
        double latitude = Double.parseDouble(coords[0]);
        double longitude = Double.parseDouble(coords[1]);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getContext().getAssets().open("City_Locations.csv"), StandardCharsets.UTF_8));
        } catch (IOException e) {
            //log the exception
        }

        String line = "";
        try {
            line = reader.readLine();
            double shortestDistance = 1000000;
            while ((line = reader.readLine()) != null) {
                String[] lines = line.split(",");
                double distance = getDistance(lines[1], lines[2], latitude, longitude);
                if (distance < shortestDistance) {
                    city = lines[0];
                    shortestDistance = distance;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return city;
    }

    double getDistance(String latitude1, String longitude1, double latitude2, double longitude2) {
        double lat1 = Double.parseDouble(latitude1);
        double long1 = Double.parseDouble(longitude1);

        return sqrt(Math.pow(Math.abs(lat1 - latitude2), 2) + Math.pow(Math.abs(long1 - longitude2), 2));
    }

    private class dbOperations implements Runnable {

        private userLocation location;
        public dbOperations(userLocation userLocation)
        {
            this.location = userLocation;
        }

        public void run()
        {
            userLocation lastLocation = lDb.locationDao().selectLast();
            if(!(lastLocation.latitude < location.latitude + .1 && lastLocation.latitude > location.latitude - .1 &&
                    lastLocation.longitude < location.longitude + .1 && lastLocation.longitude > location.longitude - .1 && Integer.parseInt(lastLocation.time) > Integer.parseInt(location.time) + 30))
            {
                lDb.locationDao().insertLocation(this.location);
            }


        }
    }

    private class dbOperationsGetLocations implements Runnable {
        private String[] dates;

        public dbOperationsGetLocations(String timeStart, String timeEnd)
        {

        }

        public void run()
        {
            userLocations = lDb.locationDao().selectAll();
        }

    }

}