package chapter_2_ListProblem;

import java.util.HashSet;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem13_RemoveRepetition.java
 * @Description: 删除无序链表的重复节点
 * @CreatTime: 2016年9月7日 下午4:38:47
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem13_RemoveRepetition {
	public static class Node {
		public Node next;
		public int value;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: removeRep1
	 * @Description: 删除重复节点
	 * @param head
	 *            头节点
	 * @author pen
	 * @CreatTime: 2016年9月7日 下午5:13:43
	 */
	private static void removeRep1(Node head) {
		if (head.next == null || head == null)
			return;
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(head.value);
		Node pre = head;
		Node cur = head.next;
		while (cur != null) {
			if (set.contains(cur.value)) {
				pre.next = cur.next;// 干掉当前节点
			} else {
				set.add(cur.value);
				pre = cur;// 更新pre节点
			}
			cur = cur.next;// 循环动力
		}
	}

	/**
	 * @MethodName: removeRep2
	 * @Description: 删除重复节点
	 * @param head
	 *            头节点
	 * @author pen
	 * @CreatTime: 2016年9月7日 下午5:14:23
	 */
	private static void removeRep2(Node head) {
		Node cur = head;
		Node pre = null;
		Node next = null;
		while (cur != null) {// 选择排序法思想
			pre = cur;
			next = cur.next;
			while (next != null) {// 只比较选出来的节点(cur)后面的节点
				if (cur.value == next.value)// 从选出来的节点(cur)的下一个节点开始比较
					pre.next = next.next;// 相等的节点删除
				else
					pre = next;// 不相等的节点更新一下指针 位置
				next = next.next;// 循环动力
			}
			cur = cur.next;
		}
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
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(4);
		head.next.next.next.next.next = new Node(4);
		head.next.next.next.next.next.next = new Node(2);
		head.next.next.next.next.next.next.next = new Node(1);
		head.next.next.next.next.next.next.next.next = new Node(1);
		printLinkedList(head);
		removeRep1(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(1);
		head.next.next = new Node(3);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(4);
		head.next.next.next.next.next = new Node(4);
		head.next.next.next.next.next.next = new Node(2);
		head.next.next.next.next.next.next.next = new Node(1);
		head.next.next.next.next.next.next.next.next = new Node(1);
		printLinkedList(head);
		removeRep2(head);
		printLinkedList(head);

	}

}
