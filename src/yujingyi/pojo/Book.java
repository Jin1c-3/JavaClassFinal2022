package yujingyi.pojo;

public class Book {
	private String isbn;
	private String title;
	private String publisher;
	private String language;
	private Double price;

	public Book(String isbn, String title, String publisher, String language, String price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.publisher = publisher;
		this.language = language;
		this.price = Double.parseDouble(price);
	}

	// toString() and toStringArray()
	public String[] toStringArray() {
		return this.toString().split(",");
	}

	@Override
	public String toString() {
		return isbn + "," + title + "," + publisher + "," + language + "," + price;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

}
