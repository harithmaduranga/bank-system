/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package banksystem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class ABankSystem {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        new WelcomePage().setVisible(true);
        calculateInterest();
        //settleAccountInterestTable(1978000006);
    }

    public static void calculateInterest(){
    
        try{
            
            Connection conn1;
            PreparedStatement select, selectSaving, selectFixed, updateSaving, updateFixed, updateSavingLastDate, updateFixedLastDate;

            DecimalFormat formatter = new DecimalFormat("######.##");

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3307/bank_system","root","");

            select = conn1.prepareStatement("select * from account_interest");
            ResultSet rs = select.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount();
            
            while(rs.next()){
            /*for(int a=1; a<=c; a++)*/{
            
                Date dueDate, today, lastDate;

                int accountNumber = rs.getInt("account_number");

                today = Date.valueOf(LocalDate.now());
                lastDate = rs.getDate("last_date");
                String lastDateText = lastDate+"";
                LocalDate d = LocalDate.parse(lastDateText);
                dueDate = Date.valueOf(d.plusMonths(1));

                if(dueDate.compareTo(today)<=0){
                    
                        if(rs.getString("account_type").equals("saving")){
                    selectSaving = conn1.prepareStatement("select * from saving_accounts");
                    ResultSet rs2 = selectSaving.executeQuery();
                    ResultSetMetaData rsmd2 = rs2.getMetaData();
                    int c2 = rsmd2.getColumnCount();
                    while(rs2.next()){
                        /*for(int b=1; b<=c2; b++)*/{
                            if(rs2.getString("account_number").equals(accountNumber+"")){
                                if(rs2.getString("state").equals("approved")){
                                double amount = rs2.getDouble("money_amount");
                                System.out.println(amount);
                                double rate = rs2.getDouble("annual_interest_rate");
                                System.out.println(rate);
                                double newAmount = (Math.round((amount + amount*rate/1200)*100))/100;
                                System.out.println(newAmount);
                                double addAmount = (Math.round(amount*rate/12))/100;
                                System.out.println(addAmount);
                                updateSaving = conn1.prepareStatement("update saving_accounts set money_amount=?, last_exchange=? where account_number=?");
                                updateSaving.setDouble(1,newAmount);
                                updateSaving.setDouble(2,addAmount);
                                updateSaving.setString(3,accountNumber+"");
                                updateSaving.executeUpdate();
                                updateSavingLastDate = conn1.prepareStatement("update account_interest set last_date=? where account_number=?");
                                updateSavingLastDate.setDate(1,dueDate);
                                updateSavingLastDate.setInt(2,accountNumber);
                                updateSavingLastDate.executeUpdate();
                                }
                            }
                        }
                    }
                    }

                        if(rs.getString("account_type").equals("fixed")){
                    selectFixed = conn1.prepareStatement("select * from fixed_deposits");
                    ResultSet rs3 = selectFixed.executeQuery();
                    ResultSetMetaData rsmd3 = rs3.getMetaData();
                    int c3 = rsmd3.getColumnCount();
                    while(rs3.next()){
                        /*for(int e=1; e<=c3; e++)*/{
                            if(rs3.getString("account_number").equals(accountNumber+"")){
                                if(rs3.getString("state").equals("approved")){
                                double amount = rs3.getDouble("money_amount");
                                System.out.println(amount);
                                double rate = rs3.getDouble("annual_interest_rate");
                                System.out.println(rate);
                                double newAmount = Math.round((amount + amount*rate/1200)*100)/100;
                                System.out.println(newAmount);
                                double addAmount = Math.round(amount*rate/12)/100;
                                System.out.println(addAmount);
                                updateFixed = conn1.prepareStatement("update fixed_deposits set money_amount=?, last_exchange=? where account_number=?");
                                updateFixed.setDouble(1,newAmount);
                                updateFixed.setDouble(2,addAmount);
                                updateFixed.setString(3,accountNumber+"");
                                updateFixed.executeUpdate();
                                updateFixedLastDate = conn1.prepareStatement("update account_interest set last_date=? where account_number=?");
                                updateFixedLastDate.setDate(1,dueDate);
                                updateFixedLastDate.setInt(2,accountNumber);
                                updateFixedLastDate.executeUpdate();
                                }
                            }
                        }
                    }
                    }

                }
            }
            }
        }

        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
            
        }
        catch(Exception f){
            JOptionPane.showMessageDialog(null, f);
            System.out.println(f);
            
        }

    }


    public static void settleAccountInterestTable(int accountNo){
    
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn4 = DriverManager.getConnection("jdbc:mysql://localhost:3307/bank_system","root","");

            PreparedStatement selector = conn4.prepareStatement("select * from account_interest");
            ResultSet rs = selector.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount();
            
            Date dueDate = Date.valueOf(LocalDate.now().plusMonths(-1)) , lastDate;

            while(rs.next()){
            for(int a=1; a<=c; a++){

                int accountNumber = rs.getInt("account_number");

                if(accountNumber == accountNo){
                    lastDate = rs.getDate("last_date");
                    String lastDateText = lastDate+"";
                    LocalDate d = LocalDate.parse(lastDateText);
                    dueDate = Date.valueOf(d.plusMonths(-1));
                }
                
            }
            }

            PreparedStatement updator = conn4.prepareStatement("update account_interest set last_date=? where account_number=?");
            updator.setDate(1,dueDate);
            updator.setInt(2,accountNo);
            updator.executeUpdate();

        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
            
        }
        catch(Exception f){
            JOptionPane.showMessageDialog(null, f);
            System.out.println(f);
            
        }

    }
    
}
