package Class1;

import java.util.Arrays;

public class Code_04_QuickSort {
	/**
	 * 快速排序实现
	 * @param arr
	 */
	public static void quickSort(int[] arr){
		if(arr == null||arr.length < 2){
			return;
		}
		quickSort(arr,0,arr.length - 1);
	}
	public static void quickSort(int[] arr,int start,int end){
		if(start < end){
			swap(arr,end,start
					+ (int)Math.random() * (end - start + 1));
			int less = start - 1;
			int more = end;
			for(int i = start;i < more;i++){
				if(arr[i] < arr[end]){
					swap(arr, ++less, i);
				}else if(arr[i] > arr[end]){
					swap(arr,--more,i--);
				}
			}
			swap(arr,end,more++);
			quickSort(arr,start,less);
			quickSort(arr,more,end);
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
//			System.out.println(arr[i]);
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
			quickSort(arr1);
			comparator(arr2);
			isSuccess = isEqual(arr1, arr2);
			if(!isSuccess){
				System.out.println("MyGod!!");
				break;
			}
		}
	}
}
