package main;

public class StatManager {
    GamePanel gp;
    private int threshold = 60;
    private int intervalWood = 0;
    private int statWood = 50;
    private int maxWood = 999;
    private int intervalStone = 0;
    private int statStone = 50;
    private int maxStone = 999;
    private int intervalFood = 0;
    private int intervalHunger = 0;
    private int statFood = 50;
    private int maxFood = 999;
    private int statHouse = 1;
    private int maxHouse = 10;
    private int statWorker = statHouse * 2;
    private int assignedWood = 0;
    private int maxAssignedWood = 6;
    private int assignedStone = 0;
    private int maxAssignedStone = 6;
    private int assignedFood = 0;
    private int maxAssignedFood = 8;
    private int statFarm = 0;
    private int maxFarm = 8;
    
    public StatManager(GamePanel gp) {
        this.gp = gp;
    }

    public void buildHouse() {
        if (statHouse < maxHouse) {
            if (statStone > 100) {
                statHouse++;
                statWorker = statHouse * 2;
                statStone -= 100;
                this.gp.oPlacer.addHouse();
                this.gp.oPlacer.addWorker();
            }
        }
    }

    public void buildFarm() {
        if (statFarm < maxFarm) {
            if (statWood > 100) {
                statFarm++;
                statWood -= 100;
                this.gp.oPlacer.addFarm();
            }
        }
    }

    public void gatherWood() {
        if (intervalWood >= threshold) {
            if (statWood < maxWood) {
                statWood++;
            }    
            intervalWood = 0;
        } else {
            intervalWood++;
        }
    }

    public void gatherStone() {
        if (intervalStone >= threshold) {
            if (statStone < maxStone) {
                statStone++;
            }    
            intervalStone = 0;
        } else {
            intervalStone++;
        }
    }

    public void gatherFood() {
        if (intervalFood >= threshold) {
            if (statFood < maxFood) {
                statFood++;
            }    
            intervalFood = 0;
        } else {
            intervalFood += 2;
        }
    }

    public void decreaseFood() {
        if (intervalHunger >= threshold * 6) {
            if (0 < statFood) {
                statFood -= statWorker;
            } else {
                gp.gameState = gp.END_STATE;
            }
            intervalHunger = 0;
        } else {
            intervalHunger++;
        }
    }

    public void assignWood() {
        if (!(statWorker == getTotalAssigned())) {
            if (assignedWood < maxAssignedWood) {
                assignedWood++;
            }
    }}
    public void assignStone() {
        if (!(statWorker == getTotalAssigned())) {
            if (assignedStone < maxAssignedStone) {
                assignedStone++;
            }
    }}
    public void assignFood() {
        if (!(statWorker == getTotalAssigned())) {
            if (statFarm > assignedFood) {
                assignedFood++; 
            }  
    }}
    public void dismissWood() {if (assignedWood > 0) {assignedWood--;}}
    public void dismissStone() {if (assignedStone > 0) {assignedStone--;}}
    public void dismissFood() {if (assignedFood > 0) {assignedFood--;}}

    public int getTotalAssigned() {
        return assignedFood + assignedWood + assignedStone;
    }

    // Getters
    public String getWood() {return Integer.toString(statWood);}
    public String getStone() {return Integer.toString(statStone);}
    public String getFood() {return Integer.toString(statFood);}
    public String getHouse() {return Integer.toString(statHouse);}
    public int getHouseInt() {return statHouse;}
    public String getWorker() {return Integer.toString(statWorker);}
    public int getWorkerInt() {return statWorker;}
    public String getFarm() {return Integer.toString(statFarm);}
    public int getFarmInt() {return statFarm;}
    public String getAssignWood() {return Integer.toString(assignedWood);}
    public int getAssignWoodInt() {return assignedWood;}
    public String getAssignStone() {return Integer.toString(assignedStone);}
    public int getAssignStoneInt() {return assignedStone;}
    public String getAssignFarm() {return Integer.toString(assignedFood);}
    public int getAssignFoodInt() {return assignedFood;}
    public String getMaxWood() {return Integer.toString(maxAssignedWood);}
    public String getMaxStone() {return Integer.toString(maxAssignedStone);}
    public String getMaxFood() {return Integer.toString(maxAssignedFood);}
    public String getMaxHouse() {return Integer.toString(maxHouse);}
    public int getMaxHouseInt() {return maxHouse;}
    public String getMaxFarm() {return Integer.toString(maxFarm);}
    public int getMaxFarmInt() {return maxFarm;}
}
