package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;

public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public String direction;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public int actionCooldownCounter = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}

    public void setStatus(String status) {}

    public void update() {

        setAction();

        // Check tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        if (collisionOn == false) {
            switch(this.direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
                case "still":
                    break;
                
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(worldX, worldY, gp.TILE_SIZE, gp.TILE_SIZE);
    }

    public String getDirectionToTarget(int targetX, int targetY) {
        int diffX = targetX - worldX;
        int diffY = targetY - worldY;

        if (Math.abs(diffX) > gp.TILE_SIZE /2 && Math.abs(diffY) > gp.TILE_SIZE/2) {
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (diffX > 0) {
                    direction = "right";
                } else if (diffX <= 0) {
                    direction = "left";
                }
            } else if (Math.abs(diffX) <= Math.abs(diffY)) {
                if (diffY > 0) {
                    direction = "down";
                } else if (diffY <= 0) {
                    direction = "up";
                }  
            }
        } else {
            direction = "still";
        }

        return direction;
    }

}
