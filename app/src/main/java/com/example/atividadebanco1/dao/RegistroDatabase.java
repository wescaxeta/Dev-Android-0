package com.example.atividadebanco1.dao;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.atividadebanco1.modelo.Registro;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.content.ContentValues.TAG;

@Database(entities = {Registro.class}, version = 1, exportSchema = false)
public abstract class RegistroDatabase extends RoomDatabase {

    public abstract RegistroDao registroDao();

    private static volatile RegistroDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RegistroDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RegistroDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RegistroDatabase.class, "database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                RegistroDao dao = INSTANCE.registroDao();
                dao.deleteAll();

                Registro reg1 = new Registro(1, "Vida", "", "A vida é muito boa mesmo, nossa, mas é boa demais");
                Registro reg2 = new Registro(2, "Morte", "", "A morte é muito boa também, nossa, mas é boa demais");
                Registro reg3 = new Registro(3, "Saúde", "", "Tome vacina e não cloroquina");
                Registro reg4 = new Registro(4, "Família", "", "Não sendo a família do presidente é uma coisa boa");

                dao.insertAll(reg1, reg2, reg3, reg4);
            });
        }
    };

}
