package com.aceegg.data.net;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jinwenxiu on 2017/12/12.
 */

public class Resource<T> {

    private String message;
    private T data;
    private Status status;

    public Resource(@Nullable String message, @Nullable T data, @NonNull Status status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(null, data, Status.SUCCESS);
    }

    public static <T> Resource<T> error(@Nullable T data, @Nullable String message) {
        return new Resource<>(message, data, Status.ERROR);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(null, data, Status.LOADING);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        if (status != resource.status) {
            return false;
        }
        if (message != null ? !message.equals(resource.message) : resource.message != null) {
            return false;
        }
        return data != null ? data.equals(resource.data) : resource.data == null;
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
