package org.andy.beans.async.example4.service;

/**
 * @author: andy
 * @Date: 2017/10/30 17:53
 * @Description:
 */

import org.springframework.scheduling.annotation.Async;

public interface DemoAsyncService {

    @Async
    void doTaskFour(int i);

}

