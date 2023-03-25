package com.example.atividadebanco1.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.atividadebanco1.dao.RegistroRepository;
import com.example.atividadebanco1.modelo.Registro;

import java.util.List;

public class RegistroViewModel extends AndroidViewModel {

    private RegistroRepository mRegistroRepository;

    private final LiveData<List<Registro>> mAllRegistros;

    public RegistroViewModel (Application application) {
        super(application);
        mRegistroRepository = new RegistroRepository(application);
        mAllRegistros = mRegistroRepository.getAll();
    }

    public LiveData<List<Registro>> getAll() { return mAllRegistros; }

    public void insertAll(Registro... reg) { mRegistroRepository.insertAll(reg); }

    public void delete(Registro reg) { mRegistroRepository.delete(reg); }

    public void update(Registro reg) { mRegistroRepository.update(reg); }
}