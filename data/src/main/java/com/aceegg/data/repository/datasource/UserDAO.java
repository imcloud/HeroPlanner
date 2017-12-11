package com.aceegg.data.repository.datasource;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aceegg.data.entities.UserEntity;

/**
 * Created by jinwenxiu on 2017/12/11.
 */
@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(UserEntity userEntity);

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    LiveData<UserEntity> findById(int id);
}
