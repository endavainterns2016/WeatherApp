package com.example.nvdovin.weatherapp.domain.factory;

import android.os.Process;

import java.util.concurrent.ThreadFactory;

public class PriorityThreadFactory implements ThreadFactory {

    private final int mThreadPriority;


    public PriorityThreadFactory(int mThreadPriority){
        this.mThreadPriority = mThreadPriority;
    }

    @Override
    public Thread newThread(final Runnable r) {
        Runnable wrapperRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Process.setThreadPriority(mThreadPriority);
                }catch (Throwable t){
                    t.printStackTrace();
                }
                r.run();
            }
        };
        return new Thread(wrapperRunnable);
    }
}
