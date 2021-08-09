package com.example.myapplication.ui.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.ui.map.userLocation;


@Database(entities = {userLocation.class}, version = 1, exportSchema = false)
public abstract class locationDatabase extends RoomDatabase {
    private static final String LOG_TAG = locationDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "locations";
    private static locationDatabase sInstance;

    public static locationDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        locationDatabase.class, locationDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract locationDao locationDao();
}
