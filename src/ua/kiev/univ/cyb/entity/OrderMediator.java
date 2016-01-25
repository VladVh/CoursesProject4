package ua.kiev.univ.cyb.entity;

import ua.kiev.univ.cyb.dao.Identified;

/**
 * Entity class to represent items of the order
 */
public class OrderMediator implements Identified<Integer> {

    /**
     * Identifier to distinguish, primary key in database.
     */
    private Integer id;

    /**
     * Order, that these books belong to.
     */
    private Order order;

    /**
     * Book, that is a part of an order
     */
    private Book book;

    /**
     * Number of books
     */
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
