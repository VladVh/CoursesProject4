package ua.kiev.univ.cyb.dao;


import java.io.Serializable;
import java.util.List;

/**
 * Unified interface for managing persistent objects
 *
 * @param <T>  persistent object's type
 * @param <PK> type of primary key
 */
public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    /**
     * Creates new record and corresponding object.
     *
     * @return created object
     * @throws PersistException
     */
    public T create() throws PersistException;

    /**
     * Creates new record.
     *
     * @param object object to save.
     * @return saved object.
     * @throws PersistException
     */
    public T persist(T object) throws PersistException;

    /**
     * @param key primary key of record.
     * @return object, corresponding to record with primary key or null.
     * @throws PersistException
     */
    public T getByPK(PK key) throws PersistException;

    /**
     * Updates object state in database.
     *
     * @param object object to update.
     * @throws PersistException
     */
    public void update(T object) throws PersistException;

    /**
     * Deletes record of that object from database.
     *
     * @param object object that represents record in database, that needs to be deleted.
     * @throws PersistException
     */
    public void delete(T object) throws PersistException;

    /**
     * @return list of objects, corresponding to all records in database.
     * @throws PersistException
     */
    public List<T> getAll() throws PersistException;
}
