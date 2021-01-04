package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Address;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AddressRepository;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AddressRepository addressRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         AddressRepository addressRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.addressRepository = addressRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher pub1 = new Publisher("Editora Um", "Rua Um", "Cidade Um", "Estado Um", "CEP Um");
        publisherRepository.save(pub1);
        System.out.println("Publisher cout: " + publisherRepository.count());

        Author jose = new Author("Jose", "da Silva");
        Book livro1 = new Book("Livro Um", "1111-1111");
        jose.getBooks().add(livro1);
        livro1.getAuthors().add(jose);

        //Address end1 = new Address("Rua Um", "Cidade Um", "Estado Um", "CEP Um");

        livro1.setPublisher(pub1);
        pub1.getBooks().add(livro1);

        authorRepository.save(jose);
        bookRepository.save(livro1);
        publisherRepository.save(pub1);
        //addressRepository.save(end1);

        Author pedro = new Author("Pedro", "Pedrosa");
        Book livro2 = new Book("Livro Dois", "2222-2222");
        pedro.getBooks().add(livro2);
        livro2.getAuthors().add(pedro);
        //Address end2 = new Address("Rua Dois", "Cidade Dois", "Estado Dois", "CEP Dois");
        //Publisher pub2 = new Publisher("Editora 2", "Rua Dois", "Cidade Dois", "Estado Dois", "CEP Dois");
        livro2.setPublisher(pub1);
        pub1.getBooks().add(livro2);

        authorRepository.save(pedro);
        bookRepository.save(livro2);
        //addressRepository.save(end2);
        publisherRepository.save(pub1);

        System.out.println("Started in bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Pub1 Number of books: " + pub1.getBooks().size());
        System.out.println("Publishers: " + publisherRepository.findAll());

    }
}
