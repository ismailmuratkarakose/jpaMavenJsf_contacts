package com.ismail.contacts.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ismail.contacts.entity.Contact;
import com.ismail.contacts.entity.Contactphone;

public class ContactController {
	EntityManagerFactory emf;
	EntityManager em;

	public ContactController() {
		emf = Persistence.createEntityManagerFactory("Contacts");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	public List<Contact> getAllContacts() {
		try {
			return em.createNamedQuery("Contact.findAllContacts", Contact.class).getResultList();
		} catch (Exception e) {
			System.err.println("There is a problem about entityManager");
			return null;
		}
	}

	public List<Contactphone> getAllContactPhones() {
		try {
			return em.createNamedQuery("Contactphone.findAll", Contactphone.class).getResultList();
		} catch (Exception e) {
			System.err.println("There is a problem about entityManager");
			return null;
		}
	}

	public boolean removeContact(Contact contact) {
		try {
			em.remove(contact);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean addContact(Contact contact) {
		try {
			em.persist(contact);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateContact(Contact contact) {
		try {
			em.merge(contact);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
