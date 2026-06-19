import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a library member who can borrow books.
 * Maintains a list of active loans held by this member.
 */
public class Member {

    private String     memberId;
    private String     name;
    private List<Loan> loans;   // active loans

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name     = name;
        this.loans    = new ArrayList<>();
    }

    // ---------- Getters ----------
    public String          getMemberId() { return memberId; }
    public String          getName()     { return name; }
    public List<Loan>      getLoans()    { return Collections.unmodifiableList(loans); }

    // ---------- Setters ----------
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public void setName(String name)         { this.name = name; }

    // ---------- Loan management ----------
    public void addLoan(Loan loan)      { loans.add(loan); }
    public void removeLoan(Loan loan)   { loans.remove(loan); }

    @Override
    public String toString() {
        return String.format("Member[ID=%s, Name=%s, ActiveLoans=%d]",
                memberId, name, loans.size());
    }
}
