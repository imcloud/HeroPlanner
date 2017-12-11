package com.aceegg.data.repository.datasource;

import android.arch.lifecycle.LiveData;

import com.aceegg.data.entities.UserEntity;

import io.reactivex.Observable;

/**
 * Created by jinwenxiu on 2017/12/11.
 */

public class CloudUserDataStore implements UserDataStore {

    public CloudUserDataStore() {
    }

    @Override
    public Observable<LiveData<UserEntity>> userEntity(int id) {
        return null;
    }
}
