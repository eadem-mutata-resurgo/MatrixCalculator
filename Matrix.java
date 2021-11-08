import java.util.Arrays;

public class Matrix {

    private int numRows;
    private int numColumns;
    private double[][] matrix;

    public int getNumRows() { return numRows; }
    public int getNumColumns() { return numColumns; }

    public void setElement(double val, int row, int column) { matrix[row][column] = val; }
    public double getElement(int row, int column) { return matrix[row][column]; }

    public void setRow(double[] row, int rowIndex) { matrix[rowIndex] = row; }
    public double[] getRow(int row) { return matrix[row]; }

    public void setColumn(double[] col, int colIndex) { for (int i = 0; i < numRows; i++) { matrix[i][colIndex] = col[i]; } }
    public double[] getColumn(int col) {
        double[] arr = new double[numRows];
        for (int i = 0; i < numRows; i++) { arr[i] = matrix[i][col]; }
        return arr;
    }

    public boolean setMatrix(double[] e) {
        if (e.length == numRows * numColumns) {
            for (int i = 0; i < numRows; i++) {
                double[] tempRow = new double[numColumns];
                for (int j = i*numColumns; j < (i+1)*numColumns; j++) {
                    tempRow[j-(i*numColumns)] = e[j];
                }
                matrix[i] = tempRow;
            }
            return true;
        }
        return false;
    }
    public double[][] getMatrix() { return matrix; }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                str.append("[").append(matrix[i][j]).append("] ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    public void fill(double val) {
        double[] arr;
        for ( int i = 0; i < numRows; i++ ) {
            arr = new double[numColumns];
            Arrays.fill(arr, val);
            matrix[i] = arr;
        }
    }

    public Matrix(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        matrix = new double[numRows][numColumns];
        this.fill(0);
    }
}
