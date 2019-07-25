package com.serkancay.doviz.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.serkancay.doviz.data.db.entity.Rate;
import java.util.List;

/**
 * Created by S.Serkan Cay on 25.07.2019
 */

@Dao
public interface RateDao {

    @Query("SELECT * FROM rates")
    List<Rate> getAllRates();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertRate(Rate rate);

}
