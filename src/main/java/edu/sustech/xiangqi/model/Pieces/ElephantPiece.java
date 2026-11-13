package edu.sustech.xiangqi.model.Pieces;

//红相

import edu.sustech.xiangqi.model.Board;
import edu.sustech.xiangqi.model.Piece;

public class ElephantPiece extends Piece {
    public ElephantPiece() {
        super("相", true);
    }

    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        if (Math.abs(toRow - fromRow) != 2 || Math.abs(toCol - fromCol) != 2) return false;
        if (toRow < 5 || toRow > 9) return false; // 不能过河

        // 塞象眼判断
        int eyeRow = (fromRow + toRow) / 2;
        int eyeCol = (fromCol + toCol) / 2;
        if (board.getPieceAt(eyeRow, eyeCol) != null) return false;

        return true;
    }
}
