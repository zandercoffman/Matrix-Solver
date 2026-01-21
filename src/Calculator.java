
public class Calculator {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Double[][] arr1 = {
			    {2.0, 3.0},
			    {5.0, 6.0}
		};
		
		Double[][] arr2 = {
			    {3.0, 3.0},
			    {5.0, 4.0}
		};
		
		Matrix<Double> mat = new Matrix<Double>(arr1);
		Matrix<Double> mat2 = new Matrix<Double>(arr2);
		System.out.println(mat);
		System.out.println(mat2);
		
		Matrix<Double> multiplied = mat.multiply(mat2);
		System.out.println(multiplied);
	}

}
