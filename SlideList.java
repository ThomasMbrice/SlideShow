/**
 * The <code>SlideList</code> implements a way for the user to interact
 * with the single node of the doubly linked list by traversing it and
 * returning the data of each node
 * 
 * @author Thomas Mbrice
 */
import java.text.DecimalFormat;

public class SlideList {
	
	private SlideListNode head;
	private SlideListNode tail;
	private SlideListNode cursor;
	private int size;
	DecimalFormat df = new DecimalFormat("#,##0.0");
	/**
	 * @var head is the first node in the linked list
	 * @var tail is the last node of the linked list
	 * @var cursor is the varible used by the user to interact with node of the linked list
	 * @var size is an int var to keep track of how many indivudal data containing nodes exist in the linked list
	 * @var df used for formating output in certain methods
	 */
	
	/**
	 * Intializes each doubly linked list object with head, cursor and tail nodes
	 */
	public SlideList() {
	head = new SlideListNode(null, null, null);
	tail = new SlideListNode(null, head, null);
	cursor = new SlideListNode(null, head, tail);
	head.setNext(tail);
	}
	/**
	 * @return how many data containg nodes exsist in the list
	 */
	public int getSize() {
		return size;
	}
	/**
	 * adds linked list node infront of tail
	 * @param data slide object 
	 */
	public void appendToTail(Slide data) {
		addNewNode(data, tail.getPrev(), tail);
	}
	/**
	 * uses the cursor's postion to insert a slide before that
	 * @param data slide object
	 * @pre if the size is 0 then the slide will just be added to tail
	 * @post cursor is moved to the new slide
	 */
	public void insertBeforeCursor(Slide data) {
		if(size == 0) {
			appendToTail(data);
		}
		else {
		addNewNode(data, cursor.getPrev(), cursor);
		cursorBackwards();
		}
	}
	/**
	 * rests cursor to node to head node
	 */
	public void resetCursorToHead() {
		cursor = head.getNext();
	}
	/**
	 * @pre cursor cannot move to tail node or to null / exit the list
	 * @post cursor moves and message is given to user
	 */
	public void cursorForward() {
		if(cursor.getNext() == null || cursor.getNext().getData() == null) {
			System.out.println("End of list cannot move forward");
		}
		else {
		cursor = cursor.getNext();
		System.out.println("The cursor moved forward to slide \"" + cursor.getData().getTitle() + "\"");
		}
	}
	/**
	 * @pre cursor cannot move to head node or to null / exit the list
	 * @post cursor moves and message is given to user
	 */
	public void cursorBackwards() {
		if(cursor.getPrev() == null || cursor.getPrev().getData() == null) {
			System.out.println("End of list cannot move backward");
		}
		else {
		cursor = cursor.getPrev();
		System.out.println("The cursor moved backward to slide \"" + cursor.getData().getTitle() + "\"");
		}
	}
	/**
	 * PRIVATE METHOD used by -appendToTail- and -insertBeforeCursor- so code isnt repeated
	 * @param data slide object
	 * @param before adress of prevous node
	 * @param after adress of next node
	 * @post increases size var
	 */
	private void addNewNode(Slide data, SlideListNode before, SlideListNode after) {
		size++;
		SlideListNode add = new SlideListNode(data, before, after);
		before.setNext(add);
		after.setPrev(add);
	}
	/**
	 * @return the slide object in the data var for the
	 * node the cursor is assigned to 
	 */
	public Slide getCursorSlide() {
		return cursor.getData();
	}
	/**
	 * removes the slide on the cursor
	 * @pre returns null if size var is 0
	 * @return returns the slide object that was removed
	 * @post node before gets adress of node after
	 * @post node after gets adress of node before
	 * @post size var is decreased
	 * @post cursor either goes backwards or returns to head
	 */
	public Slide RemoveCursor() {
		if(getSize() == 0) {
			return null;
		}
		else {
			SlideListNode after = cursor.getNext();
			SlideListNode before = cursor.getPrev();
			before.setNext(after);
			after.setPrev(before);
			size--;
			Slide s1 = cursor.getData();
			if(cursor.getPrev().getData() != null) {
				cursorBackwards();
			}
			else{
				resetCursorToHead();
			}
			return s1;
		}
	}
	/**
	 * prints list to console, visual representation of doubly linked list
	 */
	public void printList() { 
			System.out.println("Slideshow Summary: ");
			System.out.println("==============================================");
			System.out.println(String.format("Slide    Title     Duration     Bullets"));
			System.out.println("-----------------------------------------------");
			int br = 1, finbullets = 0;
			double fintime = 0;
			SlideListNode printListVar = head.getNext();
			while(printListVar != tail) {
				if(printListVar == cursor) {
				System.out.println(String.format("%-10s%-5s%10s%10s"," -> " + br, printListVar.getData().getTitle(),
						df.format(printListVar.getData().getDuration()), printListVar.getData().getNumBullet()));
				}
				else {
				System.out.println(String.format("%-10s%-5s%10s%10s",br, printListVar.getData().getTitle(),
						df.format(printListVar.getData().getDuration()), printListVar.getData().getNumBullet()));
				}
				fintime += printListVar.getData().getDuration();
				finbullets += printListVar.getData().getNumBullet();
				printListVar = printListVar.getNext();
				br++;
			}
			System.out.println("==============================================");
			System.out.println("Total: " + size + " slide(s), " + df.format(fintime) + " minute(s), " + finbullets + " bullet(s)");
			System.out.println("==============================================");

	}
	/**
	 * when size is 0 this is used to place cursor on the first slide
	 * very general because it was also used for testing and debugging
	 */
	public void cursorFirstMove() {
		if(cursor.getNext() != null)
		cursor = cursor.getNext();
		if(cursor.getNext() == null)
		cursor = cursor.getPrev();
	}
	
}
