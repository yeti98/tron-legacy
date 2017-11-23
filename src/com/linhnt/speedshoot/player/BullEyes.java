package com.linhnt.speedshoot.player;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Scope;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.animation.Renderer;
import com.linhnt.speedshoot.bases.animation.SingleImageRenderer;
import com.linhnt.speedshoot.bases.listeners.GameMouseListener;
import com.linhnt.speedshoot.bases.physic.Physic;
import com.linhnt.speedshoot.main.Settings;
import com.linhnt.speedshoot.utils.ImageUtils;
import com.linhnt.speedshoot.vehicule.Bus;
import com.linhnt.speedshoot.vehicule.Truck;
import com.linhnt.speedshoot.vehicule.Vehicule;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BullEyes extends GameObject {
    private BufferedImage image;
    private final int READY = 0;
    private final int SHOWN = 1;

    private Renderer bullsEyeRender;
    private Renderer showCaptureScreenRenderer;
    /*
    state = 0: san sang ban
    state = 1: dang show ket qua
     */
    private int state = READY;
    public BullEyes() {
        try {
            this.bullsEyeRender = new SingleImageRenderer(
                    ImageUtils.read(
                            "assets/images/bullseye.png"));
            this.showCaptureScreenRenderer = new SingleImageRenderer(null);

            this.renderer = this.bullsEyeRender;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.scale.set(0.5f, 0.5f);
        this.setupListener();
    }

    private void setupListener() {
        this.mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cliked(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if(state == READY) {
                    position.set(e.getX(), e.getY());
                }
            }
        };
        GameMouseListener.registerToInstance(this.mouseAdapter);
    }

    private void cliked(MouseEvent e) {
        //clicked va lay ra 1 phuong tien bat ky
        //TODO
        if(this.state == READY) {
            clickedReady(e);
        } else if(this.state == SHOWN) {
            clickedShown(e);
        }
    }

    private void clickedShown(MouseEvent e) {
        renderer = bullsEyeRender;
        this.getScale().set(0.5f, 0.5f);
        this.state = READY;
    }

    private void clickedReady(MouseEvent e) {
        Vehicule vehicule = Physic.bodyContains(e.getX(), e.getY(), Vehicule.class);
        //cat ra cai anh
        BufferedImage cropImage = cropImage(this.image, new Vector2D(e.getX(), e.getY()));
        makeDetailsForImage(cropImage, vehicule);
        ((SingleImageRenderer)showCaptureScreenRenderer).setImage(cropImage);
        renderer = showCaptureScreenRenderer;
        this.getScale().set(2, 2);
        this.state = SHOWN;
        this.position.set(Settings.GAME_WIDTH / 2, Settings.GAME_HEIGHT / 2);
    }

    private void makeDetailsForImage(BufferedImage cropImage, Vehicule vehicule) {
        //ve vien anh
        Graphics2D cropImageGraphics = (Graphics2D) cropImage.getGraphics();
        cropImageGraphics.setColor(Color.blue);
        cropImageGraphics.setStroke(new BasicStroke(10));
        cropImageGraphics.drawRect(-1, -1, cropImage.getWidth(), cropImage.getHeight());
        //in thong tin cua xe bi bat

        if(vehicule != null) {
            if(vehicule.isValidSpeed()) {
                cropImageGraphics.drawString("Phương tiện chạy đúng tốc độ!", 10, 10);
            } else {
                cropImageGraphics.drawString("Bắt được phương tiện chạy quá tốc độ!", 10, 10);
            }
        } else {
            cropImageGraphics.drawString("Không bắt được xe nào rồi!", 10, 10);
        }
    }

    private BufferedImage cropImage(BufferedImage image, Vector2D point) {
        float iWidth = 200;
        float iHeight = 100;
        Vector2D cropPoint = point.minus(iWidth / 2, iHeight / 2);

        cropPoint.x = Scope.scope(cropPoint.x, new Scope(0f, 600f));
        cropPoint.y = Scope.scope(cropPoint.y, new Scope(0f, 500f));

        BufferedImage iCrop = image.getSubimage((int)cropPoint.x, (int)cropPoint.y,
                (int)iWidth, (int)iHeight);
        iCrop = ImageUtils.cloneImage(iCrop);
        //test
        return iCrop;
    }

    @Override
    public void run(long milisecDelay, GameObject parent) {
        super.run(milisecDelay, parent);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
