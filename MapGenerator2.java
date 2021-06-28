
package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
public class MapGenerator2 {
    public int map[][];
    public int bricksWidth;
    public int bricksHeight;
    public MapGenerator2(int row , int col){ //accepts row and column from the object instantiation
        map = new int[row][col];
        for(int i = 0; i < map.length; i++){
            for(int j = 0;j < map[0].length; j++){
                map[i][j] = 1; //giving a random value of 1 to use the values later for visibility of the bricks
            }
        }
        bricksWidth = 740/col; //total width of container of bricks is set to 740
        bricksHeight = 200/row;//total height of container of bricks is set to 200
    }
    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    
                    //using fillrect to create a corresponding brick for the array 
                    g.setColor(Color.white);
                    g.fillRect(j * bricksWidth + 150, i * bricksHeight + 80, bricksWidth, bricksHeight);
                    //using drawRect to draw the borders in between the bricks
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * bricksWidth + 150, i * bricksHeight + 80, bricksWidth, bricksHeight);

                }
            }

        }
    }
    public void setBricksValue(int value,int row,int col)
    {
        map[row][col] = value;

    }
}
