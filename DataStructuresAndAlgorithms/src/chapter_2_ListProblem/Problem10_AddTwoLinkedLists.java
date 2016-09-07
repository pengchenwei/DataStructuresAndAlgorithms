package chapter_2_ListProblem;

import java.util.Stack;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem10_AddTwoLinkedLists.java
 * @Description: TODO
 * @CreatTime: 2016年9月7日 上午9:46:00
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem10_AddTwoLinkedLists {
	public static class Node {
		public Node next;
		public int value;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: addLists1
	 * @Description: 两个链表求和，生成新的链表
	 * @param head1头节点
	 * @param head2头节点
	 * @return 新链表的头节点
	 * @author pen
	 * @CreatTime: 2016年9月7日 上午10:57:18
	 */
	private static Node addLists1(Node head1, Node head2) {
		// 分别压入两个栈中，因为要求和进位只能从末尾开始计算
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();
		while (head1 != null) {
			s1.push(head1.value);
			head1 = head1.next;
		}
		while (head2 != null) {
			s2.push(head2.value);
			head2 = head2.next;
		}
		// 开始计算，并创建“和链表”
		int n1 = 0;
		int n2 = 0;
		int ca = 0;
		int n = 0;
		Node pre = null;
		Node node = null;
		while (!s1.empty() || !s2.empty()) {// 两个链表不一样长
			n1 = s1.empty() ? 0 : s1.pop();
			n2 = s2.empty() ? 0 : s2.pop();
			n = n1 + n2 + ca;// 加上进位值
			// 关联节点
			pre = node;// 保存上一次创建的节点
			node = new Node(n % 10);// 根据求和的值创建新节点
			node.next = pre;// 设置当前节点与上一个节点之间的关系
			ca = n / 10;// 取进位
		}
		if (ca == 1) {// 最后一次求和有进位的情况
			pre = node;// 与上面一样，关联节点
			node = new Node(1);
			node.next = pre;
		}
		return node;
	}

	private static Node addLists2(Node head1, Node head2) {
		// 反转链表
		head1 = reverseList(head1);
		head2 = reverseList(head2);
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		int ca = 0;
		Node h1 = head1;// 保存头节点，最后一步还原链表是会用
		Node h2 = head2;
		Node pre = null;
		Node node = null;
		while (h1 != null || h2 != null) {
			n1 = h1 == null ? 0 : h1.value;
			n2 = h2 == null ? 0 : h2.value;
			n = n1 + n2 + ca;
			pre = node;
			node = new Node(n % 10);
			node.next = pre;
			ca = n / 10;
			// 循环动力
			h1 = h1 != null ? h1.next : null;
			h2 = h2 != null ? h2.next : null;
		}
		// 根据进位判断头节点是否为1
		if (ca == 1) {
			pre = node;
			node = new Node(1);
			node.next = pre;
		}
		// 还原成原来的链表
		reverseList(head1);
		reverseList(head2);
		return node;
	}

	/**
	 * @MethodName: reverseList
	 * @Description: 链表反转
	 * @param head头节点
	 * @return 反转链表头节点
	 * @author pen
	 * @CreatTime: 2016年9月7日 上午10:59:38
	 */
	private static Node reverseList(Node head) {
		Node next = null;
		Node pre = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	public static void printLinkedList(Node head) {
		System.out.print("Linked List: ");
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head1 = new Node(9);
		head1.next = new Node(9);
		head1.next.next = new Node(9);

		Node head2 = new Node(1);

		printLinkedList(head1);
		printLinkedList(head2);
		printLinkedList(addLists1(head1, head2));
		printLinkedList(addLists2(head1, head2));

	}

}
