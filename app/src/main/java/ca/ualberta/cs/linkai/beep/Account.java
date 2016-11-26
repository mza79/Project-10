package ca.ualberta.cs.linkai.beep;

import java.util.ArrayList;

import io.searchbox.annotations.JestId;

/**
 * Created by lincolnqi on 2016-11-11.
 *
 * @author LinKai
 * @see SignUpActivity
 * @see WelcomeActivity
 *
 * <p>Here is the class to save all information about account</p>
 * <ul>
 *     <li>AccountId</li>
 *     <li>UserName</li>
 *     <li>PhoneNumber</li>
 *     <li>EmailAddress</li>
 * </ul>
 */

public class Account {

    @JestId
    private String id;
    private String username;
    private String phone;
    private String email;
    private int RequestNum;
    /**
     * Define the user type
     * "1" to be a driver
     * "2" to be a rider
     * "3" to be both a rider or driver
     */
    private int UserType;

    /**
     * Define the status of a request
     * "1" to be sent (by rider)
     * "2" to be accepted (by driver)
     * "3" to be cancelled (by rider)
     * "4" to be completed (by driver and rider)
     */
    public static int status;

    public static ArrayList<Request> myRequests = new ArrayList<Request>();
    public static RequestsList requestsList = new RequestsList();

    /**
     * This method is a constructor which initialize the account
     * @param username
     * @param phone
     * @param email
     * @param AccountType
     */
    public Account(String username, String phone, String email, int AccountType){
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.UserType = AccountType;
    }

    public static ArrayList<Request> getMyRequests() {
        return myRequests;
    }

    public static void setMyRequests(ArrayList<Request> myRequests) {
        Account.myRequests = myRequests;
    }

    public static RequestsList getRequestsList() {
        return requestsList;
    }


    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        Account.status = status;
    }

    public String getUsername(){
        return username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getRequestNum() {
        return RequestNum;
    }

    public void setRequestNum(int num) {
        this.RequestNum = num;
    }

    //getter and setter about JestId
    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

}
