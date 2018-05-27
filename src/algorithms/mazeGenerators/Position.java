/**
 * this class represent a position
 * format: (row,col)
 */

package algorithms.mazeGenerators;

import java.util.Objects;

public class Position {

    private int row;
    private int col;

    /**
     * constact a positions
     *
     * @param row - number of row
     * @param col - number of column
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * @return the number of the row
     */
    public int getRowIndex() {
        return row;
    }

    /**
     * @return the column of the row
     */
    public int getColumnIndex() {
        return col;
    }

    /**
     * @return string format of the position format: {row,col}
     * @Override
     */

    public String toString() {
        return "{" + row + "," + col + "}";
    }

    /**
     * @param o given object to compare
     * @return true if the given position is own the same values
     * @Override
     */

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position) o;
        return row == position.row &&
                col == position.col;
    }

    @Override
    public int hashCode() {

        int hash = 53;
        hash = ((hash + row) << 5) - (hash + row);
        hash = ((hash + col) << 5) - (hash + col);
        return hash;
    }

}



