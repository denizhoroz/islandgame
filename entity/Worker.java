package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;
import object.SuperObject;

public class Worker extends Entity {  
    GamePanel gp;
    public String status;
    public SuperObject assignedObject;

    public Worker(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        direction = "still";
        status = "idle";
        solidArea = new Rectangle(0, 0, gp.TILE_SIZE, gp.TILE_SIZE);
        speed = 16;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAction() {

        switch (status) {
            case "idle":
                actionCooldownCounter++;
                if (actionCooldownCounter == 2) {
                    Random random = new Random();
                    int i = random.nextInt(16) + 1;

                    if (i <= 1) {
                        direction = "up";
                    } else if (i <= 2) {
                        direction = "down";
                    } else if (i <= 3) {
                        direction = "left";
                    } else if (i <= 4) {
                        direction = "right";
                    } else if (i <= 16) {
                        direction = "still";
                    }

                    actionCooldownCounter = 0;
                }
                break;
            case "gatherWood":
                    if (assignedObject == null) {
                        for (int i=0; i<6; i++) {
                            if (gp.obj[i] != null) {
                                if (gp.obj[i].assigned == false) {
                                    assignedObject = gp.obj[i];
                                    gp.obj[i].assigned = true;
                                    direction = getDirectionToTarget(assignedObject.worldX, assignedObject.worldY);
                                    break;
                                }
                            }
                        }
                    } else {
                        direction = getDirectionToTarget(assignedObject.worldX, assignedObject.worldY);
                    }
                break;
            case "gatherStone":
                    if (assignedObject == null) {
                        for (int i=6; i<12; i++) {
                            if (gp.obj[i] != null) {
                                if (gp.obj[i].assigned == false) {
                                    assignedObject = gp.obj[i];
                                    gp.obj[i].assigned = true;
                                    direction = getDirectionToTarget(assignedObject.worldX, assignedObject.worldY);
                                    break;
                                }
                            }

                        }
                    } else {
                        direction = getDirectionToTarget(assignedObject.worldX, assignedObject.worldY);
                    }
                break;
            case "gatherFood":
                    if (assignedObject == null) {
                        for (int i=22; i<30; i++) {
                            if (gp.obj[i] != null) {
                                if (gp.obj[i].assigned == false) {
                                    assignedObject = gp.obj[i];
                                    gp.obj[i].assigned = true;
                                    direction = getDirectionToTarget(assignedObject.worldX, assignedObject.worldY);
                                    break;
                                }
                            }

                        }
                    } else {
                        direction = getDirectionToTarget(assignedObject.worldX, assignedObject.worldY);
                    }
                break;
            default:
                break;
        }
    }

    
}
