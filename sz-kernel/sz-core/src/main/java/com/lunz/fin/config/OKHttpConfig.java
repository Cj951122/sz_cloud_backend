package com.lunz.fin.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class OKHttpConfig {

    public static OkHttpClient okHttpClient() {
        return new OkHttpClient().newBuilder().retryOnConnectionFailure(true).connectionPool(pool())
                .connectTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS).writeTimeout(100, TimeUnit.SECONDS)
                .build();

    }


    public static ConnectionPool pool() {
        return new ConnectionPool(50, 5, TimeUnit.MINUTES);
    }
}
