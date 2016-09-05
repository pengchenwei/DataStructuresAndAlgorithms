package chapter_2_ListProblem;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem04_ReverseList.java
 * @Description: TODO
 * @CreatTime: 2016年9月5日 上午11:58:41
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem04_ReverseList {
	/**
	 * @projectName: DataStructuresAndAlgorithms
	 * @className: Problem04_ReverseList.java
	 * @Description: 单向链表实体类
	 * @CreatTime: 2016年9月5日 下午1:41:11
	 * @Author: pen
	 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
	 */
	public static class Node {
		private int value;
		private Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @projectName: DataStructuresAndAlgorithms
	 * @className: Problem04_ReverseList.java
	 * @Description: 双向链表实体类
	 * @CreatTime: 2016年9月5日 下午1:41:34
	 * @Author: pen
	 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
	 */
	public static class DoubleNode {
		private int value;
		private DoubleNode next;
		private DoubleNode last;

		public DoubleNode(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: reverseList
	 * @Description: 单向链表反转
	 * @param head
	 * @return 反转之后头节点
	 * @author pen
	 * @CreatTime: 2016年9月5日 下午1:42:02
	 */
	public static Node reverseList(Node head) {
		Node pre = null;
		Node next = null;
		while (head != null) {// 设置每一个节点的next关系
			next = head.next;// 下一步执行后当前节点(head)的下一个节点会变
			head.next = pre;// 把循环到的当前节点的next置为前一个节点
			pre = head;// 更新pre的指向，while循环完pre指向原来链表的最后一个节点
			head = next;// 更新while中的循环条件,即第一部保存的当前节点(head)的下一节点
		}
		return pre;
	}

	/**
	 * @MethodName: reverseList
	 * @Description: 方法重载，双向链表反转
	 * @param head
	 * @return 头节点
	 * @author pen
	 * @CreatTime: 2016年9月5日 下午2:32:26
	 */
	public static DoubleNode reverseList(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
		while (head != null) {// 设置每一个节点的last和next关系
			next = head.next;
			head.next = pre;// 此处的pre是上一个循环的pre值，也即上一个循环的(head)节点,等于本次循环的head.last
			head.last = next;
			pre = head;// 更新pre指向
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

	public static void printDoubleLinkedList(DoubleNode head) {
		System.out.print("Double Linked List: ");
		DoubleNode end = null;
		while (head != null) {
			System.out.print(head.value + " ");
			end = head;// 更新指针指向
			head = head.next;
		}
		System.out.print("| ");
		while (end != null) {
			System.out.print(end.value + " ");
			end = end.last;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		printLinkedList(head1);
		head1 = reverseList(head1);
		printLinkedList(head1);

		DoubleNode head2 = new DoubleNode(1);
		head2.next = new DoubleNode(2);
		head2.next.last = head2;
		head2.next.next = new DoubleNode(3);
		head2.next.next.last = head2.next;
		head2.next.next.next = new DoubleNode(4);
		head2.next.next.next.last = head2.next.next;
		printDoubleLinkedList(head2);
		printDoubleLinkedList(reverseList(head2));

	}

}
