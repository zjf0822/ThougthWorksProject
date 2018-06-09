package tw.game;

import java.util.Random;

/**
 * Created by zhoujifa-VAIO on 2018/6/9.
 */
public class World {
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
        randomState(RATE, SEED);

    }

    private void randomState(double rate, int seed){
        if(rate <= 0 || rate > 1){
            rate=RATE;
        }
        int times = (int)(ROW * COL * rate);

        Random random = new Random(seed);
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                int x = random.nextInt(ROW);
                int y = random.nextInt(COL);
                currentWorld[x][y] = CellState.Alive;
            }
        }
    }
}