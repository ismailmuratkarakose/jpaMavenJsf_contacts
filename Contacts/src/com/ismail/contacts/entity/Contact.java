package com.ismail.contacts.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the contact database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Contact.findAllContacts", query = "SELECT c FROM Contact c"),
	@NamedQuery(name = "Contact.findContactById", query = "SELECT c FROM Contact c WHERE  c.contactid=:id")})
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int contactid;

	private String useraddres;

	private String useremail;

	private String username;

	private String usersurname;

	// bi-directional many-to-one association to Contactphone
	@OneToMany(mappedBy = "contact")
	private List<Contactphone> contactphones;

	public Contact() {
	}

	public int getContactid() {
		return this.contactid;
	}

	public void setContactid(int contactid) {
		this.contactid = contactid;
	}

	public String getUseraddres() {
		return this.useraddres;
	}

	public void setUseraddres(String useraddres) {
		this.useraddres = useraddres;
	}

	public String getUseremail() {
		return this.useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsersurname() {
		return this.usersurname;
	}

	public void setUsersurname(String usersurname) {
		this.usersurname = usersurname;
	}

	public List<Contactphone> getContactphones() {
		return this.contactphones;
	}

	public void setContactphones(List<Contactphone> contactphones) {
		this.contactphones = contactphones;
	}

	public Contactphone addContactphone(Contactphone contactphone) {
		getContactphones().add(contactphone);
		contactphone.setContact(this);

		return contactphone;
	}

	public Contactphone removeContactphone(Contactphone contactphone) {
		getContactphones().remove(contactphone);
		contactphone.setContact(null);

		return contactphone;
	}

}