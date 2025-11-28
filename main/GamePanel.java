package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Worker;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    
    // Screen Configurations
    final int ORIGINAL_TILE_SIZE = 16; // grid block size
    final int DISPLAY_SCALE = 1;
    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * DISPLAY_SCALE;
    public final int MAX_SCREEN_COL = 64;
    public final int MAX_SCREEN_ROW = 48;
    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    // Set setup config
    int FPS = 60; // change
    MouseHandler mh = new MouseHandler();

    // Initialize game elements
    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);
    public StatManager sm = new StatManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public ObjectPlacer oPlacer = new ObjectPlacer(this);
    public SuperObject obj[] = new SuperObject[100];
    public Worker worker[] = new Worker[100];


    public UI ui = new UI(this);
    TileManager tileM = new TileManager(this);
    
    public int gameState;
    public final int TITLE_STATE = 0;
    public final int PLAY_STATE = 1;
    public final int PAUSE_STATE = 2;
    public final int END_STATE = 3;
    

    // Initialize the window panel
    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.addMouseMotionListener(mh);
        this.addMouseListener(mh);
    }

    public void setupGame() {
        // Place objects
        oPlacer.setObject();
        
        // Place workers
        oPlacer.setWorker();
        
        gameState = TITLE_STATE;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Run the thread
    @Override
    public void run() {

        // Time variables
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {

            // Calculate delta
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                // Update information
                update();
                
                // Draw screen
                repaint();

                // Refresh time variables
                delta--;
            }

        }
    }
    
    public void update() {
        if (gameState == PLAY_STATE) {

            int w = sm.getAssignWoodInt(); 
            int s = sm.getAssignStoneInt();
            int f = sm.getAssignFoodInt();

            for (int i=0; i<sm.getWorkerInt(); i++) {
                if (worker[i].status == "idle") {
                    if (i < w) {
                    worker[i].setStatus("gatherWood");
                    worker[i].update();
                    sm.gatherWood();
                } else if (i < w+s) {
                    worker[i].setStatus("gatherStone");
                    worker[i].update();
                    sm.gatherStone();
                } else if (i < w+s+f) {
                    worker[i].setStatus("gatherFood");
                    worker[i].update();
                    sm.gatherFood();
                } else {
                    worker[i].setStatus("idle");
                    worker[i].update(); 
                }
            } else {
                    if (worker[i] != null) {
                        if (worker[i].assignedObject != null) {
                            worker[i].assignedObject.assigned = false;
                            worker[i].assignedObject = null;
                        }
                        worker[i].setStatus("idle");
                        worker[i].update();
                    }
                }
            }

            sm.decreaseFood();
        }

        if (gameState == PAUSE_STATE) {
            // nothing
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Title screen
        if (gameState == TITLE_STATE) {
            tileM.draw(g2);
            g2.setColor(new Color(0, 0, 0, 0.5f));
            g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            ui.draw(g2, sm, mh);
        }

        // Gameplay screen
        if (gameState == PLAY_STATE || gameState == PAUSE_STATE || gameState == END_STATE) {
            // Draw tiles
            tileM.draw(g2);

            // Draw objects
            for (int i=0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
        
            // Draw workers
            for (int i=0; i < worker.length; i++) {
                if (worker[i] != null) {
                    worker[i].draw(g2);
                }
            }

            // Pause&End screen exception
            if (gameState == PAUSE_STATE || gameState == END_STATE) {
                g2.setColor(new Color(0, 0, 0, 0.5f));
                g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            }

            // Draw UI
            ui.draw(g2, sm, mh);
        }

        // Destroy graphics object
        g2.dispose();
    }
}

