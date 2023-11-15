import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDefault{

    public PercolationBFS(int n) {
        super(n);
        //TODO Auto-generated constructor stub
    }
    
    @Override
    protected void search(int row, int col){
        if(!inBounds(row, col) || !isOpen(row, col) || isFull(row, col)){
            return;
        }
        int[] colDelta = {0, 0, 1, -1};
        int[] rowDelta = {1, -1, 0, 0};

        Queue<int[]> queue = new LinkedList<>();
        myGrid[row][col] = FULL;    
        queue.add(new int[]{row, col});
        while(queue.size() != 0){
            int[] current = queue.remove();
            for(int k = 0; k < rowDelta.length; k++){
                row = current[0] + rowDelta[k];
                col = current[1] + colDelta[k];
                if(inBounds(row, col) && myGrid[row][col] == OPEN){
                    queue.add(new int[]{row, col});
                    myGrid[row][col] = FULL;
                }
            }
    }

}
}
