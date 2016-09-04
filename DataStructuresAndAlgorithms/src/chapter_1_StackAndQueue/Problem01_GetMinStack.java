package chapter_1_StackAndQueue;

import java.util.Stack;

public class Problem01_GetMinStack {
	/**
	 * public:访问权限	 static:加载方式为在加载外部类的时候就加载本类
	 * 
	 * @author pen
	 *
	 */
	public static class MyStack1 {
		// 成员变量
		private Stack<Integer> dataStack;
		private Stack<Integer> minStack;

		// 构造函数
		public MyStack1() {
			dataStack = new Stack<>();
			minStack = new Stack<>();
		}

		// 成员函数
		/**
		 * 压入元素规则
		 * 
		 * @param newData
		 */
		private void push(Integer newData) {
			if (minStack.isEmpty()) { // minStack栈第一个元素必定与dataStack的第一个元素相同
				minStack.push(newData);
			} else if (getMin() >= newData) { // 新元素newData要比minStack栈顶元素要小（或等于）则压入栈内
				minStack.push(newData);
			}
			dataStack.push(newData); // 基准栈压入操作
		}

		/**
		 * 弹出元素规则
		 * 
		 * @return
		 */
		private int pop() {
			if (dataStack.isEmpty()) {
				throw new RuntimeException("dataStack is empty!");
			}
			int value = dataStack.pop(); // 基准栈弹出操作
			if (value == minStack.peek()) { // dataStack栈弹出操作完成后要更新minStack栈的元素
				minStack.pop();
			}
			return value;
		}

		/**
		 * 获得最小元素
		 * 
		 * @return
		 */
		private int getMin() {
			if (minStack.isEmpty()) {
				throw new RuntimeException("minStack is empty!");
			}
			return minStack.peek();
		}
	}

	public static void main(String[] args) {
		MyStack1 myStack1 = new MyStack1();
		myStack1.push(2);
		System.out.println(myStack1.getMin());
		myStack1.pop();
		myStack1.push(2);
		myStack1.push(3);
		System.out.println(myStack1.getMin());
		myStack1.push(1);
		myStack1.push(1);
		System.out.println(myStack1.getMin());
		myStack1.push(5);
		System.out.println(myStack1.getMin());
		myStack1.push(-1);
		System.out.println(myStack1.getMin());

		System.out.println("=============================");

		MyStack2 myStack2 = new MyStack2();
		myStack2.push(2);
		System.out.println(myStack2.getMin());
		myStack2.pop();
		myStack2.push(3);
		myStack2.push(1);
		myStack2.push(-1);
		System.out.println(myStack2.getMin());
		myStack2.push(5);
		System.out.println(myStack2.getMin());
		myStack2.push(1);
		System.out.println(myStack2.getMin());
	}

}

/*
 * 相当于同一个包中的两个类
 */
class MyStack2 {
	// 成员变量
	private Stack<Integer> dataStack;
	private Stack<Integer> minStack;

	// 构造函数
	public MyStack2() {
		dataStack = new Stack<>();
		minStack = new Stack<>();
	}

	// 成员函数
	/**
	 * 压入元素规则
	 * 
	 * @param newData
	 */
	void push(Integer newData) {
		if (minStack.isEmpty()) { // minStack栈第一个元素必定与dataStack的第一个元素相同
			minStack.push(newData);
		} else if (newData < getMin()) { // 新元素newData要比minStack栈顶元素要小（或等于）则压入栈内
			minStack.push(newData);
		} else {
			Integer peek = minStack.peek(); // 重复压入minStack的顶端元素
			minStack.push(peek);
		}
		dataStack.push(newData); // 基准栈压入操作
	}

	/**
	 * 弹出元素规则
	 * 
	 * @return
	 */
	int pop() {
		if (dataStack.isEmpty()) {
			throw new RuntimeException("dataStack is empty!");
		}
		minStack.pop();
		return dataStack.pop(); // 基准栈弹出操作
	}

	/**
	 * 获得最小元素
	 * 
	 * @return
	 */
	int getMin() {
		if (minStack.isEmpty()) {
			throw new RuntimeException("minStack is empty!");
		}
		return minStack.peek();

	}
}
