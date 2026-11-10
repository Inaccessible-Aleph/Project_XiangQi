package edu.sustech.xiangqi.model;

//红炮

public class CannonPiece extends Piece {
    public CannonPiece() {
        super("炮", true);
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
