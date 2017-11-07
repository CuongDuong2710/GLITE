package com.glite.popeyes.util;

/**
 * Created by tamhoang on 31/05/2016.
 */
public interface Config {

    int CONNECTION_TIMEOUT = 10000; // 10 minutes
    int READ_TIMEOUT = 10000;       // 10 minutes
    int WRITE_TIMEOUT = 10000;      // 10 minutes

    int CACHE_SIZE = 10 * 1024 * 1024; // 10MB

    String CLIENT_ID = "8";

}
