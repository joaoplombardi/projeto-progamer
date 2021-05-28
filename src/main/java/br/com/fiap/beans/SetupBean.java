package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.SetupDao;
import br.com.fiap.dao.UserDao;
import br.com.fiap.model.Setup;
import br.com.fiap.model.User;

//@ManagedBean   //JSF  |-> CDI
@Named           //EJB  |
@RequestScoped
public class SetupBean {
	FacesContext context = FacesContext.getCurrentInstance();

	private User userContext = (User) context.getExternalContext().getSessionMap().get("user");
	private Setup setup = new Setup();
	private UserDao userDao = new UserDao();

	public void save() {
		User user = userDao.findByEmail(userContext.getEmail());
		System.out.println(user.getId() + user.getName() + user.getEmail());
		setup.setOwner(user.getId());
		new SetupDao().save(this.setup);
		System.out.println("Salvando..." + this.setup);
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("Setup cadastrado com sucesso!"));
	}
	
	public List<Setup> getSetups(){
		return new SetupDao().getAll();
	}
	
	public List<Setup> getSetupsFromUser(){
		User user = userDao.findByEmail(userContext.getEmail());
		return new SetupDao().getAllFromUser(user.getId());
	}

	public Setup getSetup() {
		return setup;
	}

	public void setSetup(Setup setup) {
		this.setup = setup;
	}

	
}
