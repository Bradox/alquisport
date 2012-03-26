package es.tresw.view.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;

import es.tresw.util.FacesUtil;
import es.tresw.util.Messages;

public class LoginErrorPhaseListener implements PhaseListener{
	
	private static final long serialVersionUID = -1216620620302322995L;
	 
    @Override
    public void beforePhase(final PhaseEvent arg0)
    {
        Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
        		WebAttributes.AUTHENTICATION_EXCEPTION);
        
        //Miramos, y segun la excepcion que se encuentra se pinta un mensaje u otro	
        if (e instanceof UsernameNotFoundException)
        {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
            		WebAttributes.AUTHENTICATION_EXCEPTION, null);
            FacesUtil.addErrorMessage(Messages.getString("error.login.usuario_no_existe"));
        }else if (e instanceof BadCredentialsException)
        {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
            		WebAttributes.AUTHENTICATION_EXCEPTION, null);
            FacesUtil.addErrorMessage(Messages.getString("error.login.password_incorrecto"));
        }
    }
 
    @Override
    public void afterPhase(final PhaseEvent arg0)
    {}
 
    @Override
    public PhaseId getPhaseId()
    {
        return PhaseId.RENDER_RESPONSE;
    }

}
