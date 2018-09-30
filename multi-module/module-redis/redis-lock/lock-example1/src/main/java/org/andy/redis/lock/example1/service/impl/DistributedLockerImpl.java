package org.andy.redis.lock.example1.service.impl;

import org.andy.redis.lock.example1.exception.UnableToAquireLockException;
import org.andy.redis.lock.example1.service.AquiredLockWorker;
import org.andy.redis.lock.example1.service.DistributedLocker;
import org.andy.redis.lock.example1.service.RedissonConnector;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/9/28 11:11
 * @version: V1.0
 */
@Component
public class DistributedLockerImpl implements DistributedLocker {

    private static final String LOCKER_PREFIX = "lock:";

    @Autowired
    private RedissonConnector redissonConnector;


    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker) throws UnableToAquireLockException, Exception {
        return lock(resourceName, worker, 900);
    }

    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker, int lockTime) throws
            UnableToAquireLockException, Exception {
        RedissonClient redisson = redissonConnector.getClient();
        RLock lock = redisson.getLock(LOCKER_PREFIX + resourceName);
       /* If the lock is currently held by another thread in this or any
        other process in the distributed system this method keeps trying
        to acquire the lock for up to <code>waitTime</code> before
        giving up and returning <code>false</code>*/
        boolean success = lock.tryLock(10, lockTime, TimeUnit.SECONDS);
        if (success) {
            try {
                return worker.invokeAfterLockAquire();
            } finally {
                lock.unlock();
            }
        }
        throw new UnableToAquireLockException();
    }
}
