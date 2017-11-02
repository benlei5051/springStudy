package org.andy.beans.async.service;

/**
 * @author: andy
 * @Date: 2017/10/30 17:53
 * @Description:
 */

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

public interface DemoAsyncService {

    @Async
    public Future<String> doTaskOne() throws Exception;

    @Async
    public Future<String> doTaskTwo() throws Exception;

    @Async
    public Future<String> doTaskThree() throws Exception;
}

