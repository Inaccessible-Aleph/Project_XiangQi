package edu.sustech.xiangqi.model.Pieces;

//黑象

import edu.sustech.xiangqi.model.Board;
import edu.sustech.xiangqi.model.Piece;

public class ElephantPieceB extends Piece {
    public ElephantPieceB() {
        super("象", -3);
    }

    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        if (Math.abs(toRow - fromRow) != 2 || Math.abs(toCol - fromCol) != 2) return false;
        if (toRow < 0 || toRow > 4) return false; // 不能过河

        // 塞象眼判断
        int eyeRow = (fromRow + toRow) / 2;
        int eyeCol = (fromCol + toCol) / 2;
        if (board.getPieceAt(eyeRow, eyeCol) != null) return false;

        return true;
    }
}
