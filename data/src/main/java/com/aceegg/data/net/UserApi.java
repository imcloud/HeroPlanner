package com.aceegg.data.net;

import com.aceegg.data.entities.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by jinwenxiu on 2017/12/11.
 */

public interface UserApi {

    @GET("/user/{id}")
    Observable<UserEntity> getUserEntityById(int id);
}
