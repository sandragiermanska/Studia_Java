/**
 * Created by student on 2018-11-27.
 */

import javafx.application.Platform;

import java.sql.*;

public class DB{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public boolean connect() {
        int maxNumberOfTry = 3;
        int currentTry = 0;
        boolean isConnected = false;
        while (!isConnected && currentTry < maxNumberOfTry) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                conn =
                        DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/sgierman",
                                "sgierman", "tHtCvCVgv9eAkm9q");
                isConnected = true;

            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ++currentTry;
            } catch (Exception e) {
                e.printStackTrace();
                ++currentTry;
            }
        }
        return isConnected;
    }

    private String select(String question){
        String result = new String();
        try {
            boolean isConnected = connect();
            if (isConnected) {
                stmt = conn.createStatement();

                rs = stmt.executeQuery(question);


                ResultSetMetaData md = rs.getMetaData();
                int sizeOfRecord = md.getColumnCount();

                while (rs.next()) {
                    for (int i = 1; i <= sizeOfRecord; i++) {
                        String value = rs.getString(i);
                        result += value;
                        result += " | ";
//                    System.out.print(value + " | ");
                    }
                    result += "\n";
//                System.out.println();

                }
            }
            else result = null;
        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
        return result;
    }

    public String selectAll(){
        return select("SELECT * FROM books");
    }

    public String selectByAuthor(String author_surname) {
        return select("SELECT * FROM books WHERE author LIKE '%" + author_surname + "'");
    }

    public String selectByISBN(String isbn) {
        return select("SELECT * FROM books WHERE isbn = " + isbn);
    }

    public boolean addBook(String isbn, String title, String author, String year) {
        boolean result = false;

        try {
            boolean isConnected = connect();
            if (isConnected) {
                stmt = conn.createStatement();

                stmt.executeUpdate(
                        "INSERT INTO books values ('" + isbn + "','" + title + "','" + author + "','" + year + "')");
                result = true;
            }

        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
        return result;
    }
}
