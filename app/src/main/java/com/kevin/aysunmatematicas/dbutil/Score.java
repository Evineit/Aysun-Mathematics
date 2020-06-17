package com.kevin.aysunmatematicas.dbutil;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "scores_table")
public class Score {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "puntos")
    public int puntos;

    public Score(int puntos) {
        this.puntos=puntos;
    }
    @Ignore
    public Score(int id, int puntos) {
        this.id = id;
        this.puntos = puntos;
    }
}
