package ua.kharkiv.nosarev.services;

import ua.kharkiv.nosarev.dao.api.OfficeDao;
import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.services.api.OfficeService;

import java.util.List;

public class OfficeServiceImpl implements OfficeService {

    private OfficeDao officeDao;

    public OfficeServiceImpl(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    @Override
    public List<Service> getAllServices(UserLocale language) {
        return officeDao.getAllServices(language);
    }
}
