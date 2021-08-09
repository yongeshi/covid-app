package com.example.myapplication.ui.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.ui.map.userLocation;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface locationDao{

    @Query("Select * from locations WHERE date = :date")
    public List<userLocation> selectLocationByDay(String date);

    @Insert
    void insertLocation(userLocation location);

    //delete data older than two weeks
    @Delete
    void deleteLocations(userLocation... locations);

    @Query("Select * from locations WHERE date < :date")
    List<userLocation> selectLocationbyWeek(String date);

    @Query("Select * from locations")
    List<userLocation> selectAll();

    @Query("Select * from locations where id=(SELECT max(id) from locations)")
    userLocation selectLast();

}
