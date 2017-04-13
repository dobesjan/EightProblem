package eightproblem;

/**
 *
 * @author Honza
 */
public class PuzzleStep {
    public int[][] puzzle;
    public int[] spacePosition;
    public PuzzleStep parent;
    public boolean equals = false;
    
    public PuzzleStep(int[][] puzzle, int[] spacePosition, PuzzleStep parent) {
        this.puzzle = puzzle;
        this.spacePosition = spacePosition;
        this.parent = parent;
    }
}
