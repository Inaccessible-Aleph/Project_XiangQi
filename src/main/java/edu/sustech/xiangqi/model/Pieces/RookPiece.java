package edu.sustech.xiangqi.model.Pieces;

// 红车

import edu.sustech.xiangqi.model.Board;
import edu.sustech.xiangqi.model.Piece;

public class RookPiece extends Piece {
    public RookPiece() {
        super("车", true);
    }

    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow != toRow && fromCol != toCol) return false;

        if (fromRow == toRow) {
            int minCol = Math.min(fromCol, toCol);
            int maxCol = Math.max(fromCol, toCol);
            for (int c = minCol + 1; c < maxCol; c++) {
                if (board.getPieceAt(fromRow, c) != null) return false;
            }
        } else {
            int minRow = Math.min(fromRow, toRow);
            int maxRow = Math.max(fromRow, toRow);
            for (int r = minRow + 1; r < maxRow; r++) {
                if (board.getPieceAt(r, fromCol) != null) return false;
            }
        }

        return true;
    }
}
