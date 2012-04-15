package es.tresw.view.controller.sportfacility;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

public class PruebaController implements Serializable{
	
	private String city;  
	  
    private String suburb;  
      
    private Map<String,String> cities = new HashMap<String, String>();  
  
    private Map<String,Map<String,String>> suburbsData = new HashMap<String, Map<String,String>>();  
      
    private Map<String,String> suburbs = new HashMap<String, String>();  
  
    public PruebaController() {  
        cities.put("Sevilla", "Sevilla");  
        cities.put("Malaga", "Malaga");  
        cities.put("Cadiz", "Cadiz");  
          
        Map<String,String> suburbsIstanbul = new HashMap<String, String>();  
        suburbsIstanbul.put("Dos hermanas", "Dos hermanas");  
        suburbsIstanbul.put("San Juan", "San Juan");  
        suburbsIstanbul.put("Mairena", "Mairena");  
          
        Map<String,String> suburbsAnkara = new HashMap<String, String>();  
        suburbsAnkara.put("Marbella", "Marbella");  
        suburbsAnkara.put("Ronda", "Ronda");  
        suburbsAnkara.put("Malaga", "Malaga");  
          
        Map<String,String> suburbsIzmir = new HashMap<String, String>();  
        suburbsIzmir.put("Conil", "Conil");  
        suburbsIzmir.put("Chiclana", "Chiclana");  
        suburbsIzmir.put("Trebujena", "Trebujena");  
          
        suburbsData.put("Sevilla", suburbsIstanbul);  
        suburbsData.put("Malaga", suburbsAnkara);  
        suburbsData.put("Cadiz", suburbsIzmir);  
        
    }  
      
    public String getCity() {  
        return city;  
    }  
  
    public void setCity(String city) {  
        this.city = city;  
    }  
  
    public String getSuburb() {  
        return suburb;  
    }  
  
    public void setSuburb(String suburb) {  
        this.suburb = suburb;  
    }  
  
    public Map<String, String> getCities() {  
        return cities;  
    }  
  
    public void setCities(Map<String, String> cities) {  
        this.cities = cities;  
    }  
      
    public Map<String, Map<String, String>> getSuburbsData() {  
        return suburbsData;  
    }  
  
    public void setSuburbsData(Map<String, Map<String, String>> suburbsData) {  
        this.suburbsData = suburbsData;  
    }  
      
    public Map<String, String> getSuburbs() {  
        return suburbs;  
    }  
  
    public void setSuburbs(Map<String, String> suburbs) {  
        this.suburbs = suburbs;  
    }  
      
    public void handleCityChange() {  
        if(city !=null && !city.equals(""))  
            suburbs = suburbsData.get(city);  
        else  
            suburbs = new HashMap<String, String>();  
    }  
    
    public void handleCityChange(ActionEvent a) {  
        if(city !=null && !city.equals(""))  
            suburbs = suburbsData.get(city);  
        else  
            suburbs = new HashMap<String, String>();  
    }  
    
    public void verCiudad() {  
    	FacesMessage msg = new FacesMessage("Selected", "City:" + city + ", Suburb: " + suburb);  
    	  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
  
    public void displayLocation() {  
        FacesMessage msg = new FacesMessage("Selected", "City:" + city + ", Suburb: " + suburb);  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
}
