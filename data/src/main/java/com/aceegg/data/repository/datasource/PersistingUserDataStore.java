package com.aceegg.data.repository.datasource;

import android.arch.lifecycle.LiveData;

import com.aceegg.data.entities.UserEntity;

import io.reactivex.Observable;

/**
 * {@link UserDataStore} implements based on ROOM Database
 * Created by jinwenxiu on 2017/12/11.
 */

public class PersistingUserDataStore implements UserDataStore {

    private final UserDAO userDao;

    public PersistingUserDataStore(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    @Override
    public Observable<LiveData<UserEntity>> userEntity(int id) {
        return Observable.just(userDao.findById(id));
    }
}
