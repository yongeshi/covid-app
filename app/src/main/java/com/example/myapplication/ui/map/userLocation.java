package com.example.myapplication.ui.map;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

import java.time.LocalDate;

@Entity(tableName = "locations")
public class userLocation {

    @PrimaryKey(autoGenerate=true)
    public int id;

    @ColumnInfo(name="time")
    public String time;

    @ColumnInfo(name="latitude")
    public double latitude;

    @ColumnInfo(name="longitude")
    public double longitude;

    @ColumnInfo(name="date")
    public String date;

    public userLocation(String date, String time, double latitude, double longitude)
    {
        this.time = time;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String toString()
    {
        return(time + " " + date + " " + latitude + " " + longitude);
    }

    public String getDate() { return date; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    public String getLocation() { return latitude + "," + longitude; }
}