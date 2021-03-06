package pl.com.bottega.lms.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.lms.application.LoanFlowProcess;
import pl.com.bottega.lms.model.Book;
import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.BookRepository;
import pl.com.bottega.lms.model.ClientId;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private LoanFlowProcess loanFlowProcess;
    private BookRepository bookRepository;

    public LoanController(LoanFlowProcess loanFlowProcess) {
        this.loanFlowProcess = loanFlowProcess;
    }

    @PostMapping("/loan/{clientIdIn}/{bookIdIn}")
    public void loan(@PathVariable String clientIdIn, @PathVariable String bookIdIn) {
        ClientId clientId = new ClientId(Long.parseLong(clientIdIn));
        BookId bookId = new BookId(bookIdIn);
        Book book = bookRepository.get(bookId);
        loanFlowProcess.loanBook(book, clientId);
    }

    @PostMapping("/return/{clientIdIn}/{bookIdIn}")
    public void returnBook(@PathVariable String clientIdIn, @PathVariable String bookIdIn) {
        BookId bookId = new BookId(bookIdIn);
        ClientId clientId = new ClientId(Long.parseLong(clientIdIn));
        Book book = bookRepository.get(bookId);
        loanFlowProcess.returnBook(book, clientId);
    }

}
