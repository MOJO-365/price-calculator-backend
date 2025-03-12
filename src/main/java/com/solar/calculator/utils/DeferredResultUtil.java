package com.solar.calculator.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;

@Service
public class DeferredResultUtil {
    public static <T> DeferredResult<T> executeAsync(Supplier<T> task) {

        DeferredResult<T> deferredResult = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {
            try {
                deferredResult.setResult(task.get());
            } catch (Exception e) {
                deferredResult.setErrorResult("Error in processing: " + e.getMessage());
            }
        });

        return deferredResult;
    }
}
