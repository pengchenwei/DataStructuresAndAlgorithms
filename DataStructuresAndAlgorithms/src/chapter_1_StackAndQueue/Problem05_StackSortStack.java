package chapter_1_StackAndQueue;

import java.util.Stack;

public class Problem05_StackSortStack {
	/*
	 * 借助另一个栈来将一个栈中的元素按从大到小的顺序排列
	 */
	public static void sortStackByStack(Stack<Integer> stack) {
		Stack<Integer> help = new Stack<>();// 借助栈
		while (!stack.empty()) {	//取出栈中元素
			int cur = stack.pop();
			while (!help.isEmpty() && cur > help.peek()) {	//取栈中元素要确定栈非空
				stack.push(help.pop());	//把help中的元素按顺序暂放在stack中
			}
			//help栈中按从上往下依次变大排列元素
			help.push(cur);	//如果stack弹出的元素比help栈顶元素小（等于）则压入help
		}
		//把help栈中的元素放回stack栈中
		while (!help.empty())
			stack.push(help.pop());
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(3);
		stack.push(1);
		stack.push(6);
		stack.push(2);
		stack.push(5);
		stack.push(4);
		sortStackByStack(stack);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

}
