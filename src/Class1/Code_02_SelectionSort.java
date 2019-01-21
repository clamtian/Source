package Class1;

import java.util.Arrays;

public class Code_02_SelectionSort {
	/**
	 * 选择排序的实现
	 * @param arr
	 */
	public static void selectionSort(int[] arr){
		if(arr == null||arr.length < 2){
			return;
		}
		for(int i = 0;i < arr.length - 1;i++){
			int minIndex = i;
			for(int j = i + 1;j < arr.length;j++){
				if(arr[j] < arr[minIndex]){
					minIndex = j;
				}
			}
			swap(arr,i,minIndex);
		}
	}
	public static void swap(int[] arr,int a,int b){
		int index = arr[a];
		arr[a] = arr[b];
		arr[b] = index;
	}
	/*
	 * 测试用
	 */
	public static void comparator(int[] arr){
		Arrays.sort(arr);
	}
	public static int[] generateRandomArray(int maxSize,int maxValue){
		int[] arr = new int[(int) (Math.random() * (maxSize +1))];
		for(int i = 0;i < arr.length;i++){
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}
	public static int[] copyArr(int[] arr){
		if(arr == null){
			return null;
		}
		int[] res = new int[arr.length];
		for(int i = 0;i < arr.length;i++){
			res[i] = arr[i];
		}
		return res;
	}
	public static boolean isEqual(int[] arr1,int[] arr2){
		if(arr1 == null&&arr2 == null){
			return true;
		}
		if(arr1 == null||arr2 == null){
			return false;
		}
		if(arr1.length != arr2.length){
			return false;
		}
		for(int i = 0;i < arr1.length;i++){
//			System.out.println(arr1[i] + "..." + arr2[i]);
			if(arr1[i] != arr2[i]){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args){
		int testTime = 500000;
		int maxValue = 10;
		int maxSize = 10;
		boolean isSuccess = true;
		for(int i = 0;i < testTime;i++){
			System.out.println("The " + i + " is in the below");
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArr(arr1);
			selectionSort(arr1);
			comparator(arr2);
			isSuccess = isEqual(arr1, arr2);
			if(!isSuccess){
				System.out.println("MyGod!!");
				break;
			}
		}
	}
}
