package com.ismail.contacts.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.ismail.contacts.controller.ContactController;
import com.ismail.contacts.entity.Contact;
import com.ismail.contacts.entity.Contactphone;

@ManagedBean(name = "contactBean")
@ViewScoped
public class ContactBean {
	private ContactController facade = new ContactController();
	private Contact selectedContact;
	private List<Contact> contacts;
	private List<Contactphone> phones;
	private List<String> phonesStr;
	private String username, usersurname, useremail, userphone, useraddress;

	public ContactBean() {
		init();
	}

	private void init() {
		contacts = facade.getAllContacts();
	}

	public Contact getSelectedContact() {
		return selectedContact;
	}

	public void setSelectedContact(Contact selectedContact) {
		this.selectedContact = selectedContact;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Contactphone> getPhones() {
		return phones;
	}

	public void setPhones(List<Contactphone> phones) {
		this.phones = phones;
	}

	public List<String> getPhonesStr() {
		return phonesStr;
	}

	public void setPhonesStr(List<String> phonesStr) {
		this.phonesStr = phonesStr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsersurname() {
		return usersurname;
	}

	public void setUsersurname(String usersurname) {
		this.usersurname = usersurname;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Contact Selected", ((Contact) event.getObject()).getUsername());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		phones = selectedContact.getContactphones();
		phonesStr = new ArrayList<String>();
		for (Contactphone temp : phones) {
			phonesStr.add(temp.getPhonenumber());
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		selectedContact = null;
		FacesMessage msg = new FacesMessage("Contact Unselected", ((Contact) event.getObject()).getUsername());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void deleteContact() {
		if (facade.removeContact(selectedContact)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, selectedContact.getUsername() + " deleted.",
					null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					selectedContact.getUsername() + " cannot delete.", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public void updateContact() {
		if (facade.updateContact(selectedContact)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, selectedContact.getUsername() + " updated.",
					null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					selectedContact.getUsername() + " cannot update.", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void addContact() {
		Contact newContact = new Contact();
		Contactphone newContactphone = new Contactphone();
		newContactphone.setPhonenumber(userphone);
		List<Contactphone> list = new ArrayList<Contactphone>();
		list.add(newContactphone);
		newContact.setUseremail(useremail);
		newContact.setUsername(username);
		newContact.setUsersurname(usersurname);
		newContact.setUseraddres(useraddress);
		newContact.setContactphones(list);
		if (facade.addContact(newContact)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, selectedContact.getUsername() + " added.",
					null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					selectedContact.getUsername() + " cannot add.", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

}
