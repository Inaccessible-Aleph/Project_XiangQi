package edu.sustech.xiangqi.model.Pieces;

//红马

import edu.sustech.xiangqi.model.Board;
import edu.sustech.xiangqi.model.Piece;

public class HorsePiece extends Piece {
    public HorsePiece() {
        super("马", 4);
    }

    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        int row = Math.abs(toRow - fromRow);
        int col = Math.abs(toCol - fromCol);

        // 判断基础形状
        if (!((row == 2 && col == 1) || (row == 1 && col == 2))) {
            return false;
        }

        // 蹩马腿判断
        if (row == 2) { // 竖向跳，马腿在纵向相邻的一格
            int legRow = fromRow + (toRow > fromRow ? 1 : -1);
            if (board.getPieceAt(legRow, fromCol) != null) return false;
        } else { // 横向跳，马腿在横向相邻的一格
            int legCol = fromCol + (toCol > fromCol ? 1 : -1);
            if (board.getPieceAt(fromRow, legCol) != null) return false;
        }
        return true;
    }
}
