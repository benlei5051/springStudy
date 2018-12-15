package org.andy.beans.async.example3.service;

/**
 * @author: andy
 * @Date: 2017/10/30 17:53
 * @Description:
 */

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

public interface DemoAsyncService {

    @Async
    Future<String> doTaskOne() throws Exception;

    @Async
    Future<String> doTaskTwo() throws Exception;

    @Async
    Future<String> doTaskThree() throws Exception;

}

