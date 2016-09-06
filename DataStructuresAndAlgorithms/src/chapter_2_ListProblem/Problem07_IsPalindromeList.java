package chapter_2_ListProblem;

import java.util.Stack;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem07_IsPalindromeList.java
 * @Description: TODO
 * @CreatTime: 2016年9月5日 下午5:12:07
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem07_IsPalindromeList {
	public static class Node {
		private Node next;
		private int value;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: isPalindrome1
	 * @Description: 全部压入栈中从头开始与原链表比较
	 * @param head
	 * @return 是否回文
	 * @author pen
	 * @CreatTime: 2016年9月5日 下午5:22:55
	 */
	private static boolean isPalindrome1(Node head) {
		if (head == null || head.next == null)// 至多只有一个节点肯定回文
			return true;
		Stack<Node> stack = new Stack<Node>();
		Node cur = head;// 保存头节点，第二个while循环会用(从头开始比较)
		while (cur != null) {// 从开头开始压入
			stack.push(cur);
			cur = cur.next;
		}
		while (head != null) {// 从头开始比较
			if (stack.pop().value != head.value)
				return false;
			head = head.next;
		}
		return true;

	}

	private static boolean isPalindrome2(Node head) {
		if (head == null || head.next == null)// 至多只有一个节点肯定回文
			return true;
		Node cur = head;
		Node right = head.next;// 避免奇偶出现问题:节点数为偶数的话，right最后将指向一对相同中间节点的后面一个，确保压入栈中的是右半边节点
		while (cur.next != null && cur.next.next != null) {
			right = right.next;
			cur = cur.next.next;// 每次移动的步幅
		}
		Stack<Node> stack = new Stack<Node>();
		while (right != null) {
			stack.push(right);
			right = right.next;// 循环动力
		}
		while (!stack.empty()) {
			if (stack.pop().value != head.value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

	private static boolean isPalindrome3(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		// 找到中间节点，偶数时取前半段的最后一个节点
		Node n1 = head;
		Node n2 = head;
		while (n1.next != null && n1.next.next != null) {
			n2 = n2.next;// 最终将指向所求的中间节点
			n1 = n1.next.next;// 步幅为2；
		}
		// 反转后半段，并将末节点指向上面的n2
		n1 = n2.next;// 保存后半段的头节点，反转循环需要用到
		Node n3 = null;// 辅助节点，反转时会用到
		n2.next = null;// 前半段链表头节点的next指向置为null
		while (n1 != null) {// 一下的“下一个节点”均相对于n1的当前值
			// 1取出下一个节点保存
			n3 = n1.next;
			// 2设置下一个节点的next指向
			n1.next = n2;// 第一次循环将后半段的头节点的next指向前半段的末节点
			// 3保存上一次的n1取值
			n2 = n1;
			// 4设置移动步幅,第1步的作用
			n1 = n3;
		}
		// 找到前、后半段链表的头节点，开始循环判断是否相等
		n3 = n2;// 保存后半段末节点(反转后的头节点)n2
		n1 = head;// 前半段头节点
		boolean res = true;// 就算不是回文，还要把链表还原
		while (n1 != null && n2 != null) {
			if (n2.value != n1.value) {
				res = false;
				break;
			}
			n2 = n2.next;
			n1 = n1.next;
		}
		// 恢复链表(反转)
		n2 = n3.next;// 后半部的头节点
		n3.next = null;// 后半部的头节点的next置为null
		while (n2 != null) {
			n1 = n2.next;
			n2.next = n3;// 改变当前节点的next指向
			n3 = n2;
			n2 = n1;
		}
		return res;
	}

	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {

		Node head = null;
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(2);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

	}
}
