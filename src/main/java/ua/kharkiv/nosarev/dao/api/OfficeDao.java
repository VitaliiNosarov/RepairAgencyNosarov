package ua.kharkiv.nosarev.dao.api;

import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;

import java.util.List;

public interface OfficeDao {

    List<Service> getAllServices(UserLocale language);

    boolean saveService(Service service);
}
