public class city {
 String cityName, regionName;
    public city(String cityName, String regionName) {
        this.cityName = cityName;
        this.regionName = regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    @Override
    public String toString(){
        return cityName+"-"+regionName;
    }
}
