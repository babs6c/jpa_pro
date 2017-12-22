package com.exos.entities;



import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

import com.exos.tools.JodaDateTimeConverter;



@Entity
@Table(name="membres")
public class Utilisateur implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//@Column(name="nom") On annote l'atttibut quand l'attribut et son élement dans la BD portent des noms différents,Sinon c'est facultatif
	private String nom;
	private String email;
	private String pass;
	private String photo;
	@Column(name="date_inscription",columnDefinition = "TIMESTAMP")
    @Converter( name = "dateTimeConverter", converterClass = JodaDateTimeConverter.class )
    @Convert( "dateTimeConverter" )
	private DateTime dateInscription;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public DateTime getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(DateTime date) {
		this.dateInscription = date;
	}
	
}
