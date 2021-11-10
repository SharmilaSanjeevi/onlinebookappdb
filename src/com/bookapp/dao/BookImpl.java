
/*Author @sharmila
 * version 0.1
 * 
 */
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
			return true;

		} catch (Exception e) {
			e.printStackTrace();

		}

		return false;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {

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
				Book b = new Book(title, author, category, id, price);

				return b;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	@Override
	public boolean updateBook(int bookid, int price) {
		// TODO Auto-generated method stub

		String updateSQL = "UPDATE book SET price=? WHERE bookid=?";
		Connection connection = ModelDAO.openConnection();

		try {

			PreparedStatement st = connection.prepareStatement(updateSQL);
			st.setInt(1, price);
			st.setInt(2, bookid);
			st.execute();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Book> getAllBooks() {
		Connection conn = ModelDAO.openConnection();

		String SQLQuery = "SELECT * FROM book;";

		try {
			PreparedStatement statement = conn.prepareStatement(SQLQuery);

			ResultSet result = statement.executeQuery();

			List<Book> bookList = new ArrayList<>();

			while (result.next()) {
				String title = result.getString("title");
				String author = result.getString("author");
				String category = result.getString("category");
				int bookid = result.getInt("bookid");
				int price = result.getInt("price");

				Book firstBook = new Book(title, author, category, bookid, price);

				bookList.add(firstBook);
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
			List<Book> bookList = new ArrayList<>();
			PreparedStatement st = connection.prepareStatement(getCategory);
			st.setString(1, category);

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String author1 = rs.getString("author");
				String category1 = rs.getString("category");
				int id = rs.getInt("bookid");
				int price = rs.getInt("price");

				Book b = new Book(title, author1, category1, id, price);

				bookList.add(b);
			}

			return bookList;
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

			List<Book> bookList = new ArrayList<>();

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String author1 = rs.getString("author");
				String category = rs.getString("category");
				int id = rs.getInt("bookid");
				int price = rs.getInt("price");

				Book b = new Book(title, author1, category, id, price);

				bookList.add(b);
			}

			return bookList;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

}
