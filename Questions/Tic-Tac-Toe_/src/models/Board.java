package models;

import utils.Pair;

import java.util.ArrayList;
import java.util.List;



public class Board {
    public int size;
    public PlayingPiece[][] board;

    public  Board(int size) {
        board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int column, PlayingPiece playingPiece) {
        if(board[row][column] != null) return false;
        board[row][column] = playingPiece;
        return true;
    }

    public List<Pair> getFreCells() {
        List<Pair> freeCells = new ArrayList<>();

        for(int i = 0; i<size; i++) {
            for(int j = 0; j<size; j++) {
                if(board[i][j] != null) {
                    Pair newPair = new Pair(i,j);
                    freeCells.add(newPair);
                }
            }
        }
        return freeCells;
    }

    public void printBoard() {
        for(int i = 0; i < size; i++) {
            for(int j = 0;j <size; j++) {
                if(board[i][j] != null) {
                    System.out.print(board[i][j].pieceType.name() + "    ");
                } else {
                    System.out.print("     ");
                }

            }
        }
    }

}
