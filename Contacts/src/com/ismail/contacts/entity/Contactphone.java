package com.ismail.contacts.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the contactphone database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Contactphone.findAll", query = "SELECT c FROM Contactphone c")})

public class Contactphone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int contactphoneid;

	private String phonenumber;

	// bi-directional many-to-one association to Contact
	@ManyToOne
	@JoinColumn(name = "CONTACTID")
	private Contact contact;

	public Contactphone() {
	}

	public int getContactphoneid() {
		return this.contactphoneid;
	}

	public void setContactphoneid(int contactphoneid) {
		this.contactphoneid = contactphoneid;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}