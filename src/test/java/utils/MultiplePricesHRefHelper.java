package utils;

import java.util.logging.Logger;

/**
 * Created by Александр on 07.03.2017.
 */
public class MultiplePricesHRefHelper extends HRefHelper {
    protected Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String regularPriceValue;
    private String campaignPriceValue;
    private String regularPriceDetailedViewLocator;
    private String campaignPriceDetailedViewLocator;

    public String getRegularPriceValue() {
        return regularPriceValue;
    }

    public void setRegularPriceValue(String regularPriceValue) {
        this.regularPriceValue = regularPriceValue;
        logger.info("Установлена регулярная цена: " + this.regularPriceValue);
    }

    public String getCampaignPriceValue() {
        return campaignPriceValue;
    }

    public void setCampaignPriceValue(String campaignPriceValue) {
        this.campaignPriceValue = campaignPriceValue;
        logger.info("Установлена акционная цена: " + this.campaignPriceValue);
    }

    public String getRegularPriceDetailedViewLocator() {
        return regularPriceDetailedViewLocator;
    }

    public void setRegularPriceDetailedViewLocator(String regularPriceDetailedViewLocator) {
        this.regularPriceDetailedViewLocator = regularPriceDetailedViewLocator;
        logger.info("Установлен локатор для нахождения регулярной цены: " + this.regularPriceDetailedViewLocator);
    }

    public String getCampaignPriceDetailedViewLocator() {
        return campaignPriceDetailedViewLocator;
    }

    public void setCampaignPriceDetailedViewLocator(String campaignPriceDetailedViewLocator) {
        this.campaignPriceDetailedViewLocator = campaignPriceDetailedViewLocator;
        logger.info("Установлен локатор для нахождения акционной цены: " + this.campaignPriceDetailedViewLocator);
    }
}
