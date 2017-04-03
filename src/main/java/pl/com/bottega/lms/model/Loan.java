package pl.com.bottega.lms.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LOANS")
public class Loan {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "bookId"))
    private BookId bookId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "clientId"))
    private ClientId clientId;
    private LocalDateTime loanAt;
    private LocalDateTime returnAt;

    Loan() {
    }

    public Loan(BookId bookId, ClientId clientId) {
        this.bookId = bookId;
        this.clientId = clientId;
        this.loanAt = LocalDateTime.now();
    }

    public void returnBook(BookId bookId) {
        this.bookId = bookId;
        this.returnAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public BookId getBookId() {
        return bookId;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public LocalDateTime getLoanAt() {
        return loanAt;
    }

    public LocalDateTime getReturnAt() {
        return returnAt;
    }

}
