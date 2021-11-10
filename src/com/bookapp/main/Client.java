package com.bookapp.main;

//import java.util.Scanner;

import com.bookapp.dao.BookImpl;
import com.bookapp.dao.BookInter;
import com.bookapp.dao.ModelDAO;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.bookapp.bean.Book;

public class Client {

    public static void main(String[] args) {
        // TODO Auto-generated method stub


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice :\n1.To show All Books \n2.Get Book by Author \n3.Get Book by Category \n4.Update Book \n5.Delete Book");
        int choice = scanner.nextInt();

        BookInter b = new BookImpl();

//        Book b1 = new Book("Data Structure", "Sharma", "latest", 001, 200);
//        b.addBook(b1);
//
//        Book b2 = new Book("oops", "Agarval", "Current", 002, 150);
//        b.addBook(b2);
//
//        Book b3 = new Book("Developers logic", "RohitSharma", "latestEdition", 003, 250);
//        b.addBook(b3);
//
//        Book b4 = new Book("HTML&CSS", "Sharma", "FirstEdition", 004, 50);
//        b.addBook(b4);
//
//        Book b5 = new Book("Angular", "Rohit", "SecondEdition", 005, 200);
//        b.addBook(b5);


        switch (choice) {

            case 1:
                List<Book> books = b.getAllBooks();

                for(Book book:books) {
                    System.out.println(book.toString());
                }
                break;

            case 2:

                System.out.println("Enter the author name:");
                String authorName = scanner.next();

                try {
                    System.out.println(b.getBookbyAuthor(authorName));
                } catch (AuthorNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 3:

                System.out.println("Enter the category:");
                String category = scanner.next();

                try {
                    System.out.println(b.getBookbycategory(category));
                } catch (CategoryNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 4:
                System.out.println("Update the Book Price : ");
                int bookId = scanner.nextInt();
                int price = scanner.nextInt();
                break;

            case 5:
                System.out.println("Delete the BookId : ");
                int bookid = scanner.nextInt();
                break;

            default:
                System.exit(0);
        }
        scanner.close();
    }
}

