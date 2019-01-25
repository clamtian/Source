package StackAndQueue;
/**
 * 用数组实现固定大小的栈和队列
 *@author clam
 *
 */
public class Code_01_Array_To_Stack_Queue {
	/**
	 * 用数组实现固定大小的栈
	 *@author clam
	 *
	 */
	public static class ArrayStack{
		private Integer[] arr;
		private Integer size;
		
		public ArrayStack(int initSize){
			if(initSize < 0){
				throw new IllegalArgumentException("The init size is less than 0");
			}
			arr = new Integer[initSize];
			size = 0;
		}
		/**
		 * 获取栈顶元素
		 * @return
		 */
		public Integer peek(){
			if(size == 0){
				return null;
			}
			return arr[size - 1];
		}
		/**
		 * 压栈操作
		 * @param obj
		 */
		public void push(int obj){
			if(size == arr.length){
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			arr[size++] = obj;
		}
		/**
		 * 弹栈操作
		 * @return
		 */
		public Integer pop(){
			if (size == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			return arr[--size];
		}
	}
	/**
	 * 用数组实现固定大小的队列
	 *@author clam
	 *
	 */
	public static class ArrayQueue {
		private Integer[] arr;
		private Integer size;
		private Integer first;
		private Integer last;

		public ArrayQueue(int initSize) {
			if (initSize < 0) {
				throw new IllegalArgumentException("The init size is less than 0");
			}
			arr = new Integer[initSize];
			size = 0;
			first = 0;
			last = 0;
		}
		/**
		 * 获得队列第一个元素
		 * @return
		 */
		public Integer peek() {
			if (size == 0) {
				return null;
			}
			return arr[first];
		}
		/**
		 * 向队列添加元素
		 * @param obj
		 */
		public void push(int obj) {
			if (size == arr.length) {
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			size++;
			arr[last] = obj;
			last = last == arr.length - 1 ? 0 : last + 1;
		}
		/**
		 * 取出队列第一个元素
		 * @return
		 */
		public Integer poll() {
			if (size == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			size--;
			int tmp = first;
			first = first == arr.length - 1 ? 0 : first + 1;
			return arr[tmp];
		}
	}
}
