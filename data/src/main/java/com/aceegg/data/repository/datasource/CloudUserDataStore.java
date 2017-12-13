package com.aceegg.data.repository.datasource;

import android.arch.lifecycle.LiveData;

import com.aceegg.data.entities.UserEntity;
import com.aceegg.data.net.UserApi;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by jinwenxiu on 2017/12/11.
 */

public class CloudUserDataStore implements UserDataStore {

    private final UserApi userApi;
    private final UserDAO userDao;

    public CloudUserDataStore(UserApi userApi, UserDAO userDAO) {
        this.userApi = userApi;
        this.userDao = userDAO;
    }

    @Override
    public Observable<LiveData<UserEntity>> userEntity(int id) {
        return userApi.getUserEntityById(id).flatMap(new Function<UserEntity, ObservableSource<LiveData<UserEntity>>>() {
            @Override
            public ObservableSource<LiveData<UserEntity>> apply(UserEntity userEntity) throws Exception {
                userDao.save(userEntity);
                return Observable.just(userDao.findById(userEntity.getId()));
            }
        });
    }
}
