package com.aceegg.data.net;

import com.aceegg.data.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.aceegg.data.BuildConfig.BASE_HOST;

/**
 * api service
 * Created by cloud on 2017/11/27.
 */

public class ApiService {

    private static ApiService mService;
    private final Retrofit mRetrofit;
    private final OkHttpClient client;

    private ApiService() {
        client = getHttpClient();

        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_HOST)
                .client(client)
                .build();
    }

    public static ApiService getInstance() {
        if (mService == null) {
            synchronized (ApiService.class) {
                if (mService == null) {
                    mService = new ApiService();
                }
            }
        }
        return mService;
    }

    public <T> T createApi(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    private OkHttpClient getHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return new OkHttpClient.Builder()
                .connectTimeout(BuildConfig.CONNECT_TIMEOUT, TimeUnit.MICROSECONDS)
                .readTimeout(BuildConfig.READ_TIMEOUT, TimeUnit.MICROSECONDS)
                .writeTimeout(BuildConfig.WRITE_TIMEOUT, TimeUnit.MICROSECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }
}
