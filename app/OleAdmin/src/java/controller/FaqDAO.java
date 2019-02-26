package controller;

import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Faq;

public class FaqDAO {

    public static ArrayList<Faq> getAllFaq() {
        //this method gets all the Faq from the database
        //and creates a special object
        //special object will be stored in arraylist
        //arraylist of Faq will be returned to the Faqservlet
        String statement = "select * from faq order by `faqId` ASC";
        ArrayList<Faq> faqList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Faq faq = new Faq(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                faqList.add(faq);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return faqList;
    }
    
    public static ArrayList<Faq> getUnreadFaq() {
        //this method gets all the Faq from the database
        //and creates a special object
        //special object will be stored in arraylist
        //arraylist of Faq will be returned to the Faqservlet
        String statement = "select * from faq where `answer` = \"\" order by `faqId` ASC";
        ArrayList<Faq> faqList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Faq faq = new Faq(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                faqList.add(faq);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return faqList;
    }
    
    public static ArrayList<Faq> getCategoryFaq(String category) {
        //this method gets all the Ask from the database
        //and creates a special object
        //special object will be stored in arraylist
        //arraylist of Ask will be returned to the Faqservlet
        String statement = "select * from faq where `category` = '" + category + "' order by `faqId` ASC";
        ArrayList<Faq> faqList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Faq faq = new Faq(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                faqList.add(faq);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AskDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return faqList;
    }
    
    public static ArrayList<Faq> searchKeyword(String keyword) {
        //this method gets all the users from the database
        //and creates a user object
        //users object will be stored in arraylist
        //arraylist of users will be returned to the usersservlet
        String statement = "select * from faq where `question` like '%" + keyword + "%' or `answer` like '%" + keyword + "%' order by `faqId` ASC";
        ArrayList<Faq> faqList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Faq faq = new Faq(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                faqList.add(faq);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return faqList;
    }

    public static Boolean addQuesFaq(String question) {
        //ArrayList<Faq> FaqList = FaqDAO.getAllFaq();
        //Faq specialLast = FaqList.get(FaqList.size()-1);
        //System.out.println(specialLast.getFaqId());
        //int id = Integer.parseInt(specialLast.getFaqId().substring(1));
        //id++;
        //String specialID = "s" + id;
        //INSERT INTO Faq (description, status) VALUES ("tefst", "tfest")

        //check if the Faq is in the db
        boolean exist = false;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * from `faq` WHERE `question` LIKE '" + question + "'");) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                exist = true;
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!exist) {
            String statement = "insert into faq (question, answer) values('" + question + "','');";
            System.out.println(statement);
            try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
                stmt.executeUpdate();
                conn.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public static Boolean addAnsFaq(int faqId, String answer) {
        //ArrayList<Faq> FaqList = FaqDAO.getAllFaq();
        //Faq specialLast = FaqList.get(FaqList.size()-1);
        //System.out.println(specialLast.getFaqId());
        //int id = Integer.parseInt(specialLast.getFaqId().substring(1));
        //id++;
        //String specialID = "s" + id;
        //INSERT INTO Faq (description, status) VALUES ("tefst", "tfest")

        //check if the Faq is in the db
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * from `faq` WHERE `faqId` = " + faqId + "");) {
            System.out.println("SELECT * from `faq` WHERE `faqId` = " + faqId + "");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String statement = "update faq set answer = '" + answer + "' where faqId =" + faqId + "";
                System.out.println(statement);
                try (Connection conn2 = DBConnection.getConnection(); PreparedStatement stmt2 = conn2.prepareStatement(statement);) {
                    stmt2.executeUpdate();
                    conn2.close();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            rs.close();
            conn.close();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static Boolean update(int faqId, String question, String answer) {
        //ArrayList<Faq> FaqList = FaqDAO.getAllFaq();
        //Faq specialLast = FaqList.get(FaqList.size()-1);
        //System.out.println(specialLast.getFaqId());
        //int id = Integer.parseInt(specialLast.getFaqId().substring(1));
        //id++;
        //String specialID = "s" + id;
        //INSERT INTO Faq (description, status) VALUES ("tefst", "tfest")

        //check if the Faq is in the db
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * from `faq` WHERE `faqId` = " + faqId + "");) {
            System.out.println("SELECT * from `faq` WHERE `faqId` = " + faqId + "");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String statement = "update faq set question = '" + question + "', answer = '" + answer + "'  where faqId =" + faqId + "";

                try (Connection conn2 = DBConnection.getConnection(); PreparedStatement stmt1 = conn2.prepareStatement(statement); ) {
                    stmt1.executeUpdate();
                    conn2.close();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            rs.close();
            conn.close();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public static Boolean deleteFaq(int faqId) {

        String statement = "delete from faq where faqId =" + faqId + "";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            stmt.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static Boolean add(String question, String answer, String category) {
        //ArrayList<Faq> FaqList = FaqDAO.getAllFaq();
        //Faq specialLast = FaqList.get(FaqList.size()-1);
        //System.out.println(specialLast.getFaqId());
        //int id = Integer.parseInt(specialLast.getFaqId().substring(1));
        //id++;
        //String specialID = "s" + id;
        //INSERT INTO Faq (description, status) VALUES ("tefst", "tfest")

        //check if the Faq is in the db
        boolean exist = false;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * from `faq` WHERE `question` LIKE '" + question + "'");) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                exist = true;
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!exist) {
            String statement = "insert into faq (question, answer, category) values('" + question + "', '" + answer + "', '" + category + "');";
            System.out.println(statement);
            try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
                stmt.executeUpdate();
                conn.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FaqDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
