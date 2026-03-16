// See Instructions:
// http://apcsa-book.ausdk12.org/apcsa/r/cur/c4/L23_2D_arrays/exercises0.html?topic=c4%2FL23_2D_arrays.topic
public class GameBoard {
    //String[][][] board;
    String[][] board;
    public GameBoard(int rows, int cols){
        // board[0] = new String[rows][cols];
        // board[1] = new String[rows][cols];
        // for (int r = 0; r < rows; r++) {
        //     for (int c = 0; c < cols; c++) {
        //         board[0][r][c] = ".";
        //         board[1][r][c] = ".";
        //     }
        // }
        board = new String[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = ".";
            }
        }
    }

    public void setCell(int player, int row, int col, String c){
        // board[player][row][col] = c;
        if (player == 0) board[row][col] = c;
        else board[board.length-row][board[0].length-col] = c;
    }

    public void print(int player){
        if (player == 0) {
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[0].length; c++) {
                    System.out.print(board[r][c]);
                }
                System.out.print("\n");
            }
        }
        if (player == 1) {
            for (int r = board.length-1; r >= 0; r--) {
                for (int c = board[0].length-1; c >= 0 ; c--) {
                    System.out.print(board[r][c]);
                }
                System.out.print("\n");
            }
        }
    }

    public static void main(String[] args) {
        GameBoard g = new GameBoard(5, 4);
        g.print(0);
    }
}