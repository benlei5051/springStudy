package org.andy.redis.lock.example1.service;

import org.andy.redis.lock.example1.exception.UnableToAquireLockException;
import org.andy.redis.lock.example1.service.AquiredLockWorker;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/9/28 10:56
 * @version: V1.0
 */
public interface DistributedLocker {

    /**
     * 获取锁
     *
     * @param resourceName
     * @param worker
     * @param <T>
     * @return
     * @throws UnableToAquireLockException
     * @throws Exception
     */
    <T> T lock(String resourceName, AquiredLockWorker<T> worker) throws UnableToAquireLockException, Exception;


    /**
     *
     * 获取锁
     * @param resourceName
     * @param worker
     * @param lockTime
     * @param <T>
     * @return
     * @throws UnableToAquireLockException
     * @throws Exception
     */
    <T> T lock(String resourceName, AquiredLockWorker<T> worker, int lockTime) throws UnableToAquireLockException, Exception;


}
