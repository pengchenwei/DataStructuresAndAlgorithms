package chapter_2_ListProblem;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem03_RemoveNodeByRatio.java
 * @Description: TODO
 * @CreatTime: 2016年9月5日 上午10:39:07
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem03_RemoveNodeByRatio {
	public static class Node {
		private Node next;
		private int value;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: removeMidNode
	 * @Description: 找到链表中间节点并删除
	 * @param head
	 *            链表头节点
	 * @return 新链表头节点
	 * @author pen
	 * @CreatTime: 2016年9月5日 上午10:47:03
	 */
	public static Node removeMidNode(Node head) {
		if (head == null || head.next == null)// 最多只有一个节点的情况
			return head;
		if (head.next != null && head.next.next == null)// 两个节点的情况
			return head.next;
		// 大于两个节点的情况
		Node pre = head;
		Node cur = head.next.next;// 第三个节点
		while (cur.next != null && cur.next.next != null) {// 每增加两个节点要删除中间节点才会向后移动一位
			/*
			 * 记录中间位置，循环完之后pre是中间节点的前一节点
			 */
			pre = pre.next;// pre每次移动一位
			cur = cur.next.next;// cur每次移动两位
		}
		pre.next = pre.next.next;// 跳过pre的下一个节点
		return head;
	}

	/**
	 * @MethodName: removeByRatio
	 * @Description: 删除链表a/b处节点
	 * @param head
	 *            头节点
	 * @param a
	 * @param b
	 * @return 新链表头节点
	 * @author pen
	 * @CreatTime: 2016年9月5日 上午11:36:33
	 */
	private static Node removeByRatio(Node head, int a, int b) {
		// 不满足条件的
		if (a < 1 || a > b)
			return head;
		// 1.找到链表长度n
		int n = 0;
		Node cur = head;
		while (cur != null) {
			cur = cur.next;
			n++;
		}
		// 2.找到要删除节点的节点数
		n = (int) Math.ceil((double) (a * n) / (double) b);// 向上取整，注意double的用法会影响结果，只有相除的两个数同为double结果才为double
		if (n == 1)// 删除第一个节点
			return head.next;
		if (n > 1) {
			cur = head;
			while (--n != 1)// 找到删除节点的前一个节点
				cur = cur.next;
			cur.next = cur.next.next;
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

	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		printLinkedList(head);
		head = removeMidNode(head);
		printLinkedList(head);
		head = removeByRatio(head, 2, 5);
		printLinkedList(head);
		head = removeByRatio(head, 1, 3);
		printLinkedList(head);
	}

}
