package com.aceegg.data.net;

import android.arch.lifecycle.LiveData;

import com.aceegg.data.entities.ApiResponse;
import com.aceegg.data.entities.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by jinwenxiu on 2017/12/11.
 */

public interface UserApi {

    @GET("/user/{id}")
    LiveData<ApiResponse<UserEntity>> getUserEntityById(int id);
}
