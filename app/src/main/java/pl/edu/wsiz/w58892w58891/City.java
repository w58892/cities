package pl.edu.wsiz.w58892w58891;

class City {
    private String cityName;
    private String criminal;
    private String economic;
    private String road;
    private String beating;
    private String thefts;
    private String criminalDetectability;
    private String economicDetectability;
    private String roadDetectability;
    private String beatingDetectability;
    private String theftsDetectability;
    private String v;
    private String vl;
    public City(String cityName,  String criminal, String economic, String road, String beating, String thefts, String criminalDetectability, String economicDetectability, String roadDetectability, String beatingDetectability, String theftsDetectability, String v, String vl){
        this.setCityName(cityName);
        this.setCriminal(criminal);
        this.setEconomic(economic);
        this.setRoad(road);
        this.setBeating(beating);
        this.setThefts(thefts);
        this.setCriminalDetectability(criminalDetectability);
        this.setEconomicDetectability(economicDetectability);
        this.setRoadDetectability(roadDetectability);
        this.setBeatingDetectability(beatingDetectability);
        this.setTheftsDetectability(theftsDetectability);
        this.setV(v);
        this.setVl(vl);
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCriminal() {
        return criminal;
    }

    public void setCriminal(String criminal) {
        this.criminal = criminal;
    }

    public String getEconomic() {
        return economic;
    }

    public void setEconomic(String economic) {
        this.economic = economic;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getBeating() {
        return beating;
    }

    public void setBeating(String beating) {
        this.beating = beating;
    }

    public String getThefts() {
        return thefts;
    }

    public void setThefts(String thefts) {
        this.thefts = thefts;
    }

    public String getCriminalDetectability() {
        return criminalDetectability;
    }

    public void setCriminalDetectability(String criminalDetectability) {
        this.criminalDetectability = criminalDetectability;
    }

    public String getEconomicDetectability() {
        return economicDetectability;
    }

    public void setEconomicDetectability(String economicDetectability) {
        this.economicDetectability = economicDetectability;
    }

    public String getRoadDetectability() {
        return roadDetectability;
    }

    public void setRoadDetectability(String roadDetectability) {
        this.roadDetectability = roadDetectability;
    }

    public String getBeatingDetectability() {
        return beatingDetectability;
    }

    public void setBeatingDetectability(String beatingDetectability) {
        this.beatingDetectability = beatingDetectability;
    }

    public String getTheftsDetectability() {
        return theftsDetectability;
    }

    public void setTheftsDetectability(String theftsDetectability) {
        this.theftsDetectability = theftsDetectability;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getVl() {
        return vl;
    }

    public void setVl(String vl) {
        this.vl = vl;
    }

}