package aght.math;

import java.util.Arrays;

/**
 * A class to represent a matrix of any size. The matrices are represented using
 * a 2D array of type double, and can manipulated with the methods in this
 * class.
 *
 * @author Andy Tang
 * @version 1.0
 */
public class Matrix {

    /**
     * 2D array representing a matrix.
     */
    private double[][] matrix;

    /**
     * Number of rows in matrix.
     */
    private int rows;

    /**
     * Number of columns in matrix.
     */
    private int cols;

    /**
     * Constructs an object of type Matrix using a max number of rows and
     * columns. Every value in the matrix will be set 0 by default, to change
     * the values, use the setValue(double val, int row, int col) method.
     * 
     * @param rows
     *            number of rows.
     * @param cols
     *            number of columns.
     */
    public Matrix(int rows, int cols) {
        matrix = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Constructs an object of type Matrix using a 2D array of type double. The
     * constructor will automatically set the values of the matrix according to
     * the values of the array, the size of the matrix will also be determined
     * automatically;however, the array MUST be in valid matrix form, else a
     * error will be thrown.
     * <p>
     * Examples of a valid array:
     * 
     * <pre>
     * <code>
     * double[][] matrix1 = {{1, 2},        // Valid
     *                       {3, 4}};
     * 
     * Matrix m1 = new Matrix(matrix1);     // No error
     * 
     * double[][] matrix2 = {{1, 2, 3},     // Invalid, third row only has 2
     *                       {4, 5, 7},     // columns, while the first two 
     *                       {7, 8}};       // rows have 3 columns
     * 
     * Matrix m2 = new Matrix(matrix2);     // Error
     * </code>
     * </pre>
     * 
     * @param m
     *            the 2D array to convert to a matrix
     */
    public Matrix(double[][] m) {
        int nCols = m[0].length;
        for (int i = 0; i < m.length; i++) {
            if (m[i].length != nCols) {
                throw new IllegalArgumentException(
                        "Every row of the matrix must have the same number of "
                                + "columns.");
            }
        }

        this.matrix = m;
        this.rows = m.length;
        this.cols = m[0].length;
    }

    /**
     * Constructs an object of type Matrix from a set of values provided by a
     * single dimensional array. The number of rows and columns must be provided
     * by the user and any cell not filled with an array value is assumed to be
     * 0. The user may also specify whether to fill the matrix row by row or
     * column by column.
     * 
     * @param data
     *            array of values
     * @param nRows
     *            number of rows
     * @param nCols
     *            number of columns
     * @param byRow
     *            if true the matrix is filled by rows, otherwise matrix is
     *            filled by columns
     */
    public Matrix(double[] data, int nRows, int nCols, boolean byRow) {
        if ((nRows * nCols) < data.length) {
            throw new IllegalArgumentException(
                    "The specified size of matrix is unable to hold all the "
                            + "array values.");
        }

        int index = 0;
        double[][] tempMatrix = new double[nRows][nCols];

        if (byRow) {
            for (int i = 0; i < nRows; i++) {
                for (int j = 0; j < nCols; j++) {
                    if (data.length > index) {
                        tempMatrix[i][j] = data[index];
                    } else {
                        tempMatrix[i][j] = 0;
                    }
                    index++;
                }
            }
        } else {
            for (int i = 0; i < nCols; i++) {
                for (int j = 0; j < nRows; j++) {
                    if (data.length > index) {
                        tempMatrix[j][i] = data[index];
                    } else {
                        tempMatrix[j][i] = 0;
                    }
                    index++;
                }
            }
        }

        this.matrix = tempMatrix;
        this.rows = nRows;
        this.cols = nCols;
    }

    /**
     * Constructs an object of type Matrix from a set of values provided by a
     * single dimensional array. The number of rows and columns must be provided
     * by the user and any cell not filled with an array value is assumed to be
     * 0. The matrix is filled row by row.
     * 
     * @param m
     *            array of values
     * @param nRow
     *            number of rows
     * @param nCol
     *            number of columns
     */
    public Matrix(double[] m, int nRow, int nCol) {
        this(m, nRow, nCol, true);
    }

    /**
     * Adds this matrix to another matrix.
     * 
     * @param m
     *            is a Matrix object.
     * @return this matrix containing the sum.
     */
    public Matrix add(Matrix m) {
        if (!(m.rows() == this.rows && m.cols() == this.cols)) {
            throw new IllegalArgumentException(
                    "The matrices must have the same dimensions.");
        }

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = this.matrix[i][j] + m.get(i, j);
            }
        }

        return this;
    }

    /**
     * Adds two matrices.
     * 
     * @param m1
     *            is matrix 1
     * @param m2
     *            is matrix 2
     * @return a new matrix containing the sum of the two matrices.
     */
    public static Matrix add(Matrix m1, Matrix m2) {
        if (!(m1.rows() == m2.rows() && m1.cols() == m2.cols())) {
            throw new IllegalArgumentException(
                    "The matrices must have the same dimensions.");
        }

        Matrix sum = new Matrix(m1.rows(), m1.cols());

        for (int i = 0; i < m1.rows; i++) {
            for (int j = 0; j < m1.cols; j++) {
                sum.set(m1.get(i, j) + m2.get(i, j), i, j);
            }
        }

        return sum;
    }

    /**
     * Subtracts this matrix from another matrix.
     * 
     * @param m
     *            is a Matrix object
     * @return this matrix containing the difference
     */
    public Matrix sub(Matrix m) {
        if (!(m.rows() == this.rows && m.cols() == this.cols)) {
            throw new IllegalArgumentException(
                    "The matrices must have the same dimensions");
        }

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = this.matrix[i][j] - m.get(i, j);
            }
        }

        return this;
    }

    /**
     * Subtracts two matrices.
     * 
     * @param m1
     *            is matrix 1
     * @param m2
     *            is matrix 2
     * @return a new matrix containing the difference of the two matrices.
     */
    public static Matrix sub(Matrix m1, Matrix m2) {
        if (!(m1.rows() == m2.rows() && m1.cols() == m2.cols())) {
            throw new IllegalArgumentException(
                    "The matrices must have the same dimensions.");
        }

        Matrix difference = new Matrix(m1.rows(), m1.cols());

        for (int i = 0; i < m1.rows; i++) {
            for (int j = 0; j < m1.cols; j++) {
                difference.set(m1.get(i, j) - m2.get(i, j), i, j);
            }
        }

        return difference;
    }

    /**
     * Multiplies this matrix to scalar value.
     * 
     * @param scl
     *            is a scalar value
     * @return this matrix multiplied by the scalar value
     */
    public Matrix mult(double scl) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] *= scl;
            }
        }

        return this;
    }

    /**
     * This is a static method that multiplies a matrix by a scalar value.
     * 
     * @param m
     *            the matrix to multiply by a scalar value
     * @param scl
     *            the scale value
     * @return a new Matrix object containing the product
     */
    public static Matrix mult(Matrix m, double scl) {
        Matrix mult = Matrix.clone(m);

        for (int i = 0; i < mult.rows(); i++) {
            for (int j = 0; j < mult.cols(); j++) {
                mult.set(mult.get(i, j) * scl, i, j);
            }
        }

        return mult;
    }

    /**
     * Multiplies this matrix by another matrix.
     * 
     * @param m
     *            is a Matrix object
     * @return this matrix containing the product of this matrix and the passed
     *         matrix
     */
    public Matrix mult(Matrix m) {
        if (!(this.cols == m.rows())) {
            throw new IllegalArgumentException(
                    "The number of columns in the first matrix must match the "
                            + "number of rows in the second matrix");
        }

        double[][] matrixArray = new double[this.rows][m.cols()];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < m.cols(); j++) {
                for (int n = 0; n < this.cols; n++) {
                    matrixArray[i][j] += this.get(i, n) * m.get(n, j);
                }
            }
        }

        this.matrix = matrixArray;
        this.cols = m.cols();

        return this;
    }

    /**
     * Multiplies two matrices.
     * 
     * @param m1
     *            is matrix 1
     * @param m2
     *            is matrix 2
     * @return a new matrix containing the product of the two matrices
     */
    public static Matrix mult(Matrix m1, Matrix m2) {
        if (!(m1.cols() == m2.rows())) {
            throw new IllegalArgumentException(
                    "The number of columns in the first matrix must match the "
                            + "number of rows in the second matrix");
        }

        double[][] matrixArray = new double[m1.rows()][m2.cols()];

        for (int i = 0; i < m1.rows(); i++) {
            for (int j = 0; j < m2.cols(); j++) {
                for (int n = 0; n < m1.cols(); n++) {
                    matrixArray[i][j] += m1.get(i, n) * m2.get(n, j);
                }
            }
        }

        return new Matrix(matrixArray);
    }

    /**
     * Converts all the values in a matrix to a certain value.
     * 
     * @param val
     *            the value to convert to
     * @return this Matrix object
     */
    public Matrix fill(double val) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = val;
            }
        }

        return this;
    }

    /**
     * Converts all the values in a matrix to a certain value.
     * 
     * @param m
     *            the matrix to convert
     * @param val
     *            the value to convert to
     * @return new Matrix object
     */
    public static Matrix fill(Matrix m, double val) {
        Matrix mat = Matrix.clone(m);
        for (int i = 0; i < mat.rows(); i++) {
            for (int j = 0; j < mat.cols(); j++) {
                mat.set(val, i, j);
            }
        }
        return mat;
    }

    /**
     * Copies the contents and size of a matrix to this matrix.
     * 
     * @param m
     *            the Matrix object to copy from
     * @return this Matrix object
     */
    public Matrix copy(Matrix m) {
        this.matrix = Matrix.toArray(m);
        this.rows = m.rows();
        this.cols = m.cols();

        return this;
    }

    /**
     * Clones the contents and size of a matrix and returns a new matrix
     * containing the original's data.
     * 
     * @param m
     *            the Matrix object to clone
     * @return new Matrix object containing the original's data
     */
    public static Matrix clone(Matrix m) {
        return new Matrix(Matrix.toArray(m));
    }

    /**
     * Converts a Matrix object to 2D array representation. The returned array
     * will contain the values of the argument Matrix. The size of the matrix
     * can also be derived from this array.
     * <p>
     * To get the size of the array the following method calls can be made:
     * 
     * <pre>
     * <code>
     * double[][] matrix = {{2, 2}, 
     *                      {2, 2}};
     * Matrix n = new Matrix(matrix);            
     * double[][] arrayRep = Matrix.toArray(n);  // {{2, 2}, {2, 2}}
     * 
     * int rows = arrayRep.length;               // 2
     * int cols = arrayRep[0].length;            // 2
     * </code>
     * </pre>
     * 
     * 
     * @param m
     *            the Matrix object to convert
     * @return 2D array containing the arugument's values
     */
    public static double[][] toArray(Matrix m) {
        double[][] arrCopy = new double[m.rows()][m.cols()];

        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                arrCopy[i][j] = m.get(i, j);
            }
        }

        return arrCopy;
    }

    /**
     * Gets number of rows in matrix.
     * 
     * @return number of rows in matrix.
     */
    public int rows() {
        return this.rows;
    }

    /**
     * Gets number of columns in matrix.
     * 
     * @return number of columns in matrix.
     */
    public int cols() {
        return this.cols;
    }

    /**
     * Gets value at the specified index in the matrix.
     * 
     * @param rowIndex
     *            is the row number.
     * @param colIndex
     *            is the column number.
     * @return the value stored at the row and column index specified.
     */
    public double get(int rowIndex, int colIndex) {
        return this.matrix[rowIndex][colIndex];
    }

    /**
     * Sets the value at the specified index in the matrix.
     * 
     * @param newValue
     *            is the new value
     * @param rowIndex
     *            is the row number.
     * @param colIndex
     *            is the column number.
     */
    public void set(double newValue, int rowIndex, int colIndex) {
        this.matrix[rowIndex][colIndex] = newValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cols;
        result = prime * result + Arrays.deepHashCode(matrix);
        result = prime * result + rows;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Matrix m = (Matrix) obj;
        if (this.cols == m.cols() && this.rows == m.rows()
                && Arrays.deepEquals(this.matrix, m.matrix)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        final int formatDecimalPlaces = 2;

        // Must be GREATER or EQUAL to 2 to account for negative signs
        final int spaces = 2;

        double longestVal = 0;
        int rowNumAfterSpaces = String.valueOf(this.rows).length();
        StringBuilder stringMatrix = new StringBuilder();

        // Find the number with the greatest number of characters by taking the
        // absolute value of every number
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (Math.abs(this.matrix[i][j]) > longestVal) {
                    longestVal = Math.abs(this.matrix[i][j]);
                }
            }
        }

        // Add initial offset spaces to column numbers
        for (int i = 0; i < rowNumAfterSpaces + spaces - 1 + 2; i++) {
            stringMatrix.append(" ");
        }

        // Create formatting string
        StringBuilder format = new StringBuilder();
        for (int f = 0; f < this.cols; f++) {
            format.append("%" + String
                    .format("%." + (formatDecimalPlaces + 1) + "f", longestVal)
                    .length() + "s");
            for (int i = 0; i < spaces - 1; i++) {
                if (f == this.cols - 1) {
                    break;
                }
                format.append(" ");
            }
        }

        // Create column numbers
        String[] colNums = new String[this.cols];
        for (int n = 0; n < this.cols; n++) {
            colNums[n] = "[" + n + "]";
        }

        // Append column numbers
        stringMatrix
                .append(String.format(format.toString(), (Object[]) colNums));
        stringMatrix.append("\n");

        // Create matrix values
        for (int i = 0; i < this.rows; i++) {
            String[] values = new String[this.cols + this.rows];

            for (int j = 0; j < this.cols; j++) {
                values[j] = String.format("%." + formatDecimalPlaces + "f",
                        this.matrix[i][j]);
            }

            // Create and append row numbers and add spaces to make matrix
            // values appear on the same column
            stringMatrix.append("[" + i + "]");
            for (int k = 0; k < rowNumAfterSpaces - String.valueOf(i).length()
                    + spaces - 1; k++) {
                stringMatrix.append(" ");
            }

            // Append matrix values to the end of the row numbers
            stringMatrix.append(
                    String.format(format.toString(), (Object[]) values));
            stringMatrix.append("\n");
        }

        return stringMatrix.toString();
    }
}
