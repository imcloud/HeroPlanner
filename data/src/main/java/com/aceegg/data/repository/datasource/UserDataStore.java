package com.aceegg.data.repository.datasource;

import android.arch.lifecycle.LiveData;

import com.aceegg.data.entities.UserEntity;

import io.reactivex.Observable;

/**
 * UserDataStore
 * Created by jinwenxiu on 2017/12/11.
 */

public interface UserDataStore {

    /**
     * Get an {@link Observable} which will emit a {@link UserEntity} by it id.
     *
     * @param id user id
     * @return UserEntity
     */
    Observable<LiveData<UserEntity>> userEntity(int id);
}
