package com.example.nvdovin.weatherapp.domain.utils.executor;

import java.util.concurrent.ThreadPoolExecutor;

public class DefaultThreadPoolExecutor {
    private final ThreadPoolExecutor forBackgroundTasks;

    public DefaultThreadPoolExecutor(ThreadPoolExecutor forBackgroundTasks) {
        this.forBackgroundTasks = forBackgroundTasks;
    }

    public void executeBackground(com.example.nvdovin.weatherapp.domain.utils.executor.Executor executor) {
        forBackgroundTasks.execute(createRunnable(executor));
    }

    private Runnable createRunnable(final com.example.nvdovin.weatherapp.domain.utils.executor.Executor executor) {

        return new Runnable() {
            @Override
            public void run() {

                try {
                    executor.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
    }
}