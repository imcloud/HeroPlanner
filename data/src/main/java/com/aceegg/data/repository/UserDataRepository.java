package com.aceegg.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.aceegg.data.entities.ApiResponse;
import com.aceegg.data.entities.UserEntity;
import com.aceegg.data.executor.AppExecutors;
import com.aceegg.data.net.Resource;
import com.aceegg.data.net.UserApi;
import com.aceegg.data.repository.datasource.NetworkBoundResource;
import com.aceegg.data.repository.datasource.UserDAO;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * user
 * Created by jinwenxiu on 2017/12/11.
 */
@Singleton
public class UserDataRepository {

    private final AppExecutors appExecutors;
    private final UserDAO userDao;
    private final UserApi userApi;

    @Inject
    public UserDataRepository(AppExecutors appExecutors, UserDAO userDAO, UserApi userApi) {
        this.appExecutors = appExecutors;
        this.userApi = userApi;
        this.userDao = userDAO;
    }

    public LiveData<Resource<UserEntity>> loadUser(final int id) {
        return new NetworkBoundResource<UserEntity, UserEntity>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull UserEntity item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable UserEntity data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<UserEntity> loadFromDb() {
                return userDao.findById(id);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<UserEntity>> createCall() {
                return userApi.getUserEntityById(id);
            }
        }.getAsLiveData();
    }
}
