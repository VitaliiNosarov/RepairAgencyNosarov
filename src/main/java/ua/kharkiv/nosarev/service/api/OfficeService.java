package ua.kharkiv.nosarev.service.api;

import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;

import java.util.List;

public interface OfficeService {

    /**
     * Return All services from Database
     *
     * @return List of all services
     */
    List<Service> getAllServices();

    /**
     * Validate Service and save into Database. Return InfoMessage ADD_FEEDBACK_SUCCESS when result
     * is true and WRONG_FIELDS when result is false
     *
     * @param service
     * @return message as a result of operation
     */
    InfoMessage saveService(Service service);
}
