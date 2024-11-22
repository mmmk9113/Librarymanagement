package util;

import model.Book;
import model.BookCategory;
import service.BookCategoryService;
import service.BookService;

import java.util.Optional;
import java.util.Scanner;

public class LibraryMenu {


    private final BookCategoryService bookCategoryService;
    private final BookService bookService;

    public LibraryMenu(BookCategoryService bookCategoryService, BookService bookService) {
        this.bookCategoryService = bookCategoryService;
        this.bookService = bookService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add Book Category");
            System.out.println("2. Delete Book Category");
            System.out.println("3. Update Book Category");
            System.out.println("4. View All Book Categories");
            System.out.println("5. View Book Categories with at least one book");
            System.out.println("6. Add Book");
            System.out.println("7. Delete Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addBookCategory(scanner);
                    break;
                case 2:
                    deleteBookCategory(scanner);
                    break;
                case 3:
                    updateBookCategory(scanner);
                    break;
                case 4:
                    viewAllBookCategories();
                    break;
                case 5:
                    viewCategoriesWithBooks(scanner);
                    break;
                case 6:
                    addBook(scanner);
                    break;
                case 7:
                    deleteBook(scanner);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addBookCategory(Scanner scanner) {
        System.out.print("Enter category name: ");
        String name = scanner.nextLine();
        BookCategory category = new BookCategory();
        category.setName(name);
        bookCategoryService.addCategory(category);
        System.out.println("Book category added successfully.");
    }

    private void deleteBookCategory(Scanner scanner) {
        System.out.print("Enter category ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        bookCategoryService.deleteCategory(id);
        System.out.println("Book category deleted successfully.");
    }

    private void updateBookCategory(Scanner scanner) {
        System.out.print("Enter category ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter new category name: ");
        String name = scanner.nextLine();
        BookCategory category = new BookCategory();
        category.setId(id);
        category.setName(name);
        bookCategoryService.updateCategory(category);
        System.out.println("Book category updated successfully.");
    }

    private void viewAllBookCategories() {
        Iterable<BookCategory> categories = bookCategoryService.getAllCategories();
        System.out.println("All Book Categories:");
        for (BookCategory category : categories) {
            System.out.println(category.getId() + ". " + category.getName());
        }
    }

    private void viewCategoriesWithBooks(Scanner scanner) {
        System.out.print("Enter your category ID: ");
        Long categoryId = scanner.nextLong();
        scanner.nextLine();
        Optional<BookCategory> category = bookCategoryService.findById(categoryId);
        if (category.isPresent()) {
            Iterable<Book> books = bookService.getBooksByCategory(categoryId);
            System.out.println("Books in category '" + category.get().getName() + "':");
            for (Book book : books) {
                System.out.println(book.getId() + ". " + book.getTitle());
            }
        } else {
            System.out.println("Invalid category ID.");
        }
    }

    private void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter category ID: ");
        Long categoryId = scanner.nextLong();
        scanner.nextLine();
        Optional<BookCategory> category = bookCategoryService.findById(categoryId);
        if (category.isPresent()) {
            Book book = new Book();
            book.setTitle(title);
            book.setCategory(category.get());
            bookService.addBook(book);
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Invalid category ID. Book not added.");
        }

    }

    private void deleteBook(Scanner scanner) {
        System.out.print("Enter book ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        bookService.deleteBook(id);
        System.out.println("Book deleted successfully.");
    }
}


