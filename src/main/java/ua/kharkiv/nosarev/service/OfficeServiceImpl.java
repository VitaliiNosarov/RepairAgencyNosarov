package ua.kharkiv.nosarev.service;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.OfficeDao;
import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.service.api.OfficeService;

import java.util.List;

public class OfficeServiceImpl implements OfficeService {

    private static final Logger LOGGER = Logger.getLogger(OfficeServiceImpl.class);
    private OfficeDao officeDao;
    private Validator validator;

    public OfficeServiceImpl(OfficeDao officeDao, Validator validator) {
        this.officeDao = officeDao;
        this.validator = validator;
    }

    @Override
    public List<Service> getAllServices() {
        return officeDao.getAllServices();
    }

    @Override
    public InfoMessage saveService(Service service) {
        if (validator.validateService(service) && officeDao.saveService(service)) {
            return InfoMessage.SAVE_SERVICE_SUCCESS;
        }
        LOGGER.info("Can't save service to db. Wrong fields");
        return InfoMessage.WRONG_FIELDS;
    }
}
