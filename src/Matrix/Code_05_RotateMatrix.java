package Matrix;
/**
 * 给定一个整型矩阵matrix，请按照转圈的方式打印它。
 * 例如： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 
 * 打印结果为：1，2，3，4，8，12，16，15，14，13，9，5，6，7，11， 10
 * 【要求】 额外空间复杂度为O(1)
 *@author clam
 *
 */
public class Code_05_RotateMatrix {
	public static void main(String[] args) {
		for(int i  =0;i < 100;i++){
			int x = (int)(Math.random() * 2);
			System.out.println(x);
		}
	}
}
