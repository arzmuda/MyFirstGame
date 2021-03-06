package com.game.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {
    Random random = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    @Override
    public void tick() {
        x +=velX;
        y +=velY;

        x = Game.clamp(x, 0,Game.WIDTH - 32);
        y = Game.clamp(y, 0, Game.HEIGHT - 62);

        handler.addObject(new Trail((int)x, (int)y,ID.Trail, Color.WHITE, 32, 32, 0.03f, handler));


        collision();



    }
    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy
                    || tempObject.getId() == ID.BossMinion)   {
                if(getBounds().intersects(tempObject.getBounds()))
                     HUD.HEALTH -= 2;

            }else if(tempObject.getId() == ID.HardBasicEnemy || tempObject.getId() == ID.HardFastEnemy ||
                    tempObject.getId() == ID.HardSmartEnemy){
                if(getBounds().intersects(tempObject.getBounds()))
                    HUD.HEALTH -= 3;


             }else if(tempObject.getId() == ID.Boss){
                if(getBounds().intersects(tempObject.getBounds()))
                HUD.HEALTH = 0;

            }
        }
    }

    @Override
    public void render(Graphics graphics) {

        graphics.setColor(Color.WHITE);
        //(id == ID.Player2)graphics.setColor(Color.cyan);
        graphics.fillRect((int)x ,(int)y,32,32 );


    }
}
