import java.util.ArrayList;

public class Matrix<T extends Number> {
	private T[][] matrix;
	
	public enum Attribute {
		RANDOM,
		NEGATIVE,
		POSITIVE
	}
	
	public Matrix(T[][] matrix) throws Exception {
		this.matrix = matrix;
	}
	
	@SuppressWarnings("unchecked")
	public Matrix(int row, int col) throws Exception {
		this.matrix = (T[][]) new Number[row][col];
	}
	
	public Matrix(int row, int col, T val) throws Exception {
		this(row, col);
		this.setAll(val);
	}
	
	public Matrix(int row, int col, Matrix.Attribute attribute) throws Exception {
		this(row, col);
		this.setAllParam(attribute);
	}
	
	public T[][] returnMatrix() {return matrix;}
	
	@SuppressWarnings("unchecked")
	public void setAllParam(Matrix.Attribute attribute) {
		double min = 0;
		double max = 0;
		switch (attribute) {
			case NEGATIVE:
				min = Double.MIN_VALUE;
				max = -1;
				break;
			case POSITIVE:
				min = 0;
				max = Double.MAX_VALUE;
				break;
			case RANDOM: default:
				min = Double.MIN_VALUE;
				max = Double.MAX_VALUE;
				break;
		
		}
		
		for (int row = 0; row < this.matrix.length; row++) {
			for (int col = 0; col < this.matrix[row].length; col++) {
				this.matrix[row][col] = (T) (Number) (int) ((Math.random() * (max - min)) + min);
			}
		}
	}
	
	public void setAllRandom() { this.setAllParam(Matrix.Attribute.RANDOM); }
	
	public void setAll(T t) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				matrix[row][col] = t;
			}
		}
	}
	
	@Override 
	public String toString() {
		String s = "";
		for (T[] rows : matrix) {
			s += "[";
			for (T cols : rows) {
				s += cols + ", ";
			}
			s += "]\n";
		}
		return s;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public T setValue(int row, int col, T val) throws Exception {
		T t = matrix[row][col];
		matrix[row][col] = val;
		return t;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Matrix<T> multiply(Matrix<T> mat2) throws Exception {
		if (this.matrix[0].length != mat2.returnMatrix().length)
			throw new Exception("Dimension Mismatch");
		
		T[][] multipliedMatrix = (T[][]) new Number[this.matrix.length][mat2.returnMatrix()[0].length];
		
		int index = 0;
		for (int rowsOfFirst = 0; rowsOfFirst < this.matrix.length; rowsOfFirst++) {
			for (int secCol = 0; secCol < mat2.returnMatrix()[0].length; secCol++) {
				Double total = 0.0;
				for (int secRow = 0; secRow < mat2.returnMatrix().length; secRow++) {
					total += (this.matrix[rowsOfFirst][index].doubleValue() * mat2.returnMatrix()[secRow][secCol].doubleValue());
					index++;
				}	
				index = 0;
				setNextNull(multipliedMatrix, total);
			}
		}
		
		
		Matrix<T> toRet = new Matrix<T>(multipliedMatrix);
		return toRet;
		
	}
	
	@SuppressWarnings("unchecked")
	private boolean setNextNull(T[][] arr, Number num) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {	
				if (arr[i][j] == null) {
					arr[i][j] = (T) num;
					return true;
				}
			}
		}
		return false;
	}
}
