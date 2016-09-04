package chapter_1_StackAndQueue;

import java.util.Stack;

/**
 * 用两个栈来实现队列基本操作（add，peek，poll）
 */
public class Problem02_TwoStackImplementQueue {
	public static class TwoStacksQueue {
		// 成员变量
		private Stack<Integer> pushStack;
		private Stack<Integer> popStack;

		// 构造方法
		public TwoStacksQueue() {
			this.pushStack = new Stack<>();
			this.popStack = new Stack<>();
		}

		// 队列的加入add操作
		private void add(Integer newData) {
			pushStack.push(newData);
		}

		// 队列的查看最顶层元素操作
		private int peek() {
			if (pushStack.empty() && popStack.empty()) {
				throw new RuntimeException("two Stack are empty!");
			} else if (popStack.empty()) { // popStack为空时才能将压入的元素一次性压入popStack
				while (!pushStack.empty()) {// 一次性压入
					popStack.push(pushStack.pop());
				}
			}
			return popStack.peek();
		}

		// 队列的弹出元素操作
		private int poll() {
			if (pushStack.empty() && popStack.empty()) {
				throw new RuntimeException("two Stack are empty!");
			} else if (popStack.empty()) { // popStack为空时才能将压入的元素一次性压入popStack
				while (!pushStack.empty()) {// 一次性压入
					popStack.push(pushStack.pop());
				}
			}
			return popStack.pop();
		}
	}

	public static void main(String[] args) {
		TwoStacksQueue test = new TwoStacksQueue();
		test.add(1);
		test.add(2);
		test.add(3);
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
	}

}
