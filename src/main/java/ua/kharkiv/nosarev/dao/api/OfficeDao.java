package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.Service;

import java.util.List;

public interface OfficeDao {

    /**
     * Returns List of services from Database
     *
     * @return list of services
     */
    List<Service> getAllServices();

    /**
     * Save not null service to Database
     *
     * @param service not null. Should be validate before
     * @return boolean result of operation
     */
    boolean saveService(Service service);
}
