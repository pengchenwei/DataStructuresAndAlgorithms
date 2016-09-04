package chapter_1_StackAndQueue;

import java.util.Stack;

public class Problem03_ReverseStackUsingRecursive {
	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

	/**
	 * 返回并移除栈底元素
	 * 
	 * @param s
	 * @return 栈底元素
	 */
	private static Integer getAndRemoveLast(Stack<Integer> s) {
		Integer result = s.pop();
		if (s.empty()) { // pop操作后变为空就说明本身就是最后一个元素，直接返回
			return result;
		} else {
			int last = getAndRemoveLast(s); // 递归调用，搜寻至最底层元素
			s.push(result);
			return last;
		}
	}

	/**
	 * 逆序一个栈
	 * 
	 * @param stack
	 * @return
	 */
	private static Stack<Integer> reverse(Stack<Integer> stack) {
		if (stack.empty()) {
			return stack;
		}
		Integer pop = getAndRemoveLast(stack);
		reverse(stack);
		stack.push(pop);
		return stack;
	}
}
