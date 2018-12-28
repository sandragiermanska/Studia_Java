package lab10;

import java.sql.*;
import java.util.ArrayList;

public class DB {

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
                        DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/sgierma1",
                                "sgierma1", "4sZeWY1nFwaq5Am9");
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

    private void inFinally () {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) { }
            rs = null;
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) { }
            stmt = null;
        }
    }

    public ArrayList<String> select(String pesel){
        ArrayList<String> result = new ArrayList<>();
        try {
            boolean isConnected = connect();
            if (isConnected) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM Pracownicy WHERE pesel = " + pesel);

                ResultSetMetaData md = rs.getMetaData();
                int sizeOfRecord = md.getColumnCount();

                while (rs.next()) {
                    for (int i = 1; i <= sizeOfRecord; i++) {
                        String value = rs.getString(i);
                        result.add(value);
                    }
                }
            }
            else result = null;
        }catch (SQLException ex){
        }finally {
            inFinally();
        }
        return result;
    }

    public boolean add(String pesel, double wynagrodzenieBrutto, int czyStudent) {
        boolean result = false;
        try {
            boolean isConnected = connect();
            if (isConnected) {
                stmt = conn.createStatement();

                stmt.executeUpdate(
                        "INSERT INTO Pracownicy values ('" + pesel + "','" + wynagrodzenieBrutto + "','" + czyStudent + "')");
                result = true;
            }

        }catch (SQLException ex){
        }finally {
            inFinally();
        }
        return result;
    }

    public boolean delete(String pesel) {
        boolean result = false;
        try {
            boolean isConnected = connect();
            if (isConnected) {
                stmt = conn.createStatement();

                stmt.executeUpdate(
                        "DELETE FROM Pracownicy WHERE pesel = " + pesel);
                result = true;
            }
        }catch (SQLException ex){
        }finally {
            inFinally();
        }
        return result;
    }

    public double getWynagrodzenieBrutto(String pesel) {
        ArrayList<String> selected = select(pesel);
        double result = Double.parseDouble(selected.get(1));
        return result;
    }

    public double getWynagrodzenieNetto(String pesel){
        double result;
        ArrayList<String> selected = select(pesel);
        double brutto = Double.parseDouble(selected.get(1));
        if (Integer.parseInt(selected.get(2)) == 1) {
            result = brutto;
        }
        else {
            result = 0.8 * brutto;
        }
        return result;
    }
}
