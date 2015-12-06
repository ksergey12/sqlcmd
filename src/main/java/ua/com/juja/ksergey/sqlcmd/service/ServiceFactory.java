package ua.com.juja.ksergey.sqlcmd.service;

/**
 * Created by user on 06.12.15.
 */
public class ServiceFactory {

    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
