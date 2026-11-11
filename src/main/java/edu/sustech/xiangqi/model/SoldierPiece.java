package edu.sustech.xiangqi.model;

//红兵

public class SoldierPiece extends Piece {
    public SoldierPiece() {
        super("兵", true);
    }

    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        int row = toRow - fromRow;
        int col = toCol - fromCol;
        if (fromRow >= 5) {
            return col == 0 && row == -1;
        } else {
            return (col == 1 && row == 0) || (col == 0 && row == -1) || (col == -1 && row == 0);
        }
    }
}
