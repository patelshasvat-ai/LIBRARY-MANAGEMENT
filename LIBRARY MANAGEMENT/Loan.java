import java.time.LocalDate;

/**
 * Represents a loan that connects one Member to one Book.
 * Records the borrow date and the due date.
 */
public class Loan {

    private Member    member;
    private Book      book;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    /**
     * Creates a Loan. Borrow date is today; due date is borrowDate + 14 days.
     */
    public Loan(Member member, Book book) {
        this.member     = member;
        this.book       = book;
        this.borrowDate = LocalDate.now();
        this.dueDate    = borrowDate.plusDays(14);
    }

    // ---------- Getters ----------
    public Member    getMember()     { return member; }
    public Book      getBook()       { return book; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate()    { return dueDate; }

    @Override
    public String toString() {
        return String.format("Loan[Member=%s, Book=\"%s\", Borrowed=%s, Due=%s]",
                member.getName(), book.getTitle(), borrowDate, dueDate);
    }
}
