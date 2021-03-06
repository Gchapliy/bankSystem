package com.myProject.bankSystem.bean.userAccount;

import com.myProject.bankSystem.bean.bankAccount.BankAccount;
import com.myProject.bankSystem.bean.bankAccount.BankAccountOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAccount implements Serializable{
    private String userAccountName;
    private String userAccountGender;
    private String userAccountEncryptedPassword;
    private Date accountCreateDate;
    private Role userAccountRole;
    private String userAccountEmail;
    private String userAccountPhone;
    private List<BankAccount> userBankAccounts;
    private List<BankAccountOrder> userBankAccountsOrders;

    public UserAccount() {
        userBankAccounts = new ArrayList<>();
    }

    public String getUserAccountName() {
        return userAccountName;
    }

    public void setUserAccountName(String userAccountName) {
        this.userAccountName = userAccountName;
    }

    public String getUserAccountGender() {
        return userAccountGender;
    }

    public void setUserAccountGender(String userAccountGender) {
        this.userAccountGender = userAccountGender;
    }

    public String getUserAccountEncryptedPassword() {
        return userAccountEncryptedPassword;
    }

    public void setUserAccountEncryptedPassword(String userAccountEncryptedPassword) {
        this.userAccountEncryptedPassword = userAccountEncryptedPassword;
    }

    public Date getAccountCreateDate() {
        return accountCreateDate;
    }

    public void setAccountCreateDate(Date accountCreateDate) {
        this.accountCreateDate = accountCreateDate;
    }

    public Role getUserAccountRole() {
        return userAccountRole;
    }

    public void setUserAccountRole(Role userAccountRole) {
        this.userAccountRole = userAccountRole;
    }

    public String getUserAccountEmail() {
        return userAccountEmail;
    }

    public void setUserAccountEmail(String userAccountEmail) {
        this.userAccountEmail = userAccountEmail;
    }

    public String getUserAccountPhone() {
        return userAccountPhone;
    }

    public void setUserAccountPhone(String userAccountPhone) {
        this.userAccountPhone = userAccountPhone;
    }

    public List<BankAccount> getUserBankAccounts() {
        return userBankAccounts;
    }

    public void setUserBankAccounts(List<BankAccount> userBankAccounts) {
        this.userBankAccounts = userBankAccounts;
    }

    public List<BankAccountOrder> getUserBankAccountsOrders() {
        return userBankAccountsOrders;
    }

    public void setUserBankAccountsOrders(List<BankAccountOrder> userBankAccountsOrders) {
        this.userBankAccountsOrders = userBankAccountsOrders;
    }

    public BankAccount getBankAccountByUuid(String uuid){
        for (BankAccount account : userBankAccounts) {
            if(account.getAccountUuid().equals(uuid))return account;
        }

        return null;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userAccountName='" + userAccountName + '\'' +
                ", userAccountGender='" + userAccountGender + '\'' +
                ", accountCreateDate=" + accountCreateDate +
                ", userAccountRole=" + userAccountRole +
                ", userAccountEmail='" + userAccountEmail + '\'' +
                ", userAccountPhone='" + userAccountPhone + '\'' +
                '}';
    }
}
