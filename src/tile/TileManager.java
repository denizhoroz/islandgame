package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW];

        loadMap("/maps/map.txt");
        loadTile();
    }

    public void loadTile() {
        // grass tile
        tile[0] = new Tile();
        tile[0].color = Color.GREEN;
        
        // rock tile
        tile[1] = new Tile();
        tile[1].color = Color.GRAY; 
        tile[1].collision = true;

        // water tile
        tile[2] = new Tile();
        tile[2].color = Color.CYAN;
        tile[2].collision = true;

        // sand tile
        tile[3] = new Tile();
        tile[3].color = Color.YELLOW;
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
            int tileNum = mapTileNum[col][row];

            g2.setColor(tile[tileNum].color);
            g2.fillRect(x, y, gp.TILE_SIZE, gp.TILE_SIZE);

            col++;
            x += gp.TILE_SIZE;

            if (col == gp.MAX_SCREEN_COL) {
                col = 0;
                x = 0;
                row++;
                y += gp.TILE_SIZE;
            }
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
                String line = br.readLine();

                while (col < gp.MAX_SCREEN_COL) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.MAX_SCREEN_COL) {
                    col = 0;
                    row++;
                } 
            }
            br.close();

        } catch(Exception e) {

        }
    }


}
