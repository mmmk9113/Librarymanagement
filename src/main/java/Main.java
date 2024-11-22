import repository.BookCategoryRepositoryImpl;
import repository.BookRepositoryImpl;
import service.BookCategoryService;
import service.BookService;
import util.LibraryMenu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        BookCategoryRepositoryImpl bookCategoryRepository = new BookCategoryRepositoryImpl(em);
        BookRepositoryImpl bookRepository = new BookRepositoryImpl(em);

        BookCategoryService bookCategoryService = new BookCategoryService(bookCategoryRepository);
        BookService bookService = new BookService(bookRepository, bookCategoryRepository);

        LibraryMenu menu = new LibraryMenu(bookCategoryService, bookService);

        menu.run();

        em.close();
        emf.close();
    }
}
