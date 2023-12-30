import java.util.Scanner;

public class TicTacToe {

    Scanner sc = new Scanner(System.in);
    char player = sc.next().charAt(0);
    static char[][] board = new char[3][3];
    public TicTacToe(){
        while(player != 'X' && player != 'O'){
            System.out.println("Please enter either X or O");
            player = sc.next().charAt(0);
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = ' ';
            }
        }
        layout();
    }
    public void layout(){

        int count = 9;
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++ ){
                System.out.print(board[row][col]);
                if(col != board[0].length - 1){
                    System.out.print( " | ");
                }
            }
            System.out.println();
            if(row != board.length - 1) {
                System.out.println("---------");
            }
        }
    }

    public void input(){
        int count = 9;
        while(count != 0){
            System.out.println("Enter row and column ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            if(isFilled(row,col)) {
                System.out.println("The space is already filled!!");
                while (isFilled(row, col)) {
                    row = sc.nextInt();
                    col = sc.nextInt();
                    System.out.println("The space is already filled!!");
                }
            }
            if(player == 'X') {
                if(row >= 0 && row < board.length && col >= 0 && col < board[0].length){
                    board[row][col] = player;
                    if(isWin(row,col)){
                        layout();
                        System.out.println(player + " won the match");
                        break;
                    }
                    player = 'O';
                }else{
                    System.out.println("Out of Bound");
                }
            }else{
                if(row >= 0 && row < board.length && col >= 0 && col < board[0].length ){
                    board[row][col] = player;
                    if(isWin(row,col)){
                        layout();
                        System.out.println(player + " won the match");
                        break;
                    }
                    player = 'X';
                }else{
                    System.out.println("Out of Bound");
                }
            }


            count--;
            layout();
        }
        if(count == 0) {
            System.out.println("Tie");
        }
    }

    public boolean isWin(int row, int col){
        return rowCheck(row) || colCheck(col) || leftDiagonalCheck() || rightDiagonalCheck();
    }

    private boolean rowCheck(int row){
        for(int i = 0; i < board.length; i++) {
            if (board[row][i] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean colCheck(int col){
        for(int i = 0; i < board[0].length; i++) {
            if (board[i][col] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean leftDiagonalCheck(){
        for (int i = 0; i < board.length; i++) {
            if(board[i][i] != player){
                return false;
            }
        }
        return true;
    }

    private boolean rightDiagonalCheck(){
        for(int i = 0; i < board.length; i++){
            if(board[i][board.length - 1 - i] != player){
                return false;
            }
        }

        return true;
    }

    public boolean isFilled(int row, int col){
        return board[row][col] == 'X' || board[row][col] == 'O';
    }
    public static void main(String[] args) {
        System.out.println("Enter X or O : ");
        TicTacToe obj = new TicTacToe();
        obj.input();
    }
}
