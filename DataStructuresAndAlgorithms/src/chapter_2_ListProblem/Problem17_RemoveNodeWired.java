package chapter_2_ListProblem;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem17_RemoveNodeWired.java
 * @Description: TODO
 * @CreatTime: 2016年9月8日 下午2:28:51
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem17_RemoveNodeWired {
	public static class Node {
		Node next;
		int value;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: removeNodeWired
	 * @Description: 移除给定节点
	 * @param node
	 *            给定节点
	 * @author pen
	 * @CreatTime: 2016年9月8日 下午2:38:35
	 */
	public static void removeNodeWired(Node node) {
		if (node == null)
			return;
		Node next = node.next;
		if (next == null) {
			throw new RuntimeException("you cannot remove a last node!");
		}
		// 把后一个节点复制到前面节点
		node.next = next.next;
		node.value = next.value;
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
		Node node = head;
		printLinkedList(head);
		removeNodeWired(node);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		node = head.next;
		printLinkedList(head);
		removeNodeWired(node);
		printLinkedList(head);
		// 最后一个节点不能移除
		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		node = head.next.next;
		printLinkedList(head);
		removeNodeWired(node);
		printLinkedList(head);

	}

}
