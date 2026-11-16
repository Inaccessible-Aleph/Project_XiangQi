package edu.sustech.xiangqi.model;

// 棋子的抽象父类。棋子保存身份信息，所在位置由 Board 的数组下标表示

public abstract class Piece {
    private final String name;
    private final boolean isRed;
    private final int pieceType;

    protected Piece(String name, int pieceType) {
        this.name = name;
        this.isRed = pieceType > 0;
        this.pieceType = pieceType;
    }

    public String getName() {
        return name;
    }

    public boolean isRed() {
        return isRed;
    }

    public int getPieceType() {
        return pieceType;
    }

    /**
     * 判断本棋子的局部走法是否合法
     *
     * @return 是否可以移动
     */
    public abstract boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol);
}
