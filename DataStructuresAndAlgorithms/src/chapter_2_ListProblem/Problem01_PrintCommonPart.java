package chapter_2_ListProblem;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem01_PrintCommonPart.java
 * @Description: 打印两个有序链表的公共部分
 * @CreatTime: 2016年9月4日 下午5:16:10
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem01_PrintCommonPart {
	/**
	 * @projectName: DataStructuresAndAlgorithms
	 * @className: Problem01_PrintCommonPart.java
	 * @Description: 实体类：链表的节点元素
	 * @CreatTime: 2016年9月4日 下午5:21:30
	 * @Author: pen
	 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
	 */
	public static class Node {
		// 类变量
		/**
		 * field param
		 */
		public int value;
		public Node next;

		// 构造函数
		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: printCommonPart
	 * @Description: 找出并打印公共部分
	 * @param head1
	 * @param head2
	 * @author pen
	 * @CreatTime: 2016年9月4日 下午5:35:05
	 */
	private static void printCommonPart(Node head1, Node head2) {
		System.out.print("Common Part:");
		while (head1 != null && head2 != null) { // 任何一个节点为null了即结束循环，由于是有序链表，只用next到下一个值
			if (head1.value > head2.value) {
				head2 = head2.next;
			} else if (head1.value < head2.value) {
				head1 = head1.next;
			} else {
				System.out.print(head1.value + "\t");
				head1 = head1.next;
				head2 = head2.next;
			}
		}
		System.out.println();
	}

	private static void printLinkedList(Node node) {
		System.out.print("Linked List:");
		while (node != null) {
			System.out.print(node.value + "\t");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node node1 = new Node(2);
		node1.next = new Node(3);
		node1.next.next = new Node(5);
		node1.next.next.next = new Node(6);

		Node node2 = new Node(1);
		node2.next = new Node(2);
		node2.next.next = new Node(5);
		node2.next.next.next = new Node(7);
		node2.next.next.next.next = new Node(8);

		printLinkedList(node1);
		printLinkedList(node2);
		printCommonPart(node1, node2);
	}

}
