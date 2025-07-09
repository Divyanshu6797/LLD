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
            boolean winnerIs = isWinner(inputRow, inputColumn, playerTurn);
            if(winnerIs) {
                gamingBoard.printBoard();
                return playerTurn.name;
            }
            playerList.addLast(playerTurn);

        }


        return "tie";
    }

    public boolean isWinner(int row, int column, Player player) {

        boolean winner = true;

        //row check
        for(int i = 0; i < gamingBoard.size ; i++) {
            if(gamingBoard.board[0][i] == null || gamingBoard.board[0][i].pieceType != player.playingPiece.pieceType) {
                winner = false;
                break;
            }
        }
        if(winner) return winner;
        winner = true;

        // column check
        for(int i = 0; i < gamingBoard.size ; i++) {
            if(gamingBoard.board[i][0] == null || gamingBoard.board[i][0].pieceType != player.playingPiece.pieceType) {
                winner = false;
                break;
            }
        }

        if(winner) return winner;
        winner = true;

        // Diagonal check
        for(int i = 0; i < gamingBoard.size ; i++) {
            if(gamingBoard.board[i][i] == null || gamingBoard.board[i][i].pieceType != player.playingPiece.pieceType) {
                winner = false;
                break;
            }
        }

        if(winner) return winner;
        winner = true;

        // Anti diagonal check
        for(int i = 0; i < gamingBoard.size ; i++) {
            if(gamingBoard.board[i][gamingBoard.size-1-i] == null || gamingBoard.board[i][gamingBoard.size-1-i].pieceType != player.playingPiece.pieceType) {
                winner = false;
                break;
            }
        }
        if(winner) return true;

        return winner;


    }




}
