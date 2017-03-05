package utils;

/**
 * Created by Александр on 05.03.2017.
 */
public class Country {
    private String countryName;
    private String statesUrl;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStatesUrl() {
        return statesUrl;
    }

    public void setStatesUrl(String statesUrl) {
        this.statesUrl = statesUrl;
    }


    @Override
    public String toString() {
        return "Country: " + countryName + "\nZones URL: " + statesUrl;
    }
}
