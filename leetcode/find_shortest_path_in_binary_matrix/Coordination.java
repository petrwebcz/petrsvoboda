package org.example;

public class Coordination {
    public Coordination(int row, int col, int depth) {
        this.row = row;
        this.col = col;
        this.depth = depth;
    }

    private int row;
    private int col;
    private int depth;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getDepth() {
        return depth;
    }
    
    public Coordination moveDown() {
        return new Coordination(row + 1, col, depth + 1);
    }

    public Coordination moveRight() {
        return new Coordination(row, col + 1, depth + 1);
    }

    public Coordination moveRightAndDown() {
        return new Coordination(row+1, col + 1, depth + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordination other) {
            return other.row == this.row && other.col == this.col;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "Coordination: Row: " + row + ", Col: " + col + ", Depth: " + depth;
    }
}
