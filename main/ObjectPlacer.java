package main;

import entity.Worker;
import object.ObjFarm;
import object.ObjHouse;
import object.ObjStone;
import object.ObjTree;

public class ObjectPlacer {
    
    GamePanel gp;
    StatManager sm;

    public ObjectPlacer(GamePanel gp) {
        this.gp = gp;
        this.sm = this.gp.sm;
    }

    public void setObject() {
        
        // Add trees
        gp.obj[0] = new ObjTree();
        gp.obj[0].worldX = 6 * gp.TILE_SIZE;
        gp.obj[0].worldY = 10 * gp.TILE_SIZE;

        gp.obj[1] = new ObjTree();
        gp.obj[1].worldX = 10 * gp.TILE_SIZE;
        gp.obj[1].worldY = 8 * gp.TILE_SIZE;

        gp.obj[2] = new ObjTree();
        gp.obj[2].worldX = 12 * gp.TILE_SIZE;
        gp.obj[2].worldY = 12 * gp.TILE_SIZE;

        gp.obj[3] = new ObjTree();
        gp.obj[3].worldX = 15 * gp.TILE_SIZE;
        gp.obj[3].worldY = 8 * gp.TILE_SIZE;

        gp.obj[4] = new ObjTree();
        gp.obj[4].worldX = 8 * gp.TILE_SIZE;
        gp.obj[4].worldY = 14 * gp.TILE_SIZE;

        gp.obj[5] = new ObjTree();
        gp.obj[5].worldX = 20 * gp.TILE_SIZE;
        gp.obj[5].worldY = 8 * gp.TILE_SIZE;

        // Add stones
        gp.obj[6] = new ObjStone();
        gp.obj[6].worldX = 43 * gp.TILE_SIZE;
        gp.obj[6].worldY = 5 * gp.TILE_SIZE;

        gp.obj[7] = new ObjStone();
        gp.obj[7].worldX = 47 * gp.TILE_SIZE;
        gp.obj[7].worldY = 6 * gp.TILE_SIZE;

        gp.obj[8] = new ObjStone();
        gp.obj[8].worldX = 50 * gp.TILE_SIZE;
        gp.obj[8].worldY = 6 * gp.TILE_SIZE;
        
        gp.obj[9] = new ObjStone();
        gp.obj[9].worldX = 52 * gp.TILE_SIZE;
        gp.obj[9].worldY = 7 * gp.TILE_SIZE;

        gp.obj[10] = new ObjStone();
        gp.obj[10].worldX = 54 * gp.TILE_SIZE;
        gp.obj[10].worldY = 9 * gp.TILE_SIZE;

        gp.obj[11] = new ObjStone();
        gp.obj[11].worldX = 57 * gp.TILE_SIZE;
        gp.obj[11].worldY = 13 * gp.TILE_SIZE;

        // Add house
        gp.obj[12] = new ObjHouse();
        gp.obj[12].worldX = 30 * gp.TILE_SIZE;
        gp.obj[12].worldY = 25 * gp.TILE_SIZE;
    }

    public void addHouse() {
        int i = 12 + sm.getHouseInt();
        gp.obj[i] = new ObjHouse();
        int x = ((int)(Math.random() * (40 - 20) + 20)) * gp.TILE_SIZE;
        int y = ((int)(Math.random() * (30 - 20) + 20)) * gp.TILE_SIZE;
        gp.obj[i].worldX = x;
        gp.obj[i].worldY = y;
    }

    public void addFarm() {
        int i = 22 + sm.getFarmInt();
        gp.obj[i] = new ObjFarm();
        int x = ((int)(Math.random() * (50 - 10) + 10)) * gp.TILE_SIZE;
        int y = ((int)(Math.random() * (40 - 30) + 30)) * gp.TILE_SIZE;
        gp.obj[i].worldX = x;
        gp.obj[i].worldY = y;
    }

    public void setWorker() {
        for (int i=0; i<sm.getWorkerInt(); i++) {
            gp.worker[i] = new Worker(gp);
            gp.worker[i].worldX = gp.MAX_SCREEN_COL * gp.TILE_SIZE / 2;
            gp.worker[i].worldY = gp.MAX_SCREEN_ROW * gp.TILE_SIZE / 2;
        }
    }

    public void addWorker() {
        int i = sm.getWorkerInt() - 1;

        gp.worker[i-1] = new Worker(gp);
        gp.worker[i-1].worldX = gp.MAX_SCREEN_COL * gp.TILE_SIZE / 2;
        gp.worker[i-1].worldY = gp.MAX_SCREEN_ROW * gp.TILE_SIZE / 2;

        gp.worker[i] = new Worker(gp);
        gp.worker[i].worldX = gp.MAX_SCREEN_COL * gp.TILE_SIZE / 2;
        gp.worker[i].worldY = gp.MAX_SCREEN_ROW * gp.TILE_SIZE / 2;
    }
}
