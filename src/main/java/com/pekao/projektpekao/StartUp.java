package com.pekao.projektpekao;

import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.domain.book.Book;
import com.pekao.projektpekao.domain.Comment.Comment;
import com.pekao.projektpekao.domain.User.User;
import com.pekao.projektpekao.repository.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Profile("dev")
public class StartUp implements ApplicationListener<ContextRefreshedEvent> {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ElectronicJournalRepository electronicJournalRepository;

    public StartUp(
            BookRepository bookRepository, AuthorRepository authorRepository, CommentRepository commentRepository,
            UserRepository userRepository, ElectronicJournalRepository electronicJournalRepository
    ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.electronicJournalRepository = electronicJournalRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        boolean authorsAreEmpty = authorRepository.findAll().isEmpty();
        boolean commentsAreEmpty = commentRepository.findAll().isEmpty();
        boolean usersAreEmpty = userRepository.findAll().isEmpty();
        boolean booksAreEmpty = bookRepository.findAll().isEmpty();
        if (authorsAreEmpty || commentsAreEmpty || usersAreEmpty || booksAreEmpty) {
            final Author author1 = Author.builder()
                    .withFirstName("Stephen")
                    .withLastName("King")
                    .withAuthorPhotoUrl("https://t.ly/js0-")
                    .buildNewEntity();
            final Author author2 = Author.builder()
                    .withAuthorPhotoUrl("https://t.ly/DukBw")
                    .withLastName("Walls")
                    .withFirstName("Craig")
                    .buildNewEntity();
            final Author author3 = Author.builder()
                    .withAuthorPhotoUrl("https://t.ly/OsS1")
                    .withLastName("Samuel")
                    .withFirstName("Beckett")
                    .buildNewEntity();
            final Author author4 = Author.builder()
                    .withAuthorPhotoUrl("https://t.ly/gTXmv")
                    .withLastName("Michael")
                    .withFirstName("Swaine")
                    .buildNewEntity();
            final Author author5 = Author.builder()
                    .withAuthorPhotoUrl("https://t.ly/SxGs")
                    .withLastName("Ewa")
                    .withFirstName("Guzik-Makaruk")
                    .buildNewEntity();
            authorRepository.saveAll(List.of(author1, author2, author3, author4,author5));
            final Comment comment1 = Comment.builder()
                    .content("Mockowy komentarz 1")
                    .buildNewEntity();
            final Comment comment2 = Comment.builder()
                    .content("Mockowy komentarz 2")
                    .buildNewEntity();
            final Comment comment3 = Comment.builder()
                    .content("Mockowy komentarz 3")
                    .buildNewEntity();

            final User user1 = User.builder()
                    .firstName("Tomek")
                    .lastName("Czornak")
                    .email("tomek@gmail.com")
                    .commentList(List.of(comment1, comment2))
                    .createdAt(LocalDate.now())
                    .buildNewEntity();

            final User user2 = User.builder()
                    .firstName("Marek")
                    .lastName("Nowakowski")
                    .email("mareknowakowski@gmail.com")
                    .commentList(List.of(comment3))
                    .createdAt(LocalDate.now())
                    .buildNewEntity();

            Book bookFromBuilder1 = Book.builder()
                    .title("Spring w Akcji")
                    .author(author2)
                    .commentList(List.of(comment2))
                    .publisher(Book.Publisher.ZNAK)
                    .bookPhoto("https://shorturl.at/uAGU8")
                    .buildNewEntity();
            Book bookFromBuilder2 = Book.builder()
                    .title("Darkness")
                    .author(author1)
                    .commentList(List.of(comment1))
                    .publisher(Book.Publisher.PWN)
                    .bookPhoto("https://t.ly/Gf0n")
                    .buildNewEntity();
            Book bookFromBuilder3 = Book.builder()
                    .title("Programowanie funkcyjne")
                    .author(author4)
                    .commentList(List.of(comment3))
                    .publisher(Book.Publisher.AGORA)
                    .bookPhoto("https://t.ly/5L6i")
                    .buildNewEntity();
            Book bookFromBuilder4 = Book.builder()
                    .title("Waiting For Godot")
                    .author(author3)
                    .commentList(List.of(comment3))
                    .publisher(Book.Publisher.AGORA)
                    .bookPhoto("https://upload.wikimedia.org/wikipedia/commons/2/2b/En_attendant_Godot%2C_Festival_d%27Avignon%2C_1978_f22.jpg")
                    .buildNewEntity();
            Book bookFromBuilder5 = Book.builder()
                    .title("Przestępczość Farmeceutyczna w XXI Wieku")
                    .author(author5)
                    .commentList(List.of(comment3))
                    .publisher(Book.Publisher.AGORA)
                    .bookPhoto("https://t.ly/uS4d2")
                    .buildNewEntity();
            Book bookFromBuilder6 = Book.builder()
                    .title("Tortilla Flat")
                    .author(author3)
                    .commentList(List.of(comment3))
                    .publisher(Book.Publisher.AGORA)
                    .bookPhoto("https://upload.wikimedia.org/wikipedia/commons/d/d6/Tortilla_Flat_%281935_1st_ed_dust_jacket%29.jpg")
                    .buildNewEntity();
            Book bookFromBuilder7 = Book.builder()
                    .title("Tortilla Flat")
                    .author(author3)
                    .commentList(List.of(comment3))
                    .publisher(Book.Publisher.AGORA)
                    .bookPhoto("https://upload.wikimedia.org/wikipedia/commons/d/d6/Tortilla_Flat_%281935_1st_ed_dust_jacket%29.jpg")
                    .buildNewEntity();
            Book bookFromBuilder8 = Book.builder()
                    .title("Tortilla Flat")
                    .author(author3)
                    .commentList(List.of(comment3))
                    .publisher(Book.Publisher.AGORA)
                    .bookPhoto("https://upload.wikimedia.org/wikipedia/commons/d/d6/Tortilla_Flat_%281935_1st_ed_dust_jacket%29.jpg")
                    .buildNewEntity();

            bookRepository.saveAll(List.of(bookFromBuilder1, bookFromBuilder2, bookFromBuilder3, bookFromBuilder4, bookFromBuilder5, bookFromBuilder6,bookFromBuilder7, bookFromBuilder8));
            userRepository.saveAll(List.of(user1, user2));

        }
    }
}
