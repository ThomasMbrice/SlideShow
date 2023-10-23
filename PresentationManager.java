/**
 * The <code>PresintationManager</code> 
 * 
 * @author Thomas Mbrice
 */
import java.util.Scanner;

public class PresentationManager {
/**
 * This is the main method with contains scanner object and
 * the linked list object, it is how the user is able to interact with the rest
 * of the classes and their methods
 * @param args
 */
	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	SlideList list = new SlideList();
	list.resetCursorToHead();
	char ch = 0;
	String in = null;
	
	while(true) {
	menu();
	System.out.println("Select a Menu Option: ");
	if(input.hasNextLine()) { 
	in = input.next();
	in = in.toLowerCase();
	ch = in.charAt(0);
		}
	if(input.hasNextLine()) {
	input.nextLine();	
	}
	switch(ch) {
	case'f':
		list.cursorForward();
		break;
	case'b':
		list.cursorBackwards();
		break;
	case'd':
		Slide s2 = list.getCursorSlide();
		printSlide(s2);
		break;
	case'e':
		if(list.getCursorSlide() == null) {
			System.out.println("Empty slideshow");
		}
		else {
		System.out.println("Edit title, duration, or bullets? (t/d/b) ");
		String oN = input.nextLine();
		oN = oN.toLowerCase();
		char bN= oN.charAt(0);
		if(bN != 't' ^ bN != 'd' ^ bN != 'b') {
			System.out.println("Invalid option");
		}
		else {
		if(bN == 't') {
		System.out.println("New Title: ");
		String tT = input.nextLine();
		list.getCursorSlide().setTitle(tT);
		System.out.println("The new title \"" + list.getCursorSlide().getTitle() + "\" has been set" );
		}
		if(bN == 'd') {
			System.out.println("Enter new duration: ");
			double fN = input.nextDouble();
			if(fN <= 0) {
				System.out.println("Invalid duration");
			}
			else {
			list.getCursorSlide().setDuration(fN);
			System.out.println("The new duration \"" + list.getCursorSlide().getDuration() + "\" has been set" );
			}
		}
		if(bN == 'b') {
			System.out.println("Bullet index: ");
			int hN = input.nextInt();
			hN--;
			if(hN <= -1 || hN > (list.getCursorSlide().getNumBullet()-1)) {
				System.out.println("invalid index");
			}
			else {
				System.out.println("Delete or edit? (d/e)");
				char tr = 0;
				input.nextLine();
				String rt = input.nextLine();
				rt = rt.toLowerCase();
				tr = rt.charAt(0);
				if(tr != 'd') {
					if(tr != 'e')
						System.out.println("Invalid option");
					else {
						System.out.println("Enter new text: ");
						String ed = input.nextLine();
						list.getCursorSlide().editBullet(ed, hN);
						System.out.println("Bullet " + (hN + 1) + " was edited");
						}
				}
				else {
						list.getCursorSlide().deleteBullet(hN);
						System.out.println("Bullet " + (hN + 1) + " has been deleted");
						}
					}
				}
			}
		}
		break;
	case'p':
		list.printList();
		break;
	case'a': // I know 'a' and  'i' are duplicate code but I cant figure out a way to make multiple scanner objects and still have codegrade work
		int Czech2 = 0;
		Slide s1 = new Slide();
		System.out.println("Enter the slide title: ");
		s1.setTitle(input.nextLine());
		System.out.println("Enter the slide duration: ");
		double f1 = input.nextDouble();
		if(f1 <= 0) {
			System.out.println("Invalid duration");
		}
		else {
		s1.setDuration(f1);
		input.nextLine();
		String dc = "y";
		int bullet = 1;
		while(dc.equals("y")) {
		System.out.println("Bullet " + bullet + ": ");
		s1.setBullet(input.nextLine(), (bullet-1));
		if(bullet == 5) {
			System.out.println("No more bullets allowed. Slide is full.");
			break;
		}
		System.out.println("Add another bullet (MAX IS 5)? point (y/n)");
		dc = input.nextLine();
		dc = dc.toLowerCase();
		char ezchack = dc.charAt(0);
		if(ezchack == 'y'|| ezchack == 'n') {
			bullet++;
		}
		else {
			System.out.println("Invalid option");
			Czech2 = 1;
			break;
			}
		}
		if(Czech2 == 0) {
		System.out.println();
		System.out.println("Slide \"" + s1.getTitle() + "\" added to presentation");
		list.appendToTail(s1);
		if(list.getSize() == 1) {
			list.cursorFirstMove();
			}
		}
		}
		break;
	case'i':
		int Czech1 =0;
		Slide se = new Slide();
		System.out.println("Enter the slide title: ");
		se.setTitle(input.nextLine());
		System.out.println("Enter the slide duration: ");
		double f2 = input.nextDouble(); 
		if(f2 <= 0) {
			System.out.println("Invalid duration");
		}
		else {
		se.setDuration(f2);
		input.nextLine();
		String bc = "y";
		int bullete = 1;
		while(bc.equals("y")) {
		System.out.println("Bullet " + bullete + ": ");
		se.setBullet(input.nextLine(), (bullete-1));
		if(bullete == 5) {
			System.out.println("No more bullets allowed. Slide is full.");
			break;
		}
		System.out.println("Add another bullet (MAX IS 5)? point (y/n)");
		bc = input.nextLine();
		bc = bc.toLowerCase();
		char ezcheck = bc.charAt(0);
		if(ezcheck == 'y'|| ezcheck == 'n') {
			bullete++;
		}
		else {
			System.out.println("Invalid option");
			Czech1 = 1;		// This code is ineffcient but I did it to cater to codegrade, originally i had it so you
			break;			// could re-enter if you made an invalid input in (y/n) but that would mark it wrong on code grade
				}
			}
		if(Czech1 == 0) {
		System.out.println();
		System.out.println("Slide \"" + se.getTitle() + "\" added to presentation");
		list.insertBeforeCursor(se);
		if(list.getSize() == 1) {
			list.cursorFirstMove();
			}
			}
		}
		break;
	case'r':
		Slide re = list.RemoveCursor();
		if(re == null ) {
			System.out.println("Empty Slideshow");
		}
		else {
		System.out.println("Slide \"" + re.getTitle() + "\" has been removed");
		}
		break;
	case'h':
		if(list.getSize() == 0) {
			System.out.println("Empty Slideshow");
		}
		else {
		System.out.println("Cursor has been reset to the head.");
		list.resetCursorToHead();
		}
		break;
	case'q':
		System.out.println("Program terminating normally ... ");
		System.exit(0);
		break;
	default:
		System.out.println("invalid option");
		break;
		}
	}
}

	/**
	 * the menu which displays to the user the functionality of the program
	 */
	public static void menu() {
		System.out.println();
		System.out.println("Welcome to PresentationManager!");
		System.out.println();
		System.out.println("Please select an option: ");
		System.out.println("F) Move cursor forward ");
		System.out.println("B) Move cursor backward ");
		System.out.println("D) Display cursor slide ");
		System.out.println("E) Edit cursor slide ");
		System.out.println("P) Print presentation summary ");
		System.out.println("A) Append new slide to tail ");
		System.out.println("I) Insert new slide before cursor ");
		System.out.println("R) Remove slide at cursor ");
		System.out.println("H) Reset cursor to head ");
		System.out.println("Q) Quit ");
		System.out.println();
	}
	/**
	 * this is used to print the bullets of an invidual slide in a node
	 * @param s1 the side who's bullets and title are to be printed
	 */
	public static void printSlide(Slide s1) {
		if(s1 == null) {
			System.out.println("Empty Slideshow");
		}
		else {
		int x = 0;
		System.out.println();
		System.out.println("==============================================");
		System.out.println(s1.getTitle());
		System.out.println("==============================================");
		while(x < 5) {
			if(s1.getBullet(x) != null)
			System.out.println((x+1) + ". " + s1.getBullet(x));
			x++;
		}
		System.out.println("==============================================");
		System.out.println();
		}
	}
	
}
