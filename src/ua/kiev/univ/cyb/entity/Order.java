package ua.kiev.univ.cyb.entity;


import ua.kiev.univ.cyb.dao.Identified;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entity class to represent order in library.
 */
public class Order implements Identified<Integer> {

    /**
     * Identifier to distinguish, primary key in database.
     */
    private Integer id;

    /**
     * Date of order creation.
     */
    private Date date = new Date();

    /**
     * User, who made this order.
     */
    private User user;

    /**
     * For how long user takes the books
     */
    private boolean returned;

    /**
     * List of items, that belong to this order.
     */
    private List<OrderMediator> orderParts = new ArrayList<>();

    /**
     * is taking books to reading hall or to home. Reading hall by default
     */
    private boolean atLibrary;

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAtLibrary() {
        return atLibrary;
    }

    public void setAtLibrary(boolean isAtLibrary) {
        this.atLibrary = isAtLibrary;
    }

    public List<OrderMediator> getOrderParts() {
        return orderParts;
    }

    public void setOrderParts(List<OrderMediator> orderParts) {
        this.orderParts = orderParts;
    }

    public void addOrderMediator(OrderMediator orderMediator) {
        orderParts.add(orderMediator);
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
