package chapter_2_ListProblem;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem20_RelocateLinkedList.java
 * @Description: 将一个链表分为左右半区后间插合并
 * @CreatTime: 2016年9月8日 下午3:47:49
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem20_RelocateLinkedList {
	public static class Node {
		public int value;
		public Node next;

		public Node(int value) {
			this.value = value;
		}
	}

	/**
	 * @MethodName: relocate
	 * @Description: 间插合并链表左右半区
	 * @param head头节点
	 * @author pen
	 * @CreatTime: 2016年9月8日 下午4:17:54
	 */
	public static void relocate(Node head) {
		if (head == null || head.next == null)
			return;
		Node mid = head;
		Node right = head.next;
		while (right.next != null && right.next.next != null) {
			mid = mid.next;// 最终将移到中间节点
			right = right.next.next;// 设置步长
		}
		right = mid.next;// 右半区头节点
		mid.next = null;// 变成左半区末节点
		// 合并
		mergeLR(head, right);
	}

	/**
	 * @MethodName: mergeLR
	 * @Description: 间插合并两个链表
	 * @param left链表1头节点
	 * @param right链表2头节点
	 * @author pen
	 * @CreatTime: 2016年9月8日 下午4:19:02
	 */
	public static void mergeLR(Node left, Node right) {
		Node next = null;
		// 形如：[left,right,left.next,right.next,left.next.next...]
		while (left.next != null) {// 循环动力在条件中包含
			next = right.next;// 保存右半区的下一节点
			// 从后往前关联
			right.next = left.next;
			left.next = right;
			left = right.next;
			right = next;// 右半区指针移动
		}
		left.next = right;
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
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		relocate(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		head.next.next.next.next.next.next = new Node(7);
		relocate(head);
		printLinkedList(head);

	}
}
