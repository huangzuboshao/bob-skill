package com.bob.learn.netty.spring.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author bob
 * @date 2022/10/12
 */
@Component
public class TaskProcessUtils {
    /**
     * 每个任务独立的线程池
     */
    private static final Map<String, ExecutorService> EXECUTOR_SERVICE_MAP = new ConcurrentHashMap<>();

    /**
     * 初始化一个线程池
     *
     * @param poolName 池名称
     * @param poolSize 池大小
     * @return
     */
    private static ExecutorService init(String poolName, int poolSize) {
        return new ThreadPoolExecutor(poolSize, poolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                r -> null, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 获取线程池
     *
     * @param poolName 池名称
     * @param poolSize 池大小
     * @return
     */
    public static ExecutorService getOrInitExecutors(String poolName, int poolSize) {
        if (!StringUtils.hasLength(poolName)) {
            throw new IllegalArgumentException("线程池不能为空");
        }
        ExecutorService executorService = EXECUTOR_SERVICE_MAP.get(poolName);
        if (executorService == null) {
            synchronized (TaskProcessUtils.class) {
                executorService = EXECUTOR_SERVICE_MAP.get(poolName);
                if (executorService == null) {
                    executorService = init(poolName, poolSize);
                    EXECUTOR_SERVICE_MAP.put(poolName, executorService);
                }
            }
        }
        return executorService;
    }

    /**
     * 回收线程资源
     *
     * @param poolName 池名称
     */
    public static void releaseExecutors(String poolName) {
        ExecutorService executorService = EXECUTOR_SERVICE_MAP.remove(poolName);
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
