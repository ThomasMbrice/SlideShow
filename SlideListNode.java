/**
 * The <code>Slide list node</code> class implements 
 * the SlidelistNode object which includes the doubly linked list
 * format for a single node
 * 
 * @author Thomas Mbrice
 */

public class SlideListNode {

	private Slide data;
	private SlideListNode next;
	private SlideListNode prev;
	
	/**Invariants:
	 * @var data is the slide object and represents the data of a doubly linked list 
	 * @var next is the object adress of the next node in the list
	 * @var prev is the object adress of the past node in the list
	 */
	
	
	/**
	 * creates instance of slidelistnode object
	 * @param data data of node
	 * @param prev prev adress of node in list
	 * @param next next adress of node in list
	 */
	public SlideListNode(Slide data, SlideListNode prev,SlideListNode next ) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
	/**
	 * intalized slide only using data - has no use in final program used for testing 
	 * @param data data of node
	 */
	public SlideListNode(Slide data) {
		this.data = data;
	}
	/**
	 * defult constructor
	 */
	public SlideListNode() {
	}
	/**
	 * @return slide object stored in data varrible
	 */
	public Slide getData() {
		return data;
	}
	/**
	 * @return adress of next object in linked list
	 */
	public SlideListNode getNext() {
		return next;
	}
	/**
	 * @return adress of prev object in linked list
	 */
	public SlideListNode getPrev() {
		return prev;
	}
	/**
	 * @param newSlide sets the data var with new slide object
	 */
	public void setSide(Slide newSlide) {
		data = newSlide;
	}
	/**
	 * @param newSlide sets the next var with new slideListNode object
	 */
	public void setNext(SlideListNode newNext) {
		next = newNext;
	}
	/**
	 * @param newSlide sets the data var with new slideListNode object
	 */
	public void setPrev(SlideListNode newPrev) {
		prev = newPrev;
	}

}
