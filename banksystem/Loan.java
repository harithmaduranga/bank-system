/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banksystem;

/**
 *
 * @author Acer
 */
public class Loan {
    
    private int accountNumber;
    private double amount;
    private int durationInMonths;
    private double annualInterestRate;
    private double totalReturn;
    private double monthlyReturn;
    private double paidAmount;
    private double remainingAmount;
    private String state;

    public Loan(int accountNumber, double amount, int durationInMonths, double annualInterestRate, double paidAmount, String state){
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.durationInMonths = durationInMonths;
        this.annualInterestRate = annualInterestRate;   
        this.totalReturn = amount + amount*annualInterestRate/100;
        this.monthlyReturn = totalReturn/durationInMonths;
        this.paidAmount = paidAmount;
        this.remainingAmount = totalReturn - paidAmount;
        this.state = state;
    }

    public boolean equals(Loan loan){
        return(this.accountNumber == loan.accountNumber);
    }

    public
    int getAccountNumber() {
        return accountNumber;
    }

    public
    double getAmount() {
        return amount;
    }

    public
    int getDurationInMonths() {
        return durationInMonths;
    }

    public
    double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public
    double getTotalReturn() {
        return totalReturn;
    }

    public
    double getMonthlyReturn() {
        return monthlyReturn;
    }

    public
    double getPaidAmount() {
        return paidAmount;
    }

    public
    double getRemainingAmount() {
        return remainingAmount;
    }

    public
    String getState() {
        return state;
    }

    public
    void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public
    void setAmount(double amount) {
        this.amount = amount;
    }

    public
    void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public
    void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public
    void setTotalReturn(double totalReturn) {
        this.totalReturn = totalReturn;
    }

    public
    void setMonthlyReturn(double monthlyReturn) {
        this.monthlyReturn = monthlyReturn;
    }

    public
    void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public
    void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public
    void setState(String state) {
        this.state = state;
    }

}
