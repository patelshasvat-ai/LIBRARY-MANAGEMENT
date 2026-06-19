/**
 * Represents a book in the community library system.
 * Encapsulates ISBN, title, author, and availability status.
 */
public class Book {

    private String isbn;
    private String title;
    private String author;
    private boolean available;

    /**
     * Constructor 1: Creates a Book with ISBN and title only.
     * Author defaults to "Unknown". Book starts as available.
     */
    public Book(String isbn, String title) {
        this.isbn      = isbn;
        this.title     = title;
        this.author    = "Unknown";
        this.available = true;
    }

    /**
     * Constructor 2 (overload): Creates a Book with full details.
     * Book starts as available.
     */
    public Book(String isbn, String title, String author) {
        this.isbn      = isbn;
        this.title     = title;
        this.author    = author;
        this.available = true;
    }

    // ---------- Getters ----------
    public String  getIsbn()      { return isbn; }
    public String  getTitle()     { return title; }
    public String  getAuthor()    { return author; }
    public boolean isAvailable()  { return available; }

    // ---------- Setters ----------
    public void setIsbn(String isbn)          { this.isbn   = isbn; }
    public void setTitle(String title)        { this.title  = title; }
    public void setAuthor(String author)      { this.author = author; }
    public void setAvailable(boolean available){ this.available = available; }

    @Override
    public String toString() {
        return String.format("Book[ISBN=%s, Title=\"%s\", Author=%s, Available=%s]",
                isbn, title, author, available ? "Yes" : "No");
    }
}
