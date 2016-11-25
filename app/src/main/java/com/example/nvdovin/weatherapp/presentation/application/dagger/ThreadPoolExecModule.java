package com.example.nvdovin.weatherapp.presentation.application.dagger;


import android.os.Process;

import com.example.nvdovin.weatherapp.domain.factory.PriorityThreadFactory;
import com.example.nvdovin.weatherapp.domain.utils.executor.DefaultThreadPoolExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;

@Module
public class ThreadPoolExecModule {

    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    @Provides
    @AppScope
    DefaultThreadPoolExecutor provideDefaultThreadPoolExecutor(ThreadPoolExecutor forBackgroundTasks){
        return new DefaultThreadPoolExecutor(forBackgroundTasks);
    }

    @Provides
    @AppScope
    ThreadFactory provideThreadFactory(){
        return new PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);
    }

    @Provides
    @AppScope
    LinkedBlockingQueue provideLinkedBlockingQueue(){
        return new LinkedBlockingQueue<Runnable>();
    }

    @Provides
    @AppScope
    ThreadPoolExecutor provideThreadPoolExecutor(ThreadFactory backgroundPriorityThreadFactory,
                                                 LinkedBlockingQueue linkedBlockingQueue){
        return new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                linkedBlockingQueue,
                backgroundPriorityThreadFactory
        );
    }
}
