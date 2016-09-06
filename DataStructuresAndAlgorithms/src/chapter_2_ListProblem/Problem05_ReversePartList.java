package chapter_2_ListProblem;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem05_ReversePartList.java
 * @Description: 反转链表部分节点
 * @CreatTime: 2016年9月5日 下午2:42:20
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem05_ReversePartList {
	public static class Node {
		Node next;
		int value;

		public Node(int data) {
			this.value = data;
		}
	}

	private static Node reversePart(Node head, int from, int to) {
		int len = 0;
		Node node1 = head;// 存起来，否则在if语句中head会变
		Node fPre = null;// 存放边界点外的节点
		Node tPos = null;
		while (node1 != null) {
			len++;
			fPre = len == from - 1 ? node1 : fPre;
			tPos = len == to + 1 ? node1 : tPos;
			node1 = node1.next;
		}
		// 不满足条件
		if (from > to || from < 1 || to > len)
			return head;
		// 满足条件，进行反转部分
		node1 = fPre == null ? head : fPre.next;// 确定反转部分的头节点
		Node node2 = node1.next;// 保存反转部分的头节点的下一个节点
		node1.next = tPos;// 把反转部分的尾节点与原链表的截断部分(tPos节点)连接
		Node next = null;
		while (node2 != tPos) {// 注意取值范围包括了tPos，这一步还没把反转之后的尾部连接起来了
			next = node2.next;
			node2.next = node1;
			node1 = node2;
			node2 = next;// 循环条件
		}
		// 接下来把反转部分的头节点与原链表的截断部分(fPre节点)连接
		if (fPre != null) {// 为假说明反转部分的头节点是从原链表的头节点开始的
			fPre.next = node1;// 此时node1已经是反转部分的头节点
			return head;
		}
		return node1;
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
		printLinkedList(head);
		head = reversePart(head, 1, 1);
		printLinkedList(head);

		head = new Node(1);
		printLinkedList(head);
		head = reversePart(head, 1, 1);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		printLinkedList(head);
		head = reversePart(head, 1, 2);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		printLinkedList(head);
		head = reversePart(head, 2, 3);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		printLinkedList(head);
		head = reversePart(head, 1, 3);
		printLinkedList(head);

	}

}
