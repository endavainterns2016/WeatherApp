package com.example.nvdovin.weatherapp.domain.utils.executor;

import android.os.Process;

import com.example.nvdovin.weatherapp.domain.factory.PriorityThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefaultThreadPoolExecutor {
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static DefaultThreadPoolExecutor instance;
    private final ThreadPoolExecutor forBackgroundTasks;
    private final Executor mainThreadExecutor;

    private DefaultThreadPoolExecutor() {
        ThreadFactory backgroundPriorityThreadFactory = new PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);

        forBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                backgroundPriorityThreadFactory
        );

        mainThreadExecutor = new MainThreadExecutor();
    }

    public static DefaultThreadPoolExecutor getInstance() {
        if (instance == null) {
            synchronized (DefaultThreadPoolExecutor.class) {
                instance = new DefaultThreadPoolExecutor();
            }
        }
        return instance;
    }

    public ThreadPoolExecutor forBackgroundTasks() { //TODO no need
        return forBackgroundTasks;
    }

    public Executor forMainThreadTasks() {
        return mainThreadExecutor;
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