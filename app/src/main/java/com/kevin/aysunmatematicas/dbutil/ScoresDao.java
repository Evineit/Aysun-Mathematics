package com.kevin.aysunmatematicas.dbutil;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ScoresDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Score score);

    @Update
    void update(Score score);

    @Query("DELETE FROM scores_table")
    void deleteAll();

    @Query("SELECT id from scores_table order by id DESC limit 1")
    int getLastId();

    @Query("SELECT puntos from scores_table order by id DESC limit 1")
    int getPoints();

    @Query("SELECT last_insert_rowid()")
    int getLastId2();


    @Query("SELECT * from scores_table ORDER BY id DESC")
    LiveData<List<Score>> getOrderedPoints();
}
