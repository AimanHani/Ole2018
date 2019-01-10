package controller;

import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ask;

public class AskDAO {

    public static ArrayList<Ask> getAll() {
        //this method gets all the Ask from the database
        //and creates a special object
        //special object will be stored in arraylist
        //arraylist of Ask will be returned to the Faqservlet
        String statement = "select * from askole order by `askId` ASC";
        ArrayList<Ask> askList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Ask ask = new Ask(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                askList.add(ask);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return askList;
    }
    
    public static ArrayList<Ask> getUnreadAsk() {
        //this method gets all the Ask from the database
        //and creates a special object
        //special object will be stored in arraylist
        //arraylist of Ask will be returned to the Faqservlet
        String statement = "select * from askole where `answer` = \"\" order by `askId` ASC";
        ArrayList<Ask> askList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Ask ask = new Ask(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                askList.add(ask);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return askList;
    }
    
    public static ArrayList<Ask> getCategoryAsk(String category) {
        //this method gets all the Ask from the database
        //and creates a special object
        //special object will be stored in arraylist
        //arraylist of Ask will be returned to the Faqservlet
        String statement = "select * from askole where `category` = '" + category + "' order by `askId` ASC";
        ArrayList<Ask> askList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Ask ask = new Ask(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                askList.add(ask);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return askList;
    }
    
    
    public static ArrayList<Ask> searchKeyword(String keyword) {
        //this method gets all the users from the database
        //and creates a user object
        //users object will be stored in arraylist
        //arraylist of users will be returned to the usersservlet
        String statement = "select * from askole where `question` like '%" + keyword + "%' or `answer` like '%" + keyword + "%' order by `faqId` ASC";
        ArrayList<Ask> askList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Ask ask = new Ask(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                askList.add(ask);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return askList;
    }

    public static Boolean addQuesAsk(String question) {
        //ArrayList<Ask> FaqList = FaqDAO.getAllFaq();
        //Ask specialLast = FaqList.get(FaqList.size()-1);
        //System.out.println(specialLast.getFaqId());
        //int id = Integer.parseInt(specialLast.getFaqId().substring(1));
        //id++;
        //String specialID = "s" + id;
        //INSERT INTO Ask (description, status) VALUES ("tefst", "tfest")

        //check if the Ask is in the db
        boolean exist = false;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * from `askole` WHERE `question` LIKE '" + question + "'");) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                exist = true;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!exist) {
            String statement = "insert into askole (question, answer) values('" + question + "','');";
            System.out.println(statement);
            try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
                stmt.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public static Boolean addAnsAsk(int askId, String answer) {
        //ArrayList<Ask> FaqList = FaqDAO.getAllFaq();
        //Ask specialLast = FaqList.get(FaqList.size()-1);
        //System.out.println(specialLast.getFaqId());
        //int id = Integer.parseInt(specialLast.getFaqId().substring(1));
        //id++;
        //String specialID = "s" + id;
        //INSERT INTO Ask (description, status) VALUES ("tefst", "tfest")

        //check if the Ask is in the db
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * from `askole` WHERE `askId` = " + askId + "");) {
            System.out.println("SELECT * from `askole` WHERE `askId` = " + askId + "");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String statement = "update askole set answer = '" + answer + "' where askId =" + askId + "";
                System.out.println(statement);
                try (Connection conn2 = DBConnection.getConnection(); PreparedStatement stmt2 = conn2.prepareStatement(statement);) {
                    stmt2.executeUpdate();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            rs.close();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static Boolean deleteAsk(int askId) {

        String statement = "delete from askole where askId =" + askId + "";
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
}
