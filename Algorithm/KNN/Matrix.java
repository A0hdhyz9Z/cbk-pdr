package KNN;

public class Matrix {
	
	public static Double[][] MatrixMutMatrix(Double a[][], Double b[][]) {
		int arow = a.length;
		int acol=a[0].length;
		int bcol = b[0].length;
		int brow = b.length;
		if(acol!=brow) {
			return null;
		}
		Double[][] c = new Double[arow][bcol];
		for (int i = 0; i < arow; i++) {
			for (int j = 0; j < bcol; j++) {
				Double result = 0.0;
				for (int k = 0; k < brow; k++) {
					result += a[i][k] * b[k][j];
				}
				c[i][j] = result;
			}
		}
		return c;
	}
	
	// 矩阵相减->计算误差
	public static Double[][] MatrixSubMatrix(Double[][] A, Double[][] B) {
		int ARow = A.length;
		int ACol = A[0].length;
		int BRow = B.length;
		int BCol = B[0].length;
		if(ARow!=BRow|| ACol!=BCol) {
			return null;
		}
		Double[][] C = new Double[ARow][ACol];
		
		for (int i = 0; i < ARow; i++) {
			for (int j = 0; j < ACol; j++) {
				C[i][j] = A[i][j] - B[i][j];
			}
	
		}
		return C;
	}
	

	
	// 矩阵相加
	public static Double[][] MatrixAddMatrix(Double[][] A, Double[][] B) {
		int ARow = A.length;
		int ACol = A[0].length;
		int BRow = B.length;
		int BCol = B[0].length;
		if(ARow!=BRow|| ACol!=BCol) {
			return null;
		}
		Double[][] C = new Double[ARow][ACol];
		for (int i = 0; i < ARow; i++) {
			for (int j = 0; j < ACol; j++) {
				C[i][j] = A[i][j] + B[i][j];
			}
		}
		return C;
	}
	
	
	// 将矩阵转置
	public static Double[][] revMatrix(Double temp[][]) {
		int row = temp.length;
		int col = temp[0].length;
		Double[][] result = new Double[col][row];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				result[i][j] = temp[j][i];	
			}
		}
		return result;
	}
	
	// 将矩阵乘以一个数
	public static Double[][] NumMutMatrix(Double temp[][], Double v) {
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = temp[i][j] * v;
			}
		}
		return temp;
	}
	
	// 将矩阵加一个数
	public static Double[][] NumAddMatrix(Double temp[][], Double v) {
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = temp[i][j] + v;
			}
		}
		return temp;
	}
}
