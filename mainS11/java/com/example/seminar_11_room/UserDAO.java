package com.example.seminar_11_room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Angajat> angajati);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Angajat angajat);

    @Delete
    void delete(Angajat angajat);

    @Query("SELECT * FROM ANGAJATI")
    List<Angajat> getAll();

    @Query("delete from ANGAJATI")
    void deleteAll();

    @Update
    void update(Angajat angajat);

    @Query("SELECT * FROM ANGAJATI ORDER BY COD")
    List<Angajat> getCodSorted();

    @Query("SELECT * FROM ANGAJATI ORDER BY VARSTA DESC")
    List<Angajat> getAgeSorted();

    @Query("SELECT * FROM ANGAJATI WHERE VARSTA < :maxAge ORDER BY VARSTA")
    List<Angajat> getAgeFiltered(int maxAge);
}