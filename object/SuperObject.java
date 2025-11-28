package object;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public abstract class SuperObject {
    
    public String name;
    public Color color;
    public boolean collision = false;
    public int worldX, worldY;
    public boolean assigned = false;

    public void draw(Graphics2D g2, GamePanel gp) {
        g2.setColor(this.color);
        g2.fillRect(worldX, worldY, gp.TILE_SIZE, gp.TILE_SIZE);
    }
}
