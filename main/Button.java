package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Button {
    int x, y, width, height;
    String text;
    Graphics2D g2;

    public Button (int x, int y, int w, int h, String text, Graphics2D g2) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.text = text;
        this.g2 = g2;
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= this.x-10 && mouseX <= this.x + this.width+20 && mouseY >= this.y - this.height+5 && mouseY <= this.y+5;
    }

    public void draw() {
        g2.setColor(Color.white);
        g2.drawString(this.text, this.x, this.y);
        g2.drawRect(this.x-10, this.y-this.height+5, this.width+20, this.height);
    }

    public void drawHover() {
        g2.setColor(Color.gray);
        g2.drawString(this.text, this.x, this.y);
    }
}
