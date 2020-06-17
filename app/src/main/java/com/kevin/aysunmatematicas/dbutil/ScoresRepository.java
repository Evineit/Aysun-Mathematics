package com.kevin.aysunmatematicas.dbutil;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ScoresRepository {
    private ScoresDao mScoreDao;
    private LiveData<List<Score>> mAllScores;

    ScoresRepository(Application application) {
        ScoresRoomDatabase db = ScoresRoomDatabase.getDatabase(application);
        mScoreDao = db.scoresDao();
        mAllScores = mScoreDao.getOrderedPoints();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Score>> getAllWords() {
        return mAllScores;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(final Score score) {
        try {
            ScoresRoomDatabase.databaseWriteExecutor.submit(() -> {
                mScoreDao.insert(score);
            }).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    int getLastId() {
        return mScoreDao.getLastId();
    }
    void update(Score score){
        mScoreDao.update(score);
    }

    public void deleteAll() {
        ScoresRoomDatabase.databaseWriteExecutor.execute(() -> {
            mScoreDao.deleteAll();
        });
    }
}
