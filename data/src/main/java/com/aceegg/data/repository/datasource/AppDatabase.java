package com.aceegg.data.repository.datasource;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.aceegg.data.entities.UserEntity;

/**
 * Created by jinwenxiu on 2017/12/11.
 */
@Database(entities = { UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
}
