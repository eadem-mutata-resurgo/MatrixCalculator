public class MatrixCalculator {

    //returns the determinant of inputted matrix
    public static Double determinant(Matrix mat) {
        int size = mat.getNumRows();
        if (size != mat.getNumColumns()) { return null; }
        else if (size == 2) {
            return ((mat.getElement(0,0) * mat.getElement(1,1)) - (mat.getElement(0, 1) * mat.getElement(1, 0)));
        }
        double det = 0.0;
        double[] firstRow = mat.getRow(0);
        for (int i = 0; i < mat.getNumColumns(); i++) {
            det += (Math.pow(-1, i) * firstRow[i] * determinant(subMatrix(mat,0, i)));
        }
        return det;
    }

    //returns the sub-matrix of inputted matrix missing the row and column corresponding to the inputted indexes
    public static Matrix subMatrix(Matrix mar, int rowIndex, int colIndex) {
        int cols = mar.getNumColumns();
        int rows = mar.getNumRows();
        Matrix outMar = new Matrix(rows - 1, cols - 1);
        double[] tempRow;
        for (int i = 0; i < rows; i++) {
            if (i != rowIndex) {
                tempRow = mar.getRow(i);
                double[] addRow = new double[cols - 1];
                for (int j = 0; j < cols; j++) {
                    if (j < colIndex) { addRow[j] = tempRow[j]; }
                    else if (j > colIndex) {addRow[j-1] = tempRow[j]; }
                }
                if (i < rowIndex) { outMar.setRow(addRow, i); }
                else { outMar.setRow(addRow, i-1); }
            }
        }
        return outMar;
    }

    //returns the augmented matrix (a|b)
    public static Matrix augment(Matrix a, Matrix b) {
        int numRows = a.getNumRows();
        if (numRows != b.getNumRows()) { return null; }
        int numColumns = a.getNumColumns() + b.getNumColumns();
        Matrix mat = new Matrix(numRows, numColumns);
        for (int i = 0; i < numRows; i++) {
            int num = a.getNumColumns();
            double[] temp = new double[numColumns];
            for (int j = 0; j < numColumns; j++) {
                if (j < num) { temp[j] = a.getElement(i, j); }
                else { temp[j] = a.getElement(i, j-num); }
            }
            mat.setRow(temp, i);
        }
        return mat;
    }

    //returns the transpose of the given matrix
    public static Matrix transpose(Matrix mar) {
        int rows = mar.getNumRows();
        int cols = mar.getNumColumns();
        Matrix outMar = new Matrix(cols, rows);
        for (int i = 0; i < cols; i++) {
            outMar.setRow(mar.getColumn(i), i);
        }
        return outMar;
    }
    //sets the given matrix, if square, to the identity matrix
    public static Boolean setID(Matrix sqrMat) {
        int size = sqrMat.getNumRows();
        if (size != sqrMat.getNumColumns()) { return false; }
        sqrMat.fill(0.0);
        for (int i = 0; i < size; i++) { sqrMat.setElement(1, i, i); }
        return true;
    }

    //elementary row operations
    public static void swapRows(Matrix mat, int rowIndexOne, int rowIndexTwo) {
        double[] temp = mat.getRow(rowIndexOne);
        mat.setRow(mat.getRow(rowIndexTwo), rowIndexOne);
        mat.setRow(temp, rowIndexTwo);
    }
    public static void scaleRow(Matrix mat, double scalar, int rowIndex) {
        double[] row = mat.getRow(rowIndex);
        for (int i = 0; i < row.length; i++) {
            row[i] *= scalar;
        }
        mat.setRow(row, rowIndex);
    }
    public static void sumRows(Matrix mat, double scalar, int targetRowIndex, int sourceRowIndex) {
        for (int i = 0; i < mat.getNumColumns(); i++) {
            mat.setElement(mat.getElement(targetRowIndex, i) + (mat.getElement(sourceRowIndex, i) * scalar), targetRowIndex, i);
        }
    }

    //returns the product of two matrices
    public static Matrix multiply(Matrix leftMat, Matrix rightMat) {
        int productSize = leftMat.getNumColumns();
        if (productSize != rightMat.getNumRows()) { return null; }
        int outRows = leftMat.getNumRows();
        int outCols = rightMat.getNumColumns();
        Matrix outMat = new Matrix(outRows, outCols);
        double product;
        for (int i = 0; i < outRows; i++) {
            for (int j = 0; j < outCols; j++) {
                product = 0;
                for (int k = 0; k < productSize; k++) {
                    product += leftMat.getElement(i, k) * rightMat.getElement(k,j);
                }
                outMat.setElement(product, i, j);
            }
        }
        return outMat;
    }
}
