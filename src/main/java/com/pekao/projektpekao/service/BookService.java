package com.pekao.projektpekao.service;

import com.pekao.projektpekao.controller.Book.BookEntityMapper;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;
import com.pekao.projektpekao.domain.book.Book;
import com.pekao.projektpekao.domain.book.BookFactory;
import com.pekao.projektpekao.domain.book.BookParams;
import com.pekao.projektpekao.infrastructure.BookDao;
import com.pekao.projektpekao.infrastructure.ElectronicJournalDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {
    @Resource(name = "BookDaoJpaImpl")
    private final BookDao bookDaoJpa;

    @Resource(name = "ElectronicJournalJpaImpl")
    private final ElectronicJournalDao electronicJournalDao;

    public BookService(@Qualifier("BookDaoJpaImpl") BookDao bookDaoJpa, ElectronicJournalDao electronicJournalDao) {
        this.bookDaoJpa = bookDaoJpa;
        this.electronicJournalDao = electronicJournalDao;
    }

    public List<Book> findAllBooks() {
        return bookDaoJpa.findAllBooks();
    }

    public Book findBookById(Long id) {
        return bookDaoJpa.findById(id).orElseThrow(() -> new IllegalStateException("Cannot find book with id: " + id));
    }

    public Book findBookByTitle(String title) {
        return bookDaoJpa.findByTitle(title).orElseThrow(() -> new IllegalStateException("Cannot find book with title " + title));
    }

    public void removeBookById(Long id) {
        bookDaoJpa.deleteById(id);
    }

    public Book addBook(BookParams bookParams) {
        ElectronicJournal journalToSave = BookFactory.createElectronicJournalEventType(bookParams.getPublisher());
        electronicJournalDao.addElectronicJournal(journalToSave);
        Book bookEntity = BookEntityMapper.toBookEntity(bookParams);
        return bookDaoJpa.addBook(bookEntity);
    }

    public Book updateBook(BookParams bookParams) {
        Book bookEntity = BookEntityMapper.toBookEntity(bookParams);
        return bookDaoJpa.addBook(bookEntity);
    }
}
