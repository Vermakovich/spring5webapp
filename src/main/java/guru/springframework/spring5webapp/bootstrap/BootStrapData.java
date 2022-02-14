package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "12313442");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);


        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2EE Development without EJB", "21435345");
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);


        Publisher publisher = new Publisher("Some publisher", "Warsaw, ...");
        noEjb.setPublisher(publisher);
        ddd.setPublisher(publisher);
        publisherRepository.save(publisher);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        authorRepository.save(rod);
        bookRepository.save(noEjb);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());

        publisher.getBooks().add(ddd);
        publisher.getBooks().add(noEjb);
        publisherRepository.save(publisher);

        System.out.println("Number of publishers: " + publisherRepository.count());
    }
}
