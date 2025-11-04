public class Game {

    private Player playerX;
    private Player playerO;
    private TTTBoard board;
    private Player currentPlayer;

    public Game(){
        playerX= new Player("X");
        playerO= new Player("O");
        board = new TTTBoard();
        currentPlayer= playerX;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchTurn(){
        if(currentPlayer==playerX){
            currentPlayer=playerO;
        }
        else{
            currentPlayer=playerX;
        }
    }

    public TTTBoard getBoard() {
        return board;
    }

    public void resetGame(){
        board = new TTTBoard();
        currentPlayer= playerX;
    }
}