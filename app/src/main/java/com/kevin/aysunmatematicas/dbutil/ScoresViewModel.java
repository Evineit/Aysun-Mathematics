package com.kevin.aysunmatematicas.dbutil;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ScoresViewModel extends AndroidViewModel {

    private ScoresRepository mRepository;
    private LiveData<List<Score>> mAllScores;

    public ScoresViewModel(Application application) {
        super(application);
        mRepository = new ScoresRepository(application);
        mAllScores = mRepository.getAllWords();
    }

    public LiveData<List<Score>> getAllScores() {
        return mAllScores;
    }

    public void insert(Score score) {
        mRepository.insert(score);
    }
    public int getLastId() {
        return mRepository.getLastId();
    }
    public void update(Score score){
        mRepository.update(score);
    }
    public void deleteAll(){
        mRepository.deleteAll();
    }
}
