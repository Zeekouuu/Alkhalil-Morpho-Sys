package com.tafssir.tafssir.model;


import jakarta.persistence.*;

@Entity
@Table(name = "my_table")
public class Tafssir {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private Long id;

	@Column(name = "NAya" , length = 100)
	private Integer naya;

	private Integer an;
	@Column(name = "NSoura" , length = 100)
	private Integer nsoura;

	@Column(name = "NTafsir" , length = 50)
	private Integer ntafsir;
	@Column(name = "Texte" , length = 1000)
	private String texte;
	@Column(name = "NomT" , length = 60)
	private String nom_t;
	@Column(name = "Approuve" , length = 1000)
	private Integer approuve;
	@Column(name = "Nom_S" , length = 60)
	private String nom_s;
	// @Column(name = "Approuve" , length = 1000)
	// private Integer approuve;
	@Column(name = "Description" , length = 1000)
	private String description;
	@Column(name = "NomS" , length = 50)
	private String noms;
	@Column(name = "makki_madani" , length = 50)
	private String makki_madani;
	@Column(name = "Aya" , length = 1000)
	private String aya;
	@Column(name = "Aya2" , length = 1000)
	private String aya2;
	@Column(name = "Aya4" , length = 1000)
	private String aya4;
	@Column(name = "Aya3" , length = 1000)
	private String aya3;
	@Column(name = "NPartie" , length = 50)
	private Integer npartie;
	@Column(name = "NHizb" , length = 50)
	private Integer nhizb;

	@Column(name = "NPage" , length = 50)
	private Integer npage;
	@Column(name = "Lemme" , length = 1000)
	private String lemme;

	public Tafssir(Integer nsoura, Integer naya, Integer ntafsir, String texte, String nom_t, Integer an, String nom_s, Integer approuve, String description, String noms, String makki_madani, String aya, String aya2, String aya4, String aya3, Integer npartie, Integer nhizb, Integer npage, String lemme) {
		//this.id = id;
		this.nsoura = nsoura;
		this.naya = naya;
		this.ntafsir = ntafsir;
		this.texte = texte;
		this.nom_t = nom_t;
		this.an = an;
		this.nom_s = nom_s;
		this.approuve = approuve;
		this.description = description;
		this.noms = noms;
		this.makki_madani = makki_madani;
		this.aya = aya;
		this.aya2 = aya2;
		this.aya4 = aya4;
		this.aya3 = aya3;
		this.npartie = npartie;
		this.nhizb = nhizb;
		this.npage = npage;
		this.lemme = lemme;
	}

	public Tafssir() {
	}


	public Integer getNsoura() {
		return nsoura;
	}

	public void setNsoura(Integer nsoura) {
		this.nsoura = nsoura;
	}

	public Integer getNaya() {
		return naya;
	}

	public void setNaya(Integer naya) {
		this.naya = naya;
	}

	public Integer getNtafsir() {
		return ntafsir;
	}

	public void setNtafsir(Integer ntafsir) {
		this.ntafsir = ntafsir;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getNom_t() {
		return nom_t;
	}

	public void setNom_t(String nom_t) {
		this.nom_t = nom_t;
	}

	public Integer getAn() {
		return an;
	}

	public void setAn(Integer an) {
		this.an = an;
	}

	public String getNom_s() {
		return nom_s;
	}

	public void setNom_s(String nom_s) {
		this.nom_s = nom_s;
	}

	public Integer getApprouve() {
		return approuve;
	}

	public void setApprouve(Integer approuve) {
		this.approuve = approuve;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNoms() {
		return noms;
	}

	public void setNoms(String noms) {
		this.noms = noms;
	}

	public String getMakki_madani() {
		return makki_madani;
	}

	public void setMakki_madani(String makki_madani) {
		this.makki_madani = makki_madani;
	}

	public String getAya() {
		return aya;
	}

	public void setAya(String aya) {
		this.aya = aya;
	}

	public String getAya2() {
		return aya2;
	}

	public void setAya2(String aya2) {
		this.aya2 = aya2;
	}

	public String getAya4() {
		return aya4;
	}

	public void setAya4(String aya4) {
		this.aya4 = aya4;
	}

	public String getAya3() {
		return aya3;
	}

	public void setAya3(String aya3) {
		this.aya3 = aya3;
	}

	public Integer getNpartie() {
		return npartie;
	}

	public void setNpartie(Integer npartie) {
		this.npartie = npartie;
	}

	public Integer getNhizb() {
		return nhizb;
	}

	public void setNhizb(Integer nhizb) {
		this.nhizb = nhizb;
	}

	public Integer getNpage() {
		return npage;
	}

	public void setNpage(Integer npage) {
		this.npage = npage;
	}

	public String getLemme() {
		return lemme;
	}

	public void setLemme(String lemme) {
		this.lemme = lemme;
	}
}