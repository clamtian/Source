package StackAndQueue;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作
 *@author clam
 *
 */
public class Code_02_GetMinStack {
	public static class GetMinStack{
		private Stack<Integer> dataStack;
		private Stack<Integer> minStack;
		
		public GetMinStack(){
			dataStack = new Stack<Integer>();
			minStack = new Stack<Integer>();
		}
		
		public void push(int newNum){
			if(minStack.isEmpty()){
				minStack.push(newNum);
			}else if(newNum <= this.getMin()){
				minStack.push(newNum);
			}
			dataStack.push(newNum);
		}
		public int pop(){
			if(dataStack.isEmpty()){
				throw new RuntimeException("Your stack is empty.");
			}
			int value = dataStack.pop();
			if(value == this.getMin()){
				minStack.pop();
			}
			return value;
		}
		public int peek(){
			if (this.dataStack.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return dataStack.peek();
		}
		public int getMin(){
			if (this.minStack.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return minStack.peek();
		}
	}
	public static void main(String[] args) {
		GetMinStack stack1 = new GetMinStack();
		stack1.push(3);
		System.out.println(stack1.getMin());
		stack1.push(4);
		System.out.println(stack1.getMin());
		stack1.push(1);
		System.out.println(stack1.getMin());
		System.out.println(stack1.pop());
		System.out.println(stack1.getMin());
	}
}
