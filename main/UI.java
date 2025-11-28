package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class UI {
    
    GamePanel gp;
    StatManager sm;
    MouseHandler mh;
    Graphics2D g2;
    Font arial_40;
    Font cambria_100;
    Font cambria_40;
    Font cambria_20;
    Font arial_10;
    String posString;
    boolean clicked = false;
    public int selNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_10 = new Font("Arial", Font.PLAIN, 10);
        cambria_100 = new Font("Cambria", Font.BOLD, 100);
        cambria_40 = new Font("Cambria", Font.BOLD, 40);
        cambria_20 = new Font("Cambria", Font.BOLD, 20);
    }

    public void draw(Graphics2D g2, StatManager sm, MouseHandler mh) {
        this.g2 = g2;
        this.sm = sm;
        this.mh = mh;

        if (gp.gameState == gp.TITLE_STATE) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.PLAY_STATE) {
            drawBuyWindow(10, 660, gp.SCREEN_WIDTH-20, 100);
            drawStatWindow(10, 10, gp.SCREEN_WIDTH-20, 60);
        }
        if (gp.gameState == gp.PAUSE_STATE) {
            drawPauseScreen();
        }
        if (gp.gameState == gp.END_STATE) {
            drawEndScreen();
        }

        drawMousePos();
    }

    public void drawTitleScreen() {

        // Title text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String title = "Island Game";
        int x = getXforCenterText(title);
        int y = gp.SCREEN_HEIGHT / 4;

        g2.setColor(Color.blue);
        g2.drawString(title, x+3, y+3);
        g2.setColor(Color.green);
        g2.drawString(title, x, y);

        // Buttons
        // Start button
        g2.setColor(Color.white);
        g2.setFont(cambria_40);
        String startGameText = "START GAME";
        x = getXforCenterText(startGameText);
        y = gp.SCREEN_HEIGHT * 2/4;
        int width = getWidthForText(startGameText);
        int height = getHeightForText(startGameText);
        Button startButton = new Button(x, y, width, height, startGameText, g2);
        startButton.draw();
        if (startButton.isHovered(mh.getX(), mh.getY())) {
            startButton.drawHover();

            if (mh.isPressed()) {
                gp.gameState = gp.PLAY_STATE;
            }
        }

        // Quit button
        g2.setColor(Color.white);
        g2.setFont(cambria_40);
        String quitGameText = "QUIT GAME";
        x = getXforCenterText(quitGameText);
        y = gp.SCREEN_HEIGHT * 2/4 + 100;
        width = getWidthForText(quitGameText);
        height = getHeightForText(quitGameText);
        Button quitButton = new Button(x, y, width, height, quitGameText, g2);
        quitButton.draw();
        if (quitButton.isHovered(mh.getX(), mh.getY())) {
            quitButton.drawHover();

            if (mh.isPressed()) {
                System.exit(0);
            }
        }

    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));

        String text = "PAUSED";
        int x = getXforCenterText(text);
        int y = gp.SCREEN_HEIGHT/2;

        g2.drawString(text, x, y);
    }

    public void drawEndScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));

        String text = "You ran out of food. Game over.";
        int x = getXforCenterText(text);
        int y = gp.SCREEN_HEIGHT/2;

        g2.drawString(text, x, y);
    }

    public int getXforCenterText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.SCREEN_WIDTH/2 - length/2;

        return x;
    }

    public int getWidthForText(String text) {
        return (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    }

    public int getHeightForText(String text) {
        return (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
    }

    public void drawBuyWindow(int x, int y, int width, int height) {
        // Window background
        Color c=new Color(0f,0f,0f,.4f );
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 0, 0);
        
        // Window border
        g2.setColor(Color.lightGray);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, width, height, 0, 0);

        // BUTTONS
        // Gather Wood
        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String gatherWoodText = "Gather Wood";
        int text_x = x + 20;
        int text_y = y + 25;
        width = getWidthForText(gatherWoodText);
        height = getHeightForText(gatherWoodText);
        Button gatherWoodButton = new Button(text_x, text_y, width, height, gatherWoodText, g2);
        gatherWoodButton.draw();
        if (gatherWoodButton.isHovered(mh.getX(), mh.getY())) {
            gatherWoodButton.drawHover();

            if (mh.isPressed()) {clicked = true;}
            if (mh.isPressed() == false && clicked == true) {
                sm.assignWood();
                clicked = false;
            }
        }

        // Dismiss gathering wood
        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String disWoodText = "-";
        text_x = x + 250;
        text_y = y + 25;
        width = getWidthForText(disWoodText);
        height = getHeightForText(disWoodText);
        Button disWoodButton = new Button(text_x, text_y, width, height, disWoodText, g2);
        disWoodButton.draw();
        if (disWoodButton.isHovered(mh.getX(), mh.getY())) {
            disWoodButton.drawHover();

            if (mh.isPressed()) {clicked = true;}
            if (mh.isPressed() == false && clicked == true) {
                sm.dismissWood();
                clicked = false;
            }
        }

        // Gather Stone
        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String gatherStoneText = "Gather Stone";
        text_x = x + 20;
        text_y = y + 55;
        width = getWidthForText(gatherStoneText);
        height = getHeightForText(gatherStoneText);
        Button gatherStoneButton = new Button(text_x, text_y, width+2, height, gatherStoneText, g2);
        gatherStoneButton.draw();
        if (gatherStoneButton.isHovered(mh.getX(), mh.getY())) {
            gatherStoneButton.drawHover();

            if (mh.isPressed()) {clicked = true;}
            if (mh.isPressed() == false && clicked == true) {
                sm.assignStone();
                clicked = false;
            }
        }

        // Dismiss gathering Stone
        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String disStoneText = "-";
        text_x = x + 250;
        text_y = y + 55;
        width = getWidthForText(disStoneText);
        height = getHeightForText(disStoneText);
        Button disStoneButton = new Button(text_x, text_y, width, height, disStoneText, g2);
        disStoneButton.draw();
        if (disStoneButton.isHovered(mh.getX(), mh.getY())) {
            disStoneButton.drawHover();

            if (mh.isPressed()) {clicked = true;}
            if (mh.isPressed() == false && clicked == true) {
                sm.dismissStone();
                clicked = false;
            }
        }

        // Gather Food
        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String gatherFoodText = "Gather Food";
        text_x = x + 20;
        text_y = y + 85;
        width = getWidthForText(gatherFoodText);
        height = getHeightForText(gatherFoodText);
        Button gatherFoodButton = new Button(text_x, text_y, width+8, height, gatherFoodText, g2);
        gatherFoodButton.draw();
        if (gatherFoodButton.isHovered(mh.getX(), mh.getY())) {
            gatherFoodButton.drawHover();

            if (mh.isPressed()) {clicked = true;}
            if (mh.isPressed() == false && clicked == true) {
                sm.assignFood();
                clicked = false;
            }
        }

        // Dismiss gathering food
        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String disFoodText = "-";
        text_x = x + 250;
        text_y = y + 85;
        width = getWidthForText(disFoodText);
        height = getHeightForText(disFoodText);
        Button disFoodButton = new Button(text_x, text_y, width, height, disFoodText, g2);
        disFoodButton.draw();
        if (disFoodButton.isHovered(mh.getX(), mh.getY())) {
            disFoodButton.drawHover();

            if (mh.isPressed()) {clicked = true;}
            if (mh.isPressed() == false && clicked == true) {
                sm.dismissFood();
                clicked = false;
            }
        }

        // Build house
        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String buildHouseText = "Build House";
        text_x = x + 500;
        text_y = y + 25;
        width = getWidthForText(buildHouseText);
        height = getHeightForText(buildHouseText);
        Button buildHouseButton = new Button(text_x, text_y, width, height, buildHouseText, g2);
        buildHouseButton.draw();
        if (buildHouseButton.isHovered(mh.getX(), mh.getY())) {
            buildHouseButton.drawHover();

            if (mh.isPressed()) {clicked = true;}
            if (mh.isPressed() == false && clicked == true) {
                sm.buildHouse();
                clicked = false;
            }
        }

        // Build farm
        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String buildFarmText = "Build Farm";
        text_x = x + 500;
        text_y = y + 55;
        width = getWidthForText(buildFarmText);
        height = getHeightForText(buildFarmText);
        Button buildFarmButton = new Button(text_x, text_y, width+8, height, buildFarmText, g2);
        buildFarmButton.draw();
        if (buildFarmButton.isHovered(mh.getX(), mh.getY())) {
            buildFarmButton.drawHover();

            if (mh.isPressed()) {clicked = true;}
            if (mh.isPressed() == false && clicked == true) {
                sm.buildFarm();
                clicked = false;
            }
        }

        // STATS
        // Stat wood
        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String statWoodText = sm.getAssignWood() + "/" + sm.getMaxWood();
        text_x = x + 180;
        text_y = y + 25;
        g2.drawString(statWoodText, text_x, text_y);

        // Stat stone
        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String statWoodStone = sm.getAssignStone() + "/" + sm.getMaxStone();
        text_x = x + 180;
        text_y = y + 55;
        g2.drawString(statWoodStone, text_x, text_y);

        // Stat food
        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String statWoodFood = sm.getAssignFarm() + "/" + sm.getMaxFood();
        text_x = x + 180;
        text_y = y + 85;
        g2.drawString(statWoodFood, text_x, text_y);

        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String statReqWoodFood = "< Requires farm";
        text_x = x + 300;
        text_y = y + 85;
        g2.drawString(statReqWoodFood, text_x, text_y);

        // Stat build house
        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String statHouse = sm.getHouse() + "/" + sm.getMaxHouse();
        text_x = x + 650;
        text_y = y + 25;
        g2.drawString(statHouse, text_x, text_y);

        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String statReqHouse = "< Costs 100 Stone";
        text_x = x + 750;
        text_y = y + 25;
        g2.drawString(statReqHouse, text_x, text_y);

        // Stat build farm
        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String statFarm = sm.getFarm() + "/" + sm.getMaxFarm();
        text_x = x + 650;
        text_y = y + 55;
        g2.drawString(statFarm, text_x, text_y);

        g2.setColor(Color.white);
        g2.setFont(cambria_20);
        String statReqFarm = "< Costs 100 Wood";
        text_x = x + 750;
        text_y = y + 55;
        g2.drawString(statReqFarm, text_x, text_y);
    }

    public void drawStatWindow(int x, int y, int width, int height) {
        // Window background
        Color c=new Color(0f,0f,0f,.4f );
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 0, 0);

        // Window border
        g2.setColor(Color.lightGray);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, width, height, 0, 0);

        // Window text
        Font statFont = new Font("Cambria", Font.PLAIN, 30);
        g2.setColor(Color.white);
        g2.setFont(statFont);

        String windowText = "Wood: ";
        windowText += this.sm.getWood();
        windowText += "          Stone: ";
        windowText += this.sm.getStone();
        windowText += "          Food: ";
        windowText += this.sm.getFood();
        windowText += "          Workers: ";
        windowText += this.sm.getWorker();
        windowText += "          Houses: ";
        windowText += this.sm.getHouse();
        g2.drawString(windowText, getXforCenterText(windowText), 50);
    }

    public void drawMousePos() {

        g2.setColor(Color.white);
        g2.setFont(arial_10);
        int x = 0;
        int y = gp.SCREEN_HEIGHT;
        posString = "X: " + mh.getX() + " Y: " + mh.getY();
        g2.drawString(posString, x, y);
    }
}
