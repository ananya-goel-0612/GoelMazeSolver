/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // Push the parent cell of the endCell to the stack
        // and continue pushing parentCells until it reaches the startCell
        Stack<MazeCell> route = new Stack<MazeCell>();

        MazeCell cell = maze.getEndCell();
        MazeCell start = maze.getStartCell();

        while (cell != start) {
            route.push(cell);
            cell = cell.getParent();
        }

        route.push(start);

        // ArrayList to return the correct path
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();

        // Traverse through the stack to add to the ArrayList in the correct order
        while (!route.empty()) {
            solution.add(route.pop());
        }

        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell cell = maze.getStartCell();
        Stack<MazeCell> toVisit = new Stack<MazeCell>();

        // Makes sure that it continues until it reaches the end cell
        while (cell != maze.getEndCell()) {
            // Makes sure the cell is valid, then add it to the Stack, setExplored to true, and
            // set the current cell as its parent cell
            int row = cell.getRow();
            int col = cell.getCol();

            // Check all the surrounding cells to see if there is a valid path
            if (maze.isValidCell(row - 1, col)) {
                maze.getCell(row - 1, col).setExplored(true);
                maze.getCell(row - 1, col).setParent(cell);
                toVisit.push(maze.getCell(row - 1, col));
            }

            if (maze.isValidCell(row, col + 1)) {
                maze.getCell(row, col + 1).setExplored(true);
                maze.getCell(row, col + 1).setParent(cell);
                toVisit.push(maze.getCell(row, col + 1));
            }

            if (maze.isValidCell(row + 1, col)) {
                maze.getCell(row + 1, col).setExplored(true);
                maze.getCell(row + 1, col).setParent(cell);
                toVisit.push(maze.getCell(row + 1, col));
            }

            if (maze.isValidCell(row, col - 1)) {
                maze.getCell(row, col - 1).setExplored(true);
                maze.getCell(row, col - 1).setParent(cell);
                toVisit.push(maze.getCell(row, col - 1));
            }

            cell = toVisit.pop();
        }

        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell cell = maze.getStartCell();
        Queue<MazeCell> toVisit = new LinkedList<MazeCell>();

        // Continues until it reaches the end cell
        while (cell != maze.getEndCell()) {
            // Makes sure the cell is valid, then add it to the Queue, setExplored to true, and
            // set the current cell as its parent cell
            int row = cell.getRow();
            int col = cell.getCol();

            if (maze.isValidCell(row - 1, col)) {
                maze.getCell(row - 1, col).setExplored(true);
                maze.getCell(row - 1, col).setParent(cell);
                toVisit.add(maze.getCell(row - 1, col));
            }

            if (maze.isValidCell(row, col + 1)) {
                maze.getCell(row, col + 1).setExplored(true);
                maze.getCell(row, col + 1).setParent(cell);
                toVisit.add(maze.getCell(row, col + 1));
            }

            if (maze.isValidCell(row + 1, col)) {
                maze.getCell(row + 1, col).setExplored(true);
                maze.getCell(row + 1, col).setParent(cell);
                toVisit.add(maze.getCell(row + 1, col));
            }

            if (maze.isValidCell(row, col - 1)) {
                maze.getCell(row, col - 1).setExplored(true);
                maze.getCell(row, col - 1).setParent(cell);
                toVisit.add(maze.getCell(row, col - 1));
            }

            cell = toVisit.remove();
        }

        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
