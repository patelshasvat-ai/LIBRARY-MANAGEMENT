import java.util.ArrayList;
import java.util.List;

/**
 * The central Library class.
 * Holds the full collection of books and members.
 * Provides operations: addBook, registerMember, lendBook, returnBook, searchByTitle.
 *
 * Relationship justification:
 *   - Library <>-- Book   : COMPOSITION  — books are created for and owned by the library;
 *                           they have no meaningful existence outside it.
 *   - Library <>-- Member : AGGREGATION  — members are independent people who can exist
 *                           outside the library (they may leave without the library ceasing to exist).
 */
public class Library {

    private List<Book>   books;
    private List<Member> members;

    public Library() {
        this.books   = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    // ---------- Management ----------
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void registerMember(Member member) {
        members.add(member);
        System.out.println("Member registered: " + member.getName());
    }

    /**
     * Lends a book to a member.
     * Enforces the "at most one active loan per book" rule.
     */
    public Loan lendBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("ERROR: Book with ISBN " + isbn + " not found.");
            return null;
        }
        if (!book.isAvailable()) {
            System.out.println("ERROR: \"" + book.getTitle() + "\" is already on loan — request rejected.");
            return null;
        }
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("ERROR: Member with ID " + memberId + " not found.");
            return null;
        }

        Loan loan = new Loan(member, book);
        book.setAvailable(false);
        member.addLoan(loan);
        System.out.println("Lent: \"" + book.getTitle() + "\" to " + member.getName()
                + " (due: " + loan.getDueDate() + ")");
        return loan;
    }

    /**
     * Returns a book previously lent to a member.
     */
    public void returnBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("ERROR: Book with ISBN " + isbn + " not found.");
            return;
        }
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("ERROR: Member with ID " + memberId + " not found.");
            return;
        }

        // Find the matching loan
        Loan targetLoan = null;
        for (Loan loan : member.getLoans()) {
            if (loan.getBook().getIsbn().equals(isbn)) {
                targetLoan = loan;
                break;
            }
        }

        if (targetLoan == null) {
            System.out.println("ERROR: No active loan found for ISBN " + isbn
                    + " under member " + member.getName());
            return;
        }

        member.removeLoan(targetLoan);
        book.setAvailable(true);
        System.out.println("Returned: \"" + book.getTitle() + "\" from " + member.getName());
    }

    /** Searches books by title (case-insensitive, partial match). */
    public List<Book> searchByTitle(String titleQuery) {
        List<Book> results = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(titleQuery.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }

    // ---------- State display ----------
    public void printState() {
        System.out.println("\n===== LIBRARY STATE =====");
        System.out.println("-- Books --");
        for (Book b : books) System.out.println("  " + b);
        System.out.println("-- Members --");
        for (Member m : members) {
            System.out.println("  " + m);
            for (Loan l : m.getLoans()) System.out.println("    " + l);
        }
        System.out.println("=========================\n");
    }

    // ---------- Private helpers ----------
    private Book findBookByIsbn(String isbn) {
        for (Book b : books) if (b.getIsbn().equals(isbn)) return b;
        return null;
    }

    private Member findMemberById(String id) {
        for (Member m : members) if (m.getMemberId().equals(id)) return m;
        return null;
    }
}
