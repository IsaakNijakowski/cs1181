
public class LinkedList {

	private Node head;
	private Node tail;
	
	public void add(String item) {
		
		Node newItem = new Node(item);
		
		// handles the case where the new item 
		// is the only thing in the list
		if (head == null) {
			head = newItem;
			tail = newItem;
			return;
		}
		
		tail.next = newItem;
		tail = newItem;
	}
	
	
	public void print() {
		Node current = head;
		while (current != null) {
			System.out.println(current.item);
			current = current.next;
		}
	}
	
	
	public void printWithSkips() {
		Node current = head;
		int skips = 1; // Starts at 1 because zero skips still moves over 1 node
		while (current != null) {
			System.out.println(current.item);
			for (int i = 0; i < skips; i++) {
				current = current.next;
				if (current == null) {
					break;
				}
			}
			skips++;
		}
	}

	
	class Node {
		String item;
		Node next;
		
		public Node(String item) {
			this.item = item;
			this.next = null;
		}
	}
}
