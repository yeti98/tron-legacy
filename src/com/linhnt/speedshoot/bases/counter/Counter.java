package com.linhnt.speedshoot.bases.counter;

public interface Counter {
    boolean isLoop();
    boolean run(long milisecDelay);
    void reset();
}
