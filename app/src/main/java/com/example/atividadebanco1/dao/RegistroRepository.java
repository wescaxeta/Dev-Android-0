package com.example.atividadebanco1.dao;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.atividadebanco1.modelo.Registro;

import java.util.List;

public class RegistroRepository {
    private RegistroDao mRegistroDao;
    private LiveData<List<Registro>> mAllRegistros;

    public RegistroRepository(Application application) {
        RegistroDatabase db = RegistroDatabase.getDatabase(application);
        mRegistroDao = db.registroDao();
        mAllRegistros = mRegistroDao.getAlphabetizedNomes();
    }

    public LiveData<List<Registro>> getAll() {
        return mAllRegistros;
    }

    public void insertAll(Registro... registro) {
        RegistroDatabase.databaseWriteExecutor.execute(() -> {
            mRegistroDao.insertAll(registro);
        });
    }

    public void delete(Registro registro) {
        RegistroDatabase.databaseWriteExecutor.execute(() -> {
            mRegistroDao.delete(registro);
        });
    }

    public void update(Registro registro) {
        RegistroDatabase.databaseWriteExecutor.execute(() -> {
            mRegistroDao.update(registro);
        });
    }
}
