package com.holderzone.holder.saas.store.item.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:31
 * desc：
 */
@Component
@Slf4j
public class ThreadPoolExcuterConfig {
    @Autowired
    private DynamicHelper dynamicHelper;

    private final AtomicInteger mCount = new AtomicInteger(0);

    private final ExecutorService executorService = new ThreadPoolExecutor(5, 15,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(99999),
            r -> new Thread(r, "dishCustomerPublish-" + mCount.incrementAndGet()),
            new ThreadPoolExecutor.AbortPolicy());

    public Future<?> excute(Runnable runnable) throws ExecutionException, InterruptedException {
//        String enterpriseGuid = UserContextUtils.getEnterpriseGuid();
//        dynamicHelper.changeDatasource(enterpriseGuid);

        return executorService.submit(runnable);
    }
}

