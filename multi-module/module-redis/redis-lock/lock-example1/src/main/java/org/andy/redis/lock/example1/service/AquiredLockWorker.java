package org.andy.redis.lock.example1.service;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/9/28 10:49
 * @version: V1.0
 */
public interface AquiredLockWorker<T> {
    /**
     * 获取锁后需要处理的逻辑
     *
     * @return
     * @throws Exception
     */
    T invokeAfterLockAquire() throws Exception;
}
