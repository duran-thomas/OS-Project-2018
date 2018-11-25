package DoublyLinkedList;

//Doubly linked list

public class LinkNode {
	private Data data; 
    private LinkNode next;
    private LinkNode prev;
    
    public LinkNode() {
    	this.data= new Data();
    	this.next=null;
    	this.prev=null;
    }
    
    public LinkNode(Data data) {
    	this.data=data;
    	this.next=null;
    	this.prev=null;
    }
    
    public LinkNode(LinkNode node){
		this.data=node.data;
		this.next=node.next;
		 
		}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public LinkNode getNext() {
		return next;
	}

	public void setNext(LinkNode next) {
		this.next = next;
	}

	public LinkNode getPrev() {
		return prev;
	}

	public void setPrev(LinkNode prev) {
		this.prev = prev;
	}
    
    
}
