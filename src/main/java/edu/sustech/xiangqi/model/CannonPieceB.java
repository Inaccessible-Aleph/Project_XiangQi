package edu.sustech.xiangqi.model;

//黑炮

public class CannonPieceB extends Piece {
    public CannonPieceB() {
        super("炮", false);
    }

    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {

        //TODO: special capture of cannon

        int row = Math.abs(toRow - fromRow);
        int col = Math.abs(toCol - fromCol);
        //TODO:other piece has blocked the path

        return row == 0 || col == 0;
    }
}
