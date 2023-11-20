package com.bob.learn.juc;

/**
 *  拒绝策略
 * @author huangzuboshao
 * @date 2023/2/22 15:23
 */
public interface MyRejectPolicyHandler<T> {
    /**
     * 拒绝策略
     * @param task
     * @param blockingDeque
     */
    void reject(T task, MyBlockingDeque blockingDeque);
}
