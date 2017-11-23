package com.linhnt.speedshoot.bases.counter;

public class FrameCounter implements Counter {
    private int maxFrame;
    private int current;
    private boolean loop;

    public FrameCounter() {}

    public FrameCounter(int maxFrame) {
        this.maxFrame = maxFrame;
    }

    @Override
    public boolean run(long milisecDelay) {
        if(current <= maxFrame) {
            current++;
            return false;
        } else if(loop) {
            reset();
        }
        return true;
    }

    @Override
    public void reset() {
        this.current = 0;
    }

    public int getMaxFrame() {
        return maxFrame;
    }

    public void setMaxFrame(int maxFrame) {
        this.maxFrame = maxFrame;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    @Override
    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
