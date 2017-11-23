package com.linhnt.speedshoot.player;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.group.Group;
import com.linhnt.speedshoot.bases.physic.Physic;
import com.linhnt.speedshoot.bases.pool.GameObjectPool;
import com.linhnt.speedshoot.main.Settings;
import java.util.LinkedList;
import java.util.ArrayList;

public class Tail extends GameObject {
    public float length = 10;
    private LinkedList<Particle> particles = new LinkedList<>();
    private Player player;
    Group paticlesGroup;
    public Tail() throws Exception {
        paticlesGroup= GameObjectPool.createAndAdd(Group.class);
    }
    public void setPlayer(Player player){
        this.player = player;
    }

    public void setupParticleDeque(){
        for(int i=0; i< 10; i++) grow();
    }
    public float getLength(){ return length;}
    public void setLength(float value){ length = value;}

    @Override
    public void run(long milisecDelay, GameObject parent) {
        super.run(milisecDelay, parent);
        particles.getFirst().getPosition().set(player.getPosition());
        if(checkParticle()){
            particles.addFirst(particles.removeLast());
        }
        if(Math.floor(length)<particles.size()) degrow();
        else if(Math.floor(length)>particles.size()) grow();
        if(particles.size()<=5) player.setSpeed(Settings.NORMAL_SPEED);
        setupVaChamDuoi();
    }
    public  void degrow(){
        if(particles.size()>5) paticlesGroup.removeChild(particles.removeLast());
    }
    public void grow(){
        try {
            Particle  tmpparticle = GameObjectPool.create(Particle.class);
            paticlesGroup.addChild(tmpparticle);
            tmpparticle.getPosition().set(player.getPosition());
            particles.addFirst(tmpparticle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupVaChamDuoi() { //va chạm đuôi
        for(int i=4;i<particles.size();i++){
            Player otherplayer= Physic.bodyContains(particles.get(i).getPosition().x,particles.get(i).getPosition().y, Player.class);
            if(otherplayer!=null){
                otherplayer.setBlood(0);
                otherplayer.getVelocity().set(0,0);
                player.getVelocity().set(0,0);
            }
        }
    }

    private boolean checkParticle(){
        Particle f = particles.get(0);
        Particle n = particles.get(1);
        return Math.sqrt(Math.pow(f.getPosition().x - n.getPosition().x, 2) + Math.pow(f.getPosition().y - n.getPosition().y, 2)) > 15;

    }
}
