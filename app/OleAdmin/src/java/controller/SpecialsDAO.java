package controller;

import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Specials;

public class SpecialsDAO {

    public static ArrayList<Specials> getAllSpecials() {
        //this method gets all the specials from the database
        //and creates a special object
        //special object will be stored in arraylist
        //arraylist of specials will be returned to the specialsservlet
        String statement = "select * from specials";
        ArrayList<Specials> specialsList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Specials special = new Specials(rs.getInt(1), rs.getString(2), rs.getString(3));
                specialsList.add(special);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return specialsList;
    }

    public static Boolean addSpecials(String description) {
        //ArrayList<Specials> specialsList = SpecialsDAO.getAllSpecials();
        //Specials specialLast = specialsList.get(specialsList.size()-1);
        //System.out.println(specialLast.getSpecialsId());
        //int id = Integer.parseInt(specialLast.getSpecialsId().substring(1));
        //id++;
        //String specialID = "s" + id;
        //INSERT INTO Specials (description, status) VALUES ("tefst", "tfest")
        String statement = "insert into specials (description, status) values('" + description + "','N');";
        System.out.println(statement);
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Boolean deleteSpecials(int specialsId) {

        String statement = "delete from specials where specialsId =" + specialsId + "";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Boolean updateSpecials(String[] specialsList) {

        if (specialsList != null) {
            String updates = "";
            for (int i = 1; i < specialsList.length; i++) {
                updates = updates + "" + specialsList[i - 1] + ",";
            }
            updates = updates + "" + specialsList[specialsList.length - 1] + "";

            System.out.println(updates);

            for (String specials : specialsList) {
                System.out.println(specials);
            }

            String statement1 = "update specials set status ='N'";
            String statement2 = "update specials set status ='Y' where specialsId IN (" + updates + ");";
            try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt1 = conn.prepareStatement(statement1); PreparedStatement stmt2 = conn.prepareStatement(statement2);) {
                stmt1.executeUpdate();
                stmt2.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String statement1 = "update specials set status ='N'";
            try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt1 = conn.prepareStatement(statement1);) {
                stmt1.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
