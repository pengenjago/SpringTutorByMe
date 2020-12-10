package com.maanz.tutor.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Biodata extends AbstractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nama;
	private String jenisKelamin;
	private Date tglLahir;
	private String alamat;

	@OneToMany(mappedBy = "biodata", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Handphone> hp = new ArrayList<>();

	@OneToMany(targetEntity = Book.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Book> book = new ArrayList<>();

	public void setHp(List<Handphone> hp) {
		for (Handphone val : hp) {
			val.setBiodata(this);
		}
		this.hp = hp;
	}
}
