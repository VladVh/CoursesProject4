package ua.kiev.univ.cyb.entity;

import ua.kiev.univ.cyb.dao.Identified;

import java.util.Date;

/**
 * Entity class to represent book in library
 */
public class Book implements Identified<Integer> {
    /**
     * Identifier to distinguish, primary key in database.
     */
    private Integer id;

    /**
     * Title of a book
     */
    private String name;

    /**
     * Author of a book
     */
    private String author;

    /**
     * Description of a book
     */
    private String description;

    /**
     * URL of a book image
     */
    private String image;

    /**
     * When was published
     */
    private Date published = new Date();

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
