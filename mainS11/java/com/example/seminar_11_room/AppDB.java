package com.example.seminar_11_room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Angajat.class}, version = 3)
public abstract class AppDB extends RoomDatabase {
    public abstract UserDAO userDao();
}