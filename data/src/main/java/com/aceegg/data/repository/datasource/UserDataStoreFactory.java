package com.aceegg.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jinwenxiu on 2017/12/12.
 */
@Singleton
public class UserDataStoreFactory {

    private final Context mContext;
    private final UserDAO mUserDao;

    @Inject
    public UserDataStoreFactory(@NonNull Context context, @NonNull UserDAO userDAO) {
        this.mContext = context.getApplicationContext();
        this.mUserDao = userDAO;
    }


}
