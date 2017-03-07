package utils;

import java.util.logging.Logger;

/**
 * Created by Александр on 05.03.2017.
 */
public class HRefHelper {
    protected Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String value;
    protected String url;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        logger.info("Установлено значение: " + this.value);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        logger.info("Установлен URL: " + this.url);
    }


    @Override
    public String toString() {
        return "Name: " + value + "\nURL: " + url;
    }
}
