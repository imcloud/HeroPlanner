package com.aceegg.data.repository.datasource;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.aceegg.data.entities.ApiResponse;
import com.aceegg.data.executor.AppExecutors;
import com.aceegg.data.net.Resource;

import java.util.Objects;

/**
 * 数据源基本类
 * Created by jinwenxiu on 2017/12/12.
 */

public abstract class NetworkBoundResource<ResultType, RequestType> {

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();
    private final AppExecutors appExecutors;


    public NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        result.setValue(Resource.<ResultType>loading(null));
        final LiveData<ResultType> fromDb = loadFromDb();
        result.addSource(fromDb, new Observer<ResultType>() {
            @Override
            public void onChanged(@Nullable ResultType resultType) {
                result.removeSource(fromDb);

                if (shouldFetch(resultType)) {
                    fetchFromNetwork(fromDb);
                }else {
                    result.addSource(fromDb, new Observer<ResultType>() {
                        @Override
                        public void onChanged(@Nullable ResultType resultType) {
                            setValue(Resource.success(resultType));
                        }
                    });
                }
            }
        });
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @MainThread
    protected abstract boolean shouldFetch(@Nullable ResultType data);

    @NonNull @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    @MainThread
    protected void onFetchFailed() {
    }

    public final LiveData<Resource<ResultType>> getAsLiveData(){
        return result;
    }

    @MainThread
    private void setValue(Resource<ResultType> newValue) {
        if (!Objects.equals(result.getValue(), newValue)) {
            result.setValue(newValue);
        }
    }

    @WorkerThread
    protected RequestType processResponse(ApiResponse<RequestType> response) {
        return response.body;
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        final LiveData<ApiResponse<RequestType>> apiResponse = createCall();
        // 优先展示数据库数据
        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(@Nullable ResultType resultType) {
                setValue(Resource.loading(resultType));
            }
        });
        // 网络数据到来后存到数据库中，再从数据库读数据
        result.addSource(apiResponse, new Observer<ApiResponse<RequestType>>() {
            @Override
            public void onChanged(@Nullable final ApiResponse<RequestType> requestTypeApiResponse) {
                result.removeSource(apiResponse);

                if (requestTypeApiResponse!=null && requestTypeApiResponse.isSuccessful()) {
                    result.removeSource(dbSource);
                    appExecutors.getDiskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            saveCallResult(processResponse(requestTypeApiResponse));

                            appExecutors.getMainthread().execute(new Runnable() {
                                @Override
                                public void run() {
                                    result.addSource(loadFromDb(), new Observer<ResultType>() {
                                        @Override
                                        public void onChanged(@Nullable ResultType resultType) {
                                            setValue(Resource.success(resultType));
                                        }
                                    });
                                }
                            });
                        }
                    });
                }else {
                    onFetchFailed();
                }
            }
        });
    }
}
