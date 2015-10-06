package com.byteme.Util;

import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

/**
 * MULE
 */
public class GlobalTimer {
    private static final GlobalTimer ourInstance = new GlobalTimer();
    public static GlobalTimer getInstance() {
        return ourInstance;
    }

    private CanTick tickHandler;
    private Timer timer;

    private GlobalTimer() {
        initTimer();
    }

    public void setTickHandler(CanTick tickHandler) {
        this.tickHandler = tickHandler;
    }

    private void tick() {
        if (tickHandler != null) {
            tickHandler.tick();
        }
    }

    private void initTimer() {
        stopTimer();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(GlobalTimer.this::tick);
            }
        }, 0, 1000);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }
}
