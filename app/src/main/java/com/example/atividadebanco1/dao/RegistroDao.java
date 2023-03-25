package com.example.atividadebanco1.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.atividadebanco1.modelo.Registro;

import java.util.List;

@Dao
public interface RegistroDao {

    @Query("SELECT * FROM registro")
    List<Registro> getAll();

    @Query("SELECT * FROM registro WHERE id IN (:registroIds)")
    List<Registro> loadAllByIds(int[] registroIds);

    @Query("SELECT * FROM registro WHERE nome LIKE :nome LIMIT 1")
    Registro findByName(String nome);

    @Query("SELECT * FROM registro ORDER BY nome ASC")
    LiveData<List<Registro>> getAlphabetizedNomes();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Registro... registros);

    @Delete
    void delete(Registro registro);

    @Update
    int update(Registro registro);

    @Query("DELETE FROM registro")
    void deleteAll();
}
