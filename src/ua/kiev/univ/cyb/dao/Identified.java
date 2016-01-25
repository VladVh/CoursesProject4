package ua.kiev.univ.cyb.dao;

import java.io.Serializable;

/**
 * Interface of identified objects.
 *
 * @param <PK> type of primary key.
 */
public interface Identified<PK extends Serializable> {

    /**
     * @return identifier of an object, determined by primary key in database.
     */
    public PK getId();
}
