package ua.kiev.univ.cyb.dao;


/**
 * Object factory to work with database
 *
 * @param <Context> context to work with datasource. For database it is <code>Connection</code>
 */
public interface DaoFactory<Context> {

    public interface DaoCreator<Context> {
        public GenericDao create(Context context);
    }

    /**
     * Object to work with persistent properties of object
     *
     * @param dtoClass class of object to work with
     * @param context  context of datasource
     * @return appropriate DAO object, according to dtoClass parameter
     * @throws PersistException
     */
    public GenericDao getDao(Class dtoClass, Context context) throws PersistException;
}
