package tw.game;

import javax.swing.*;
import java.util.Random;

/**
 * Created by zhoujifa-VAIO on 2018/6/9.
 */
public class World extends JPanel implements Runnable{
    private static enum CellState{
        Alive,
        Dead;
    }
    private final int ROW;
    private final int COL;
    private final double RATE = 0.3;
    private final int SEED = 47;
    private CellState [][] currentWorld;
    private CellState [][] nextWorld;

    public World(int row, int col){
        this.ROW = row;
        this.COL = col;
        initWorld();
        randomState(RATE, SEED);

    }

    private void initWorld() {
        currentWorld = new CellState[ROW][COL];
        for(int i = 0;i < ROW;i++) {
            for(int j = 0;j < COL;j++) {
                currentWorld[i][j] = CellState.Dead;
            }
        }
        nextWorld = new CellState[ROW][COL];
        for(int i = 0;i < ROW;i++) {
            for(int j=0;j<COL;j++) {
                nextWorld[i][j] = CellState.Dead;
            }
        }
    }

    private void randomState(double rate, int seed){
        if(rate <= 0 || rate > 1){
            rate = RATE;
        }
        int times = (int)(ROW * COL * rate);

        Random random = new Random(seed);
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                int x = random.nextInt(ROW);
                int y = random.nextInt(COL);
                if(isVaild(x, y)) {
                    currentWorld[x][y] = CellState.Alive;
                }
            }
        }
    }

    public void run(){
        while(true) {
            synchronized (this) {
                for (int i = 0; i < ROW; i++) {
                    for (int j = 0; j < COL; j++) {
                        nextGeneration(i, j);
                    }
                }
                currentWorld = nextWorld;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isVaild(int x,int y){
        if((x >= 0 && x < ROW) && (y >=0 && y < COL)){
            return true;
        }
        return false;
    }

    private void nextGeneration(int x, int y){
        int countState = 0;
        if(isVaild(x-1, y-1)&&(currentWorld[x-1][y-1]==CellState.Alive)){
            countState++;
        }
        if(isVaild(x,y-1) && (currentWorld[x][y-1] == CellState.Alive)){
            countState++;
        }
        if(isVaild(x+1, y-1) && (currentWorld[x+1][y-1] == CellState.Alive)){
            countState++;
        }
        if(isVaild(x+1, y) && (currentWorld[x+1][y] == CellState.Alive)){
            countState++;
        }
        if(isVaild(x+1, y+1) && (currentWorld[x+1][y+1] == CellState.Alive)){
            countState++;
        }
        if(isVaild(x,y+1) && (currentWorld[x][y+1] == CellState.Alive)){
            countState++;
        }
        if(isVaild(x-1, y+1) && (currentWorld[x-1][y+1] == CellState.Alive)){
            countState++;
        }
        if(isVaild(x-1, y) && (currentWorld[x-1][y] == CellState.Alive)){
            countState++;
        }
        if(countState == 3) {
            nextWorld[x][y] = CellState.Alive;
        }
        else if(countState == 2){
            nextWorld[x][y] = currentWorld[x][y];
        }
        else {
            nextWorld[x][y] = CellState.Dead;
        }

    }
}