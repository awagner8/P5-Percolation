import java.util.Stack;

public class PercolationDFS extends PercolationDefault {

    public PercolationDFS(int n) {
        super(n);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void search(int row, int col) {
        if(!inBounds(row, col) || !isOpen(row, col) || isFull(row, col)){
            return;
        }
        int[] colDelta = {0, 0, 1, -1};
        int[] rowDelta = {1, -1, 0, 0};
        Stack<int[]> stack = new Stack<int[]>();
        myGrid[row][col] = FULL;
        stack.push(new int[]{row, col});
        while(stack.size() !=0){
            int[] current = stack.pop();
            for(int k = 0; k < rowDelta.length; k++){
                row = current[0] + rowDelta[k];
                col = current[1] + colDelta[k];
                if(inBounds(row, col) && myGrid[row][col] == OPEN){
                    stack.push(new int[]{row, col});
                    myGrid[row][col] = FULL;
                }
            }
        }
    }

}
