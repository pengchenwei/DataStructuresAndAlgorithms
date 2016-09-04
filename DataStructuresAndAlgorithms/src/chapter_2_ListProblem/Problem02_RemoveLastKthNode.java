package chapter_2_ListProblem;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem02_RemoveLastKthNode.java
 * @Description: TODO
 * @CreatTime: 2016年9月4日 下午9:23:18
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem02_RemoveLastKthNode {
	/**
	 * @projectName: DataStructuresAndAlgorithms
	 * @className: Problem02_RemoveLastKthNode.java
	 * @Description: 实体类：单链表节点
	 * @CreatTime: 2016年9月4日 下午9:40:03
	 * @Author: pen
	 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
	 */
	public static class Node {
		// 类变量
		int value = 0;
		Node next;

		// 构造函数
		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: removeLastKthNode
	 * @Description: remove单链表的倒数第K个元素
	 * @param head
	 *            链表的头节点
	 * @param Kth
	 *            倒数第k个元素
	 * @return 去除倒数第k个元素后链表的头节点
	 * @author pen
	 * @CreatTime: 2016年9月4日 下午9:41:03
	 */
	private static Node removeLastKthNode(Node head, int Kth) {
		if (Kth < 1 || head == null)// 条件验证
			return head;
		// 满足条件，继续下面的步骤
		Node cur = head;// 记录头节点
		while (cur != null) {// 遍历完节点后根据Kth的值来分情况讨论
			Kth--;
			cur = cur.next;// 这里的next相当于循环条件中的i++功能
		}
		if (Kth == 0)// Kth=0说明倒数第k个元素是原链表的头节点
			head = head.next;// 只需要把头节点去掉即可，也即把新链表的头节点设为原来链表的第二个节点
		if (Kth < 0) {// Kth<0说明倒数第k个元素在链表中间，这事需要找到倒数第k个节点的前一个节点，并处理它的前后节点指向
			cur = head;// 返回的仍然是原来的头节点，只是倒数第k个节点需要处理一下
			while (++Kth == 0)// 找到倒数第k个节点的前一个节点的条件
				cur = cur.next;
			cur.next = cur.next.next;// 将倒数第k个节点的前一个节点的next连接到next的next节点上即可
		}
		return head;
	}

	/**
	 * @projectName: DataStructuresAndAlgorithms
	 * @className: Problem02_RemoveLastKthNode.java
	 * @Description: 实体类
	 * @CreatTime: 2016年9月4日 下午10:08:56
	 * @Author: pen
	 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
	 */
	public static class DoubleNode {
		// 类变量
		int value = 0;
		DoubleNode next;
		DoubleNode last;

		// 构造函数
		public DoubleNode(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: removeLastKthNode
	 * @Description: 删除双向链表的倒数第k个元素
	 * @param head
	 *            头节点
	 * @param Kth
	 *            倒数第k个数
	 * @return 新链表的头节点
	 * @author pen
	 * @CreatTime: 2016年9月4日 下午10:10:54
	 */
	private static DoubleNode removeLastKthNode(DoubleNode head, int Kth) {
		if (Kth < 0 || head == null)
			return head;
		DoubleNode cur = head;
		while (cur.next != null) {
			Kth--;
			cur = cur.next;
		}
		if (Kth == 0) {
			head = head.next;// 更新记录下一个节点(next)的信息
			head.last = null;// 更新记录上一个节点(last)的信息
		}
		if (Kth < 0) {
			cur = head;
			while (++Kth == 0)
				cur = cur.next;
			DoubleNode newDNode = cur.next.next;
			cur.next = newDNode;// 更新倒数第k个节点的前一个节点的下一个节点(next)的信息
			if (newDNode != null)// 只有在倒数第k-1个节点存在时才需要更新其上一个(last)节点的信息
				newDNode.last = cur;
		}
		return head;
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
			end = head;
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
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		printLinkedList(head1);
		head1 = removeLastKthNode(head1, 3);
		// head1 = removeLastKthNode(head1, 6);
		// head1 = removeLastKthNode(head1, 7);
		printLinkedList(head1);

		DoubleNode head2 = new DoubleNode(1);
		head2.next = new DoubleNode(2);
		head2.next.last = head2;
		head2.next.next = new DoubleNode(3);
		head2.next.next.last = head2.next;
		head2.next.next.next = new DoubleNode(4);
		head2.next.next.next.last = head2.next.next;
		head2.next.next.next.next = new DoubleNode(5);
		head2.next.next.next.next.last = head2.next.next.next;
		head2.next.next.next.next.next = new DoubleNode(6);
		head2.next.next.next.next.next.last = head2.next.next.next.next;
		printDoubleLinkedList(head2);
		head2 = removeLastKthNode(head2, 3);
		// head2 = removeLastKthNode(head2, 6);
		// head2 = removeLastKthNode(head2, 7);
		printDoubleLinkedList(head2);

	}
}
