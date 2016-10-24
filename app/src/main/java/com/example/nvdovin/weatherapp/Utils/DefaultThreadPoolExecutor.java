package com.example.nvdovin.weatherapp.Utils;

import android.os.Process;

import com.example.nvdovin.weatherapp.factory.PriorityThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefaultThreadPoolExecutor {

    public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private final ThreadPoolExecutor mForBackgroundTasks;
    private final Executor mMainThreadExecutor;
    private static DefaultThreadPoolExecutor sInstance;

    public static DefaultThreadPoolExecutor getInstance() {
        if (sInstance == null) {
            synchronized (DefaultThreadPoolExecutor.class) {
                sInstance = new DefaultThreadPoolExecutor();
            }
        }
        return sInstance;
    }

    private DefaultThreadPoolExecutor(){
        ThreadFactory backgroundPriorityThreadFactory = new PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);

        mForBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                backgroundPriorityThreadFactory
        );

        mMainThreadExecutor = new MainThreadExecutor();
    }

    public ThreadPoolExecutor forBackgroundTasks(){ //TODO no need
        return mForBackgroundTasks;
    }

    public Executor forMainThreadTasks() {
        return mMainThreadExecutor;
    }

    public void executeBackground(com.example.nvdovin.weatherapp.Utils.Executor executor) {
        mForBackgroundTasks.execute(createRunnable(executor));
    }


    private Runnable createRunnable(final com.example.nvdovin.weatherapp.Utils.Executor executor){

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