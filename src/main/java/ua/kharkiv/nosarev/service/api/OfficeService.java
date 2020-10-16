package ua.kharkiv.nosarev.service.api;

import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;

import java.util.List;

public interface OfficeService {

    List<Service> getAllServices(UserLocale language);
}
