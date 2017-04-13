package eightproblem;

/**
 *
 * @author Honza
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PuzzleSolver solver = new PuzzleSolver();
        solver.solve(new int[][] {{8, 0, 2}, {1, 6, 7}, {4, 5, 3}}, new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
    }
    
}
