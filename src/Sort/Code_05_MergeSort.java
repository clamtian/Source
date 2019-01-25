package Sort;

import java.util.Arrays;

public class Code_05_MergeSort {
	/**
	 * 归并排序实现
	 * @param arr
	 */
	public static void mergeSort(int[] arr){
		if(arr == null||arr.length < 2){
			return;
		}
		mergeSort(arr,0,arr.length - 1);
	}
	public static void mergeSort(int[] arr,int l,int r){
		if(l == r){
			return;
		}
		int mid = l + ((r - l) >> 1);
		mergeSort(arr,l,mid);
		mergeSort(arr,mid + 1,r);
		merge(arr,l,mid,r);
	}
	public static void merge(int[] arr,int l,int mid,int r){
		int[] help = new int[r - l + 1];
		int i = l;
		int j = mid + 1;
		int k = 0;
		while(i < mid + 1&&j < r + 1){
			help[k++] = arr[i] > arr[j]?arr[j++]:arr[i++];
		}
		while(i < mid + 1){
			help[k++] = arr[i++];
		}
		while(j < r + 1){
			help[k++] = arr[j++];
		}
		for(i = 0;i < help.length;i++){
			arr[l + i] = help[i];
		}
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
			mergeSort(arr1);
			comparator(arr2);
			isSuccess = isEqual(arr1, arr2);
			if(!isSuccess){
				System.out.println("MyGod!!");
				break;
			}
		}
	}
	
}
