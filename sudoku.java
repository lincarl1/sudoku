public class sudoku {

    //Function to check whether the board is empty or not
    public static boolean isBoardEmpty(int[][] board)
    {
        for (int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                if(board[i][j] == 0)
                {
                    return false;
                }
            }
        }

        return true;
    }

    //Solves the Sudoku Board
    public static boolean solveSudokuBoard(int[][] board, int length)
    {
        int row = -1;
        int col = -1;
        
        //Checks to see if the board is empty
        if(isBoardEmpty(board))
        {
            return true;
        }

        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                if(board[i][j] == 0)
                {
                    //sets both row and column equal to the position of value
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        for(int number = 1; number < 10; number++)
        {
            if(safeValue(board, row, col, number) == true)
            {
                board[row][col] = number;
                if(solveSudokuBoard(board, length) == true)
                {
                    return true;
                }
                else
                {
                    board[row][col] = 0;
                }
            }
        }

        return false;
    }

    //Checks to see if the inserted value is valid
    //Inputs are the board, the row and column position of the empty value,
    //and the number that is being tested
    public static boolean safeValue(int[][] board, int row, int col, int number)
    {
        //Check if the value exists in the row
        for(int columnVal = 0; columnVal < board.length; columnVal++)
        {
            if(board[row][columnVal] == number)
            {
                return false;
            }
        }

        //Check if the value exists in the column
        for(int rowVal = 0; rowVal < board.length; rowVal++)
        {
            if(board[rowVal][col] == number)
            {
                return false;
            }
        }

        //Checks to see if the value exists within the rectangle
        int sqrt = (int) Math.sqrt(board.length);
        int rowStart = row - (row % sqrt);
        int colStart = col - (col % sqrt);
        for(int i = rowStart; i < rowStart + sqrt; i++)
        {
            for(int j = colStart; j < colStart + sqrt; j++)
            {
                if(board[i][j] == number)
                {
                    return false;
                }
            }
        }

        //Meets all requirements
        return true;
    }

    //Prints the Sudoku Board
    public static void printBoard(int[][] board, int length)
    {
        for(int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    //Driver Function
    public static void main(String[] args)
    {
        // Pre-Determined Sudoku Board (N x N)
        int[][] board = new int [][] {
            { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, 
            { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, 
            { 0, 8, 7, 0, 0, 0, 0, 3, 1 }, 
            { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, 
            { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, 
            { 0, 5, 0, 0, 9, 0, 6, 0, 0 }, 
            { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, 
            { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, 
            { 0, 0, 5, 2, 0, 6, 3, 0, 0 } 
        };

        //The length of the Sudoku Board (N)
        int length = board.length; 

        if(solveSudokuBoard(board, length) == true)
        {
            printBoard(board, length);
        }
        else
        {
            System.out.println("There is no solution for the Sudoku Board!");
        }
    }
}