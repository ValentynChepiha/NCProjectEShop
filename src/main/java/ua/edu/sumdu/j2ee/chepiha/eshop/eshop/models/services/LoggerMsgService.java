package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerMsgService {

    private Logger logger = null;
    private String className = null;

    public LoggerMsgService(Class className) {
        logger = LoggerFactory.getLogger(className.getName());
        this.className = className.getSimpleName();
    }

    public void msgError(String string, Throwable throwable) {
        logger.error(string, throwable);
    }

    public void msgDebug(String string) {
        logger.debug(className + ": " + string);
    }

    public void msgDebugCreate(String string) {
        logger.debug("Create new " + className + ": " + string);
    }

    public void msgDebugCreateNewId(long id) {
        logger.debug("Id new " + className + ": " + id);
    }

    public void msgDebugUpdateNewValue(String string) {
        logger.debug("Update " + className + "(new value): " + string);
    }

    public void msgDebugUpdateOldValue(String string) {
        logger.debug("Update " + className + "(old value): " + string);
    }

    public void msgDebugDelete(long id) {
        logger.debug("Delete " + className + " with Id: " + id);
    }

    public void msgDebugGetAll() {
        logger.debug("Get all " + className + "s");
    }

    public void msgDebugGetOne(long id) {
        logger.debug("Get " + className + " by Id: " + id);
    }

    public void msgDebugDeleteByIdOrder(long id) {
        logger.debug("Delete " + className + " with IdOrder: " + id);
    }

    public void msgDebugGetOrderByIdOrder(long id) {
        logger.debug("Get all " + className + "s with IdOrder: " + id);
    }

    public void msgDebugGetFullInfoAboutOrder(long id) {
        logger.debug("Get full info about " + className + " with IdOrder: " + id);
    }

    public void msgDebugUpdateCountProducts(long id, int count) {
        logger.debug("Update to " + className + " by id = " + id + " count(new value): " + count);
    }

    public void msgDebugGetAllWithoutOneId(long id) {
        logger.debug("Get all " + className + "s without id: " + id);
    }

    public void msgDebugUpdateUserRole(long id, String role) {
        logger.debug("Update to " + className + " by id = " + id + " role(new value): " + role);
    }

    public void msgDebugGetOneOnlyAuthority(long id, String role){
        logger.debug("Get authority to " + className + " by id: " + id + ", role: " + role);
    }

}
