package es.tresw.view.controller.sportfacility;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import es.tresw.db.entities.SportFacility;

@ManagedBean
@RequestScoped
public class HomeController {
	
	private List<SportFacility> instalaciones;
	private DashboardModel model;
	
	public HomeController() {
		model = new DefaultDashboardModel();
		DashboardColumn column1 = new DefaultDashboardColumn();
		DashboardColumn column2 = new DefaultDashboardColumn();
		
		column1.addWidget("ocupacion");
		column1.addWidget("notificaciones");
		
		column2.addWidget("instalaciones");
		column2.addWidget("publicidad");
		
		model.addColumn(column1);
		model.addColumn(column2);
		
		
		//Creamos la lista de instalaciones a mano
		SportFacility sf1 = new SportFacility();
		sf1.setName("Charo de la pava");
		sf1.setId(new Long(1));
		SportFacility sf2 = new SportFacility();
		sf2.setName("Tejares");
		sf2.setId(new Long(2));
		SportFacility sf3 = new SportFacility();
		sf3.setName("Mar de plata");
		sf3.setId(new Long(3));
		instalaciones = new ArrayList<SportFacility>();
		instalaciones.add(sf1);
		instalaciones.add(sf2);
		instalaciones.add(sf3);
		
	}
	
	public void handleReorder(DashboardReorderEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Reordered: " + event.getWidgetId());
		message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
		
		addMessage(message);
	}
	
	
	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public DashboardModel getModel() {
		return model;
	}

	public List<SportFacility> getInstalaciones() {
		return instalaciones;
	}

	public void setInstalaciones(List<SportFacility> instalaciones) {
		this.instalaciones = instalaciones;
	}
	
}
