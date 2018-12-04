package lab7;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Kadra {

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













    LinkedList<Pracownik> kadra = new LinkedList<>();
///////////////////////////
    public void add(Pracownik pracownik) {
        kadra.add(pracownik);
    }


/////////////////////////////
    public Pracownik find(String pesel) {
        int size = kadra.size();
        Pracownik result = null;
        for (int i = 0; i < size; i++) {
            if(pesel.equals(kadra.get(i).pesel)) {
                result = kadra.get(i);
            }
        }
        return result;
    }
////////////////////////////////
    public void delete(Pracownik pracownik) {
        kadra.remove(pracownik);
    }

//    public double getWynagrodzenieBrutto(String pesel) {
//        Pracownik pracownik = find(pesel);
//        if (pracownik != null) {
//            return pracownik.wynagrodzenieBrutto;
//        }
//        return -1;
//    }

//    public double getWynagrodzenieNetto(String pesel) {
//        Pracownik pracownik = find(pesel);
//        if (pracownik != null) {
//            return pracownik.wynagrodzenieNetto();
//        }
//        return -1;
//    }

//    public void sort() {
//        Collections.sort(kadra, new PracownikComparator());
//    }

}

