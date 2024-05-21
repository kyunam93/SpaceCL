package chapter7;

public class Book {
	private String bookName;
	private String author;

	public Book() {	}

	public Book(String bookName, String auString) {
		this.bookName = bookName;
		this.author = auString;
	}

	public void showBookInfo() {
		System.out.println(bookName + ", " + author);
	}
	
	
	
	// GET | SET
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


}
