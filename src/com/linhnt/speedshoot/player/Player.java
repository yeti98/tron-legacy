package com.linhnt.speedshoot.player;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.animation.Animation;
import com.linhnt.speedshoot.bases.physic.Physic;
import com.linhnt.speedshoot.bases.physic.PhysicBody;
import com.linhnt.speedshoot.bases.pool.GameObjectPool;
import com.linhnt.speedshoot.bonus.Bonus;
import com.linhnt.speedshoot.input.KeyboardListener;
import com.linhnt.speedshoot.main.Settings;
import com.linhnt.speedshoot.utils.ImageUtils;

import java.io.IOException;
import java.util.Random;

public class Player extends GameObject implements PhysicBody {
    private KeyboardListener keyboardListener;

    private Animation normalAnimation;
    private Animation turboAnimation;
    private boolean isVaCham= false;

    private int blood = Settings.INIT_BLOOD;
    private int point = Settings.INIT_POINT;

    private float speed = Settings.NORMAL_SPEED;

    private Tail tail;

    public Player(){
        setupTail();
        this.getScale().set(0.4f, 0.4f);
        this.matchSpeedToVelocity();
    }
    private void checkBlood(){
        if(this.blood<=0){
            this.tail.paticlesGroup.setActive(false);
            this.setActive(false);
        }
    }

    public Tail getTail() {
        return tail;
    }

    void setupVaCham() {
        Bonus bonus= Physic.bodyContains(this.position.x, this.position.y, Bonus.class );
        if(bonus!=null ) {
            Random rd= new Random();
            bonus.getPosition().set(rd.nextInt(Settings.GAME_WIDTH), rd.nextInt(Settings.GAME_HEIGHT));
            this.point+=10;
            this.tail.grow();
            this.tail.length++;
        }
        Player otherPlayer= Physic.bodyContains(this.position.x, this.position.y, Player.class);
        if(otherPlayer!=null){
            if(!isVaCham){
                isVaCham=true;
                this.blood-=10;
            }
        }
        else {
            isVaCham=false;
        }
    }

    public void setupTail(){
        try {
            this.tail = GameObjectPool.createAndAdd(Tail.class);
            this.tail.setPlayer(this);
            this.tail.setupParticleDeque();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run(long milisecDelay, GameObject parent) {
        checkBlood();
        super.run(milisecDelay, parent);
        setupVaCham();
        checkOutside();
    }

    private void checkOutside() {
        if(position.x>Settings.GAME_WIDTH) position.set(0, position.y);
        if(position.x<0) position.set(Settings.GAME_WIDTH, position.y);
        if(position.y<0) position.set(position.x, Settings.GAME_HEIGHT);
        if(position.y>Settings.GAME_HEIGHT) position.set(position.x, 0);
    }

    public void matchSpeedToVelocity(){
        this.getVelocity().multiplyThis(speed / this.getVelocity().getLength());
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setNormalAnimationFromUrl(String url){
        try {
            this.normalAnimation = new Animation(ImageUtils.readAllInForder(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTurboAnimation(String url){
        try {
            this.turboAnimation = new Animation(ImageUtils.readAllInForder(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;

        this.matchSpeedToVelocity();
    }

    @Override
    public boolean contains(Vector2D point) {
        return this.renderer.contains(point.minus(position));
    }
}
