package com.kevin.aysunmatematicas.dbutil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Score.class},version = 1,exportSchema = false)
public abstract class ScoresRoomDatabase extends RoomDatabase {
    public abstract ScoresDao scoresDao();
    private static volatile ScoresRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static ScoresRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (ScoresRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ScoresRoomDatabase.class,"Scores_database")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

//            // If you want to keep data through app restarts,
//            // comment out the following block
//            databaseWriteExecutor.execute(() -> {
//                // Populate the database in the background.
//                // If you want to start with more words, just add them.
////                ScoresDao dao = INSTANCE.scoresDao();
////                dao.deleteAll();
////
////                Score score = new Score(1);
////                dao.insert(score);
////                score = new Score(2);
////                dao.insert(score);
//            });
        }
    };
}

