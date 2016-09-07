package chapter_2_ListProblem;

import java.util.Stack;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem12_ConvertEveryKNodeaList.java
 * @Description: 每k个节点反转
 * @CreatTime: 2016年9月7日 下午2:03:32
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem12_ConvertEveryKNodeaList {
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node reverseKNodes1(Node head, int k) {
		if (k < 2) {
			return head;
		}
		// 每隔k个节点反转链表，注意每k个节点的头尾节点
		Stack<Node> s = new Stack<Node>();
		Node next = null;
		Node pre = null;
		Node newHead = head;
		Node cur = head;
		while (cur != null) {
			next = cur.next;
			s.push(cur);
			if (s.size() == k) {// 集齐k个节点可以反转链表了
				pre = resign1(s, pre, next);// 保存每k个节点反转后的末节点，pre是下个k个节点组成链表的左节点
				newHead = newHead == head ? cur : newHead;// 确定最终返回的头节点，只在第一次反转时变一次
			}
			cur = next;// 循环动力
		}
		return newHead;
	}

	/**
	 * @MethodName: resign1
	 * @Description: k个节点反转，并与上(下)k个节点组成链表的末(头)节点相连
	 * @param stack栈中依次压入的k个节点
	 * @param left上k个节点组成链表的末节点
	 * @param right下k个节点组成链表的头节点
	 * @return 本次k个节点链表的末节点，即下k个节点组成链表的left
	 * @author pen
	 * @CreatTime: 2016年9月7日 下午2:33:55
	 */
	private static Node resign1(Stack<Node> stack, Node left, Node right) {
		Node cur = stack.pop();
		if (left != null) {// 如果上k个节点的末节点不为空的话就相连
			left.next = cur;
		}
		// 将栈中节点依序相连
		Node next = null;
		while (!stack.empty()) {
			next = stack.pop();
			cur.next = next;
			cur = next;
		}
		cur.next = right;// 与下k个节点的头节点相连
		return cur;
	}

	private static Node reverseKNodes2(Node head, int k) {
		if (k < 2)
			return head;
		Node next = null;
		Node cur = head;
		Node pre = null;
		Node start = null;
		int count = 1;
		while (cur != null) {
			next = cur.next;
			if (count == k) {
				// 找到left，start，end，right节点
				start = pre == null ? head : pre.next;// pre==null：判断是否是第一组数
				head = pre == null ? cur : head;// 第一组数的时候把末节点置为链表的头节点，以后就不用改head的值了
				resign2(pre, start, cur, next);
				pre = start;
				count = 0;
			}
			count++;
			cur = next;
		}
		return head;
	}

	/**
	 * @MethodName: resign2
	 * @Description: 每一组k个节点的反转，并连接
	 * @param left上k个节点组成链表的末节点
	 * @param start本组数的头节点
	 * @param end本组数的末节点
	 * @param right下k个节点组成链表的头节点
	 * @author pen
	 * @CreatTime: 2016年9月7日 下午4:30:48
	 */
	private static void resign2(Node left, Node start, Node end, Node right) {
		Node cur = start.next;// start的next单独配置(最后一步)
		Node pre = start;// 确定反转之后的末节点为start
		Node next = null;
		while (cur != right) {// 反转
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		if (left != null)// 处理头节点和末节点的连接
			left.next = end;
		start.next = right;
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
		Node head = null;
		int K = 3;
		printLinkedList(head);
		head = reverseKNodes1(head, K);
		printLinkedList(head);
		head = reverseKNodes2(head, K);
		printLinkedList(head);
		System.out.println("=======================");

		head = new Node(1);
		K = 3;
		printLinkedList(head);
		head = reverseKNodes1(head, K);
		printLinkedList(head);
		head = reverseKNodes2(head, K);
		printLinkedList(head);
		System.out.println("=======================");

		head = new Node(1);
		head.next = new Node(2);
		K = 2;
		printLinkedList(head);
		head = reverseKNodes1(head, K);
		printLinkedList(head);
		head = reverseKNodes2(head, K);
		printLinkedList(head);
		System.out.println("=======================");

		head = new Node(1);
		head.next = new Node(2);
		K = 3;
		printLinkedList(head);
		head = reverseKNodes1(head, K);
		printLinkedList(head);
		head = reverseKNodes2(head, K);
		printLinkedList(head);
		System.out.println("=======================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		K = 2;
		printLinkedList(head);
		head = reverseKNodes1(head, K);
		printLinkedList(head);
		head = reverseKNodes2(head, K);
		printLinkedList(head);
		System.out.println("=======================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		head.next.next.next.next.next.next = new Node(7);
		head.next.next.next.next.next.next.next = new Node(8);
		K = 3;
		printLinkedList(head);
		head = reverseKNodes1(head, K);
		printLinkedList(head);
		head = reverseKNodes2(head, K);
		printLinkedList(head);
		System.out.println("=======================");

	}
}
