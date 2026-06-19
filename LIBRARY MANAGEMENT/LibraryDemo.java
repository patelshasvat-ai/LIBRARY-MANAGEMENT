/**
 * LibraryDemo -- demonstrates the full library system.
 * Creates 2 members, 3 books, performs lend/return operations
 * (including one correctly rejected attempt) and prints library state before
 * and after.
 */
public class LibraryDemo {

    public static void main(String[] args) {

        Library library = new Library();

        // --- Register members ---
        Member ryaka = new Member("VU1", "Ryaka Innocent");
        Member abaho = new Member("VU2", "Abaho Charles");
        library.registerMember(ryaka);
        library.registerMember(abaho);

        // --- Add books (two constructors demonstrated) ---
        Book b1 = new Book("978-0-13-110362-7", "The C Programming Language", "K&R");
        Book b2 = new Book("978-0-13-468599-1", "Clean Code"); // constructor 1
        Book b3 = new Book("978-0-13-235088-4", "Effective Java", "Joshua Bloch");
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        // --- State BEFORE any loans ---
        System.out.println("\n>>> STATE BEFORE LOANS");
        library.printState();

        // --- Lend operations ---
        System.out.println(">>> LENDING OPERATIONS");
        library.lendBook("978-0-13-110362-7", "VU1"); // Ryaka borrows C book
        library.lendBook("978-0-13-235088-4", "VU2"); // Abaho borrows Effective Java
        library.lendBook("978-0-13-110362-7", "VU2"); // REJECTED: C book already on loan

        // --- State AFTER first round ---
        System.out.println("\n>>> STATE AFTER LENDING");
        library.printState();

        // --- Return and re-lend ---
        System.out.println(">>> RETURN + RE-LEND");
        library.returnBook("978-0-13-110362-7", "VU1"); // Ryaka returns C book
        library.lendBook("978-0-13-110362-7", "VU2"); // Abaho now borrows it (success)

        // --- Search by title ---
        System.out.println("\n>>> SEARCH: 'Java'");
        for (Book b : library.searchByTitle("Java")) {
            System.out.println("  Found: " + b);
        }

        // --- Final state ---
        System.out.println("\n>>> FINAL STATE");
        library.printState();
    }
}
