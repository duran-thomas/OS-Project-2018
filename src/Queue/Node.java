package Queue;

public class Node {
	
	private Process data; 
	private Node prevNode; 
	private Node nextNode;
	
	public Node() { 
		this.data =null; 
		prevNode = null; 
		nextNode = null; 
		}
	
	public Node(Process data){
		this.data=data;
		this.prevNode=null;
		this.nextNode=null;
		}
	
	public Node(Node node){
		this.data=node.data;
		this.prevNode=node.prevNode;
		this.nextNode=node.nextNode;
		}

	public Process getData() {
		return data;
	}

	public void setData(Process data) {
		this.data = data;
	}

	public Node getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(Node prevNode) {
		this.prevNode = prevNode;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	
	
	
	
}
