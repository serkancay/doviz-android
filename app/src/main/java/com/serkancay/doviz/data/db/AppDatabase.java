package com.serkancay.doviz.data.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.serkancay.doviz.data.db.dao.RateDao;
import com.serkancay.doviz.data.db.entity.Rate;

/**
 * Created by S.Serkan Cay on 25.07.2019
 */

@Database(entities = {Rate.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    public abstract RateDao rateModel();

    public static AppDatabase getDatabase(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "doviz")
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }

    public static AppDatabase getMemoryDatabase(Context context) {
        if (sInstance == null) {
            sInstance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

}
