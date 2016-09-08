package chapter_2_ListProblem;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem18_InsertNumToCircularList.java
 * @Description: 在环形有序链表中添加一个节点
 * @CreatTime: 2016年9月8日 下午2:40:46
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem18_InsertNumToCircularList {
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: insertNum
	 * @Description: 插入一个节点到有序环形链表中，新链表仍为有序环形链表
	 * @param head头节点
	 * @param num
	 *            新节点的值
	 * @return 插入之后的头节点
	 * @author pen
	 * @CreatTime: 2016年9月8日 下午2:49:38
	 */
	private static Node insertNum(Node head, int num) {
		Node node = new Node(num);// 新节点
		if (head == null) {// 新链表只有一个节点
			node.next = node;
			return node;
		}
		Node cur = head.next;
		Node pre = head;
		while (cur != head) {// 找到要插入的位置cur
			if (pre.value <= num && cur.value >= num) {
				break;
			}
			pre = cur;
			cur = cur.next;// 循环动力
		}
		pre.next = node;// 插入新节点
		node.next = cur;// 连接下一个节点
		return head.value < num ? head : node;// 返回新链表头节点
	}

	public static void printCircularList(Node head) {
		if (head == null) {
			return;
		}
		System.out.print("Circular List: " + head.value + " ");
		Node cur = head.next;
		while (cur != head) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println("-> " + head.value);
	}

	public static void main(String[] args) {
		Node head = null;
		head = insertNum(head, 2);
		printCircularList(head);
		head = insertNum(head, 1);
		printCircularList(head);
		head = insertNum(head, 4);
		printCircularList(head);
		head = insertNum(head, 3);
		printCircularList(head);
		head = insertNum(head, 5);
		printCircularList(head);
		head = insertNum(head, 0);
		printCircularList(head);

	}
}
