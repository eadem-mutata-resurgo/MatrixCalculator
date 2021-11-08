public class MatrixTester {
    public static void main (String[] args) {
        int rows = 3;
        int cols = 3;
        Matrix mat = new Matrix(rows, cols);
        mat.setMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println(mat + "\n" + MatrixCalculator.determinant(mat));
    }
}
