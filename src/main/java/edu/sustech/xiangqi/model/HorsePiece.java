package edu.sustech.xiangqi.model;

//红马

public class HorsePiece extends Piece {
    public HorsePiece() {
        super("马", true);
    }

    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        //TODO:blocking horse leg

        int row = Math.abs(toRow - fromRow);
        int col = Math.abs(toCol - fromCol);
        return row == 2 && col == 1 || row == 1 && col == 2;
    }
}
