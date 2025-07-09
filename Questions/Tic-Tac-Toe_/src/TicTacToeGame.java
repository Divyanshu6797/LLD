import models.*;
import utils.Pair;

import java.util.*;

public class TicTacToeGame {

    Deque<Player> playerList;
    Board gamingBoard;


    public void initializeGame() {

        playerList = new LinkedList<>();
        PlayingPiece playingPieceX = new PlayingPiece(PieceType.X);
        PlayingPiece playingPieceO = new PlayingPiece(PieceType.O);

        Player playerX = new Player("iamX", playingPieceX);
        Player playerY = new Player("iamO", playingPieceO);

        playerList.add(playerX);
        playerList.add(playerY);

        gamingBoard = new Board(3);

        System.out.println("players list : " + playerList);




    }

    public String startGame() {

        boolean noWinner = true;
        while(noWinner) {
            Player playerTurn = playerList.removeFirst();

            gamingBoard.printBoard();
            List<Pair> freeCells= gamingBoard.getFreCells();
            if(freeCells.isEmpty()) {
                noWinner = false;
                System.out.print("no free cells");
                continue;

            }

            System.out.println("Player : " + playerTurn.getName() + "give location to put " + playerTurn.playingPiece + "in row,column format.");
            Scanner inputScanner = new Scanner(System.in);

            String input = inputScanner.nextLine();

            String []values = input.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn = Integer.valueOf(values[1]);


            boolean added = gamingBoard.addPiece(inputRow, inputColumn, playerTurn.playingPiece);

            if(!added) {
                playerList.addFirst(playerTurn);
                System.out.println("invalid coordinates");
                continue;

            }

            playerList.addLast(playerTurn);

        }


        return "tie";
    }




}
