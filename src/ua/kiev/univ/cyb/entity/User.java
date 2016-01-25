package ua.kiev.univ.cyb.entity;

import ua.kiev.univ.cyb.dao.Identified;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class to represent user in library
 */
public class User implements Identified<Integer> {
    /**
     * Identifier to distinguish, primary key in database.
     */
    private Integer id;

    /**
     * First name of user.
     */
    private String firstName;

    /**
     * Last name of user.
     */
    private String secondName;

    /**
     * Email of user.
     */
    private String email;

    /**
     * Password of user.
     */
    private String pass;

    /**
     * Has user the administrator rights or not. False by default.
     */
    private boolean admin = false;

    /**
     * List of orders this user has made.
     */
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public boolean isAdmin() {
        return admin;
    }

    protected void setAdmin(boolean admin) {
        this.admin = admin;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return pass;
    }

    public void setPassword(String password) {
        this.pass = password;
    }

    public Integer getCountNotReturned() {
        int count = 0;
        for (Order order : orders) {
            if (!order.isReturned()) {
                count++;
            }
        }
        return count;
    }

    public Integer getCountReturned() {
        return orders.size() - getCountNotReturned();
    }
}
