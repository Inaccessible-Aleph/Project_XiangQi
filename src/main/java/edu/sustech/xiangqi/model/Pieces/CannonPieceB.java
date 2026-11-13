package edu.sustech.xiangqi.model.Pieces;

//黑炮

import edu.sustech.xiangqi.model.Board;
import edu.sustech.xiangqi.model.Piece;

public class CannonPieceB extends Piece {
    public CannonPieceB() {
        super("炮", false);
    }

    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow != toRow && fromCol != toCol) return false;

        // 中间棋子数量
        int count = 0;
        if (fromRow == toRow) {
            int min = Math.min(fromCol, toCol), max = Math.max(fromCol, toCol);
            for (int c = min + 1; c < max; c++) {
                if (board.getPieceAt(fromRow, c) != null) count++;
            }
        } else {
            int min = Math.min(fromRow, toRow), max = Math.max(fromRow, toRow);
            for (int r = min + 1; r < max; r++) {
                if (board.getPieceAt(r, fromCol) != null) count++;
            }
        }

        // 根据终点是否有棋子，判断炮架数量
        if (board.getPieceAt(toRow, toCol) == null) {
            return count == 0; // 移动，不能有炮架
        } else {
            return count == 1; // 吃子，必须有且仅有一个炮架
        }
    }
}
