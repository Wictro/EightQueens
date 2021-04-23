package com.company;

/*
* A simple backtracking solution to the eight queens problem
 */
public class EightQueens {
    static int counter = 0;

    /*
    * Entry
     */
    public static void main(String[] args) {
        generateSolutions(14, false);
    }

    /*
    * Makes the customary input checks and calls the main recursive algorithm
     */
    public static void generateSolutions(int size, boolean print){
        int[][] template  = new int[size][size];
        addQueen(template, 0, print);
        System.out.printf("There is a total of %d possible configurations.", counter);
    }

    /*
    * Algorithm that places a queen at some column in a row, and proceeds
    * to put queens in the subsequent rows while avoiding attacks.
    * This is done until it is possible to put a total of 8 queens, at which point
    * the solution is recorded and, optionally, printed.
     */
    static void addQueen(int[][] board, int row, boolean print){
        //if we placed 8 queens then we're finished
        if(row == board.length) {
            if(print) printMatrix(board);
            counter++;
            return;
        }

        //try and find a spot in a column in that row
        for(int i = 0; i < board.length; i++){
            //see if a col in this row is available
            if(isGood(row, i, board)){
                board[row][i] = 1;
                addQueen(board, row+1, print);
            }
            //empty the spot again
            board[row][i] = 0;
        }
    }

    /*
    * Ensures that placing a queen at the specified spot doesn't raise any conflicts
     */
    private static boolean isGood(int row, int col, int[][] board){
        return isGoodColumn(row, col, board) && checkFirstDiagonal(row, col, board) && checkSecondDiagonal(row, col, board);
    }

    /*
    * Ensure the column is clear
     */
    private static boolean isGoodColumn(int row, int col, int[][] board){
        for(int i = 0; i < row; i++){
            if(board[i][col] == 1)
                return false;
        }
        return true;
    }

    /*
    * Ensure one diagonal
     */
    private static boolean checkFirstDiagonal(int row, int col, int[][] board){
        for(int i = col, j = 0; i < board.length && row+j < board.length; i++, j++){
            if(board[row + j][i] == 1)
                return false;
        }

        for(int i = col, j = 0; i >= 0 && row-j >= 0; i--, j++){
            if(board[row - j][i] == 1)
                return false;
        }
        return true;
    }

    /*
    * Ensure the other diagonal
     */
    private static boolean checkSecondDiagonal(int row, int col, int[][] board){
        for(int i = col, j = 0; i < board.length && row-j >= 0; i++, j++){
            if(board[row - j][i] == 1)
                return false;
        }

        for(int i = col, j = 0; i >= 0 && row+j < board.length; i--, j++){
            if(board[row + j][i] == 1)
                return false;
        }
        return true;
    }

    /*
    * Prints the matrix
     */
    private static void printMatrix(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
