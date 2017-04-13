package eightproblem;

import java.util.ArrayList;

/**
 *
 * @author Honza
 */
public class PuzzleSolver {
    public int width;
    public int height;
    public int zero;
    public ArrayList<String> steps;
    public String target;
    public int stepCount;
    public PuzzleStep result;
    
    public PuzzleSolver() {
        this.width = 3;
        this.height = 3;
        this.zero = 0;
        this.steps = new ArrayList<String>();
        this.target = "";
        this.stepCount = 0;
        this.result = null;
    }
    
    public void printPuzzle(int[][] puzzle) {
        for (int[] row : puzzle) {
            for (int item : row) {
                System.out.print(item);
            }
            System.out.println();
            System.out.println("----------------");
        }
    }
    
    public void printResultProccess() {
        System.out.println("##########################");
        PuzzleStep item = this.result;
        while (true) {
            this.printPuzzle(item.puzzle);
            item = item.parent;
            if (item == null) {
                break;
            }
        }
        System.out.println("##########################");
    }
    
    public PuzzleStep getNextStep(PuzzleStep step, int x, int y) {
        int[][] nstep = step.puzzle;
        nstep[step.spacePosition[1]][step.spacePosition[0]] = nstep[step.spacePosition[1] + y][step.spacePosition[0] + x];
        nstep[step.spacePosition[1] + y][step.spacePosition[0] + x] = nstep[step.spacePosition[1]][step.spacePosition[0]];
        String strStep = this.convertPuzzleToString(nstep);
        if (strStep.equals(this.target)) {
            this.result = new PuzzleStep(nstep, new int[] {step.spacePosition[0] + x, step.spacePosition[1] + y}, step);
            step.equals = true;
            return step;
        }
        
        if (this.steps.indexOf(strStep) == -1) {
            this.steps.add(strStep);
            return new PuzzleStep(nstep, new int[] {step.spacePosition[0] + x, step.spacePosition[1] + y}, step);
        } else {
            return null;
        }
    }
    
    public ArrayList<PuzzleStep> getNextSteps(PuzzleStep step) {
        ArrayList<PuzzleStep> nextSteps = new ArrayList<PuzzleStep>();
        
        if (step.spacePosition[0] != 0) { // x - 1
            PuzzleStep temp = this.getNextStep(step, -1, 0);
            if (temp.equals) {
                return null;
            }
            
            if (temp != null) {
                nextSteps.add(temp);
            }
        }
        
        if (step.spacePosition[1] != 0) { // y - 1
            PuzzleStep temp = this.getNextStep(step, 0, -1);
            if (temp.equals) {
                return null;
            }
            
            if (temp != null) {
                nextSteps.add(temp);
            }
        }
        
        if (step.spacePosition[0] != this.width - 1) { // x + 1
            PuzzleStep temp = this.getNextStep(step, 1, 0);
            if (temp.equals) {
                return null;
            }
            
            if (temp != null) {
                nextSteps.add(temp);
            }
        }
        
        if (step.spacePosition[1] != this.height) { // y + 1
            PuzzleStep temp = this.getNextStep(step, 0, 1);
            if (temp.equals) {
                return null;
            }
            
            if (temp != null) {
                nextSteps.add(temp);
            }
        }
        
        return nextSteps;
    }
    
    public String convertPuzzleToString(int[][] puzzle) {
        String s = "";
        for (int[] row : puzzle) {
            for (int item : row) {
                s += Integer.toString(item) + ":";
            }
        }
        return s.substring(s.length() - 1);
    }
    
    public void solve(int[][] puzzleMap,int[][] targetMap) {
        this.target = this.convertPuzzleToString(targetMap);
        
        int[] position = null;
        
        for (int i = 0; i < puzzleMap.length; i++) {
            for (int item : puzzleMap[i]) {
                if (item == 0) {
                    position = new int[] {item, i};
                    break;
                }
            }
        }
        
        PuzzleStep step = new PuzzleStep(puzzleMap, position, null);
        
        if (!this.convertPuzzleToString(puzzleMap).equals(this.target)) {
            ArrayList<PuzzleStep> queue = new ArrayList<PuzzleStep>();
            queue.add(step);
            
            while (true) {
                this.stepCount += 1;
                System.out.println("Step: " + Integer.toString(this.stepCount));
                
                ArrayList<PuzzleStep> newQueue = new ArrayList<PuzzleStep>();
                
                for (PuzzleStep s : queue) {
                    ArrayList<PuzzleStep> temp = this.getNextSteps(s);
                    if (temp != null) {
                        newQueue.addAll(temp);
                    } else {
                        newQueue = null;
                        this.result = s;
                        break;
                    }
                }
                
                if (newQueue == null) {
                    break;
                }
                
                queue = newQueue;
            }
        } else {
            this.result = step;
        }
        
        this.printResultProccess();
        System.out.println("Steps: " + Integer.toString(this.stepCount));
    }
}
