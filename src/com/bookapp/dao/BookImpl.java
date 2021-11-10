package com.bookapp.dao;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import java.util.List;
import com.bookapp.bean.Book;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookImpl implements BookInter {


    @Override
    public void addBook(Book book) {
        // TODO Auto-generated method stub

        String sql = "insert into book values(?,?,?,?,?)";
        Connection connection = ModelDAO.openConnection();

        try {

            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, book.getTitle());
            st.setString(2, book.getAuthor());
            st.setString(3, book.getCategory());
            st.setInt(4, book.getBookid());
            st.setInt(5, book.getPrice());
            st.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean deleteBook(int bookid) throws BookNotFoundException {
        // TODO Auto-generated method stub

        String deleteSQL = "DELETE from book WHERE bookid=?";
        Connection connection = ModelDAO.openConnection();

        try {

            PreparedStatement st = connection.prepareStatement(deleteSQL);
            System.out.println("Enter id to delete : ");

            st.setInt(1, bookid);
            st.execute();


        } catch (Exception e) {
            e.printStackTrace();

        }


        return false;
    }

    @Override
    public Book getBookById(int bookid) throws BookNotFoundException {
        // TODO Auto-generated method stub

        String getBook = "SELECT * from book WHERE bookid=?";
        Connection connection = ModelDAO.openConnection();

        try {

            PreparedStatement st = connection.prepareStatement(getBook);
            st.setInt(1, bookid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String title = rs.getString(1);
                String author = rs.getString(2);
                String category = rs.getString(3);
                int id = rs.getInt(4);
                int price = rs.getInt(5);
                System.out.println(title + "\t" + author + "\t" + category + "\t" + id + "\t" + price);
                st.execute();

            }

        } catch (Exception e) {
            e.printStackTrace();

        }


        return null;
    }

    @Override
    public boolean updateBook(int bookid, int price) {
        // TODO Auto-generated method stub

        String updateSQL = "UPDATE from book set price=? WHERE bookid=? ";
        Connection connection = ModelDAO.openConnection();

        try {

            PreparedStatement st = connection.prepareStatement(updateSQL);
            System.out.println("Enter price: ");
            st.setInt(1, price);
            st.setInt(2, bookid);
            st.execute();


        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public List<Book> getAllBooks() {
        // TODO Auto-generated method stub

        List<Book> bookList = new ArrayList<>();

        String getBook = "SELECT * from book ";
        Connection connection = ModelDAO.openConnection();

        try {

            PreparedStatement st = connection.prepareStatement(getBook);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String category = rs.getString("category");
                int id = rs.getInt("bookid");
                int price = rs.getInt("price");
                st.execute();
                Book b = new Book(title, author, category, id, price);

                bookList.add(b);
            }
            return bookList;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<Book> getBookbycategory(String category) throws CategoryNotFoundException {
        // TODO Auto-generated method stub

        String getCategory = "SELECT * from book WHERE category=?";
        Connection connection = ModelDAO.openConnection();

        try {

            PreparedStatement st = connection.prepareStatement(getCategory);
            st.setString(1, category);


            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String title = rs.getString(1);
                String author = rs.getString(2);
                String category1 = rs.getString(3);
                int id = rs.getInt(4);
                int price = rs.getInt(5);
                System.out.println(title + "\t" + author + "\t" + category1 + "\t" + id + "\t" + price);
                st.execute();

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException {
        // TODO Auto-generated method stub

        String getAuthor = "SELECT * from book WHERE author=?";
        Connection connection = ModelDAO.openConnection();

        try {

            PreparedStatement st = connection.prepareStatement(getAuthor);
            st.setString(1, author);


            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String title = rs.getString(1);
                String author1 = rs.getString(2);
                String category = rs.getString(3);
                int id = rs.getInt(4);
                int price = rs.getInt(5);
                System.out.println(title + "\t" + author1 + "\t" + category + "\t" + id + "\t" + price);
                st.execute();

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }

}
