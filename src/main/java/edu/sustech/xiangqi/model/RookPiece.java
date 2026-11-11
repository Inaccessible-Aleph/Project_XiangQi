package edu.sustech.xiangqi.model;

// 红车

public class RookPiece extends Piece {
    public RookPiece() {
        super("车", true);
    }

    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        //TODO:other piece has blocked the path

        int row = Math.abs(toRow - fromRow);
        int col = Math.abs(toCol - fromCol);
        return row == 0 || col == 0;
    }
}
