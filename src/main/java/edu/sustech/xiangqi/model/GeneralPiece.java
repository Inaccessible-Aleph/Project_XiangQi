package edu.sustech.xiangqi.model;

// 红帅

public class GeneralPiece extends Piece {
    public GeneralPiece() {
        super("帅", true);
    }

    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        int row = Math.abs(toRow - fromRow);
        int col = Math.abs(toCol - fromCol);
        if (!((row == 1 && col == 0) || (row == 0 && col == 1))) {
            return false;
        }

        //TODO:facing generals

        if (toCol < 3 || toCol > 5) {
            return false;
        }

        return toRow >= 7 && toRow <= 9;
    }
}

