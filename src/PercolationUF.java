public class PercolationUF implements IPercolate {
    
    private boolean[][] myGrid;
    private int myOpenCount;
    private IUnionFind myFinder;
    private final int VTOP; 
    private final int VBOTTOM;

    public PercolationUF(IUnionFind finder, int size){
        myGrid = new boolean[size][size];
        finder.initialize(size*size + 2);
        myFinder = finder;
        myOpenCount = 0;
        VTOP = size*size;
        VBOTTOM = size*size + 1;
    }
    private boolean inBounds(int row, int col){
        return row >= 0 && row < myGrid.length && col >= 0 && col < myGrid[0].length;
    }
    @Override
    public void open(int row, int col){
        if(!inBounds(row,col)){
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        if(myGrid[row][col]){
            return;
        }        
        myGrid[row][col] = true;
        myOpenCount++;
        if(inBounds(row-1,col) && isOpen(row-1,col)){
            myFinder.union(row*myGrid.length + col, (row-1)*myGrid.length + col);
        }
        if(inBounds(row+1,col) && isOpen(row+1,col)){
            myFinder.union(row*myGrid.length + col, (row+1)*myGrid.length + col);
        }
        if(inBounds(row, col - 1) && isOpen(row, col - 1)){
            myFinder.union(row*myGrid.length + col, row*myGrid.length + col - 1);
        }
        if(inBounds(row, col + 1) && isOpen(row, col + 1)){
            myFinder.union(row*myGrid.length + col, row*myGrid.length + col + 1);
        }
        if(row == 0){
            myFinder.union(VTOP, row*myGrid.length + col);
        }
        if(row == myGrid.length - 1){
            myFinder.union(VBOTTOM, row*myGrid.length + col);
        }
        
    }
    @Override
    public boolean isOpen(int row, int col){
        if(!inBounds(row,col)){
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        return myGrid[row][col];
    }
    @Override
    public boolean isFull(int row, int col){
        if(!inBounds(row,col)){
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        return myFinder.connected(VTOP, row*myGrid.length + col);
    }
    @Override
    public boolean percolates(){
        return myFinder.connected(VTOP, VBOTTOM);
    }
    @Override
    public int numberOfOpenSites(){
        return myOpenCount;
    }
}
