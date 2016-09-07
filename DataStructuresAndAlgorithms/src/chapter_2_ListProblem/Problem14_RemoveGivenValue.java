package chapter_2_ListProblem;

import java.util.Stack;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem14_RemoveGivenValue.java
 * @Description: 删除链表中指定节点
 * @CreatTime: 2016年9月7日 下午5:17:14
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem14_RemoveGivenValue {
	public static class Node {
		private int value;
		private Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: removeValue1
	 * @Description: 删除指定节点
	 * @param head头节点
	 * @param num指定节点值
	 * @return 删除后的链表头节点
	 * @author pen
	 * @CreatTime: 2016年9月7日 下午8:46:45
	 */
	private static Node removeValue1(Node head, int num) {
		Stack<Node> stack = new Stack<Node>();
		while (head != null) {
			if (head.value != num)
				stack.push(head);
			head = head.next;
		}
		while (!stack.empty()) {
			stack.peek().next = head;// 上面的循环结束后head指向了末节点
			head = stack.pop();// 一个一个返回，本循环结束后，head指向最先压入栈的值
		}
		return head;
	}

	/**
	 * @MethodName: removeValue1
	 * @Description: 删除指定节点
	 * @param head头节点
	 * @param num指定节点值
	 * @return 删除后的链表头节点
	 * @author pen
	 * @CreatTime: 2016年9月7日 下午8:47:47
	 */
	private static Node removeValue2(Node head, int num) {
		while (head != null) {
			if (head.value != num)
				break;
			head = head.next;// 找到第一个不等于num的节点，即为最后的头节点
		}
		Node pre = head;// 保存上一个节点信息
		Node cur = head;// 保存头节点信息
		while (cur != null) {
			if (cur.value == num)// 如果是要删除的
				pre.next = cur.next;// 删除当前节点
			else
				pre = cur;// 更新指针
			cur = cur.next;// 循环动力
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
		head.next = new Node(1);
		head.next.next = new Node(3);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(1);
		head.next.next.next.next.next = new Node(2);
		head.next.next.next.next.next.next = new Node(1);
		head.next.next.next.next.next.next.next = new Node(1);
		head = removeValue1(head, 1);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(1);
		head.next.next = new Node(3);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(1);
		head.next.next.next.next.next = new Node(2);
		head.next.next.next.next.next.next = new Node(1);
		head.next.next.next.next.next.next.next = new Node(1);
		head = removeValue2(head, 1);
		printLinkedList(head);

	}
}
