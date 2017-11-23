package com.linhnt.speedshoot.bases.counter;

public class MilisecCounter implements Counter {
    private long maxMilisec;
    private long miliSecCount;
    private boolean loop;

    public MilisecCounter() {}

    public MilisecCounter(long maxMilisec) {
        this.maxMilisec = maxMilisec;
        this.miliSecCount = 0;
    }

    @Override
    public boolean run(long milisecDelay) {
        if(miliSecCount <= maxMilisec) {
            miliSecCount += milisecDelay;
            return false;
        } else if(loop) {
            reset();
        }
        return true;
    }

    @Override
    public void reset() {
        this.miliSecCount = 0;
    }

    public long getMaxMaxMilisec() {
        return maxMilisec;
    }

    public void setMaxMaxMilisec(long maxMilisec) {
        this.maxMilisec = maxMilisec;
    }

    public long getMiliSecCount() {
        return miliSecCount;
    }

    public void setMiliSecCount(long miliSecCount) {
        this.miliSecCount = miliSecCount;
    }

    @Override
    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
