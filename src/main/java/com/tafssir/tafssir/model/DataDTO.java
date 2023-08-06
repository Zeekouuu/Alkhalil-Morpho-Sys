package com.tafssir.tafssir.model;

public class DataDTO {
    private Integer naya;
    private Integer nsoura;
    private Integer ntafsir;
    private String texte;
    private String nom_t;
    private Integer an;
    private String nom_s;
    private Integer approuve;
    private String description;
    private String noms;
    private String makki_madani;
    private String aya;
    private String aya2;
    private String aya4;
    private String aya3;
    private Integer npartie;
    private Integer nhizb;
    private Integer npage;
    private String lemme;

    public DataDTO() {
    }

    // Ajoutez ici les constructeurs, les getters et les setters

    // Exemple de constructeur avec tous les attributs
    public DataDTO(Integer naya, Integer nsoura, Integer ntafsir, String texte, String nom_t, Integer an,
                   String nom_s, Integer approuve, String description, String noms, String makki_madani,
                   String aya, String aya2, String aya4, String aya3, Integer npartie, Integer nhizb,
                   Integer npage, String lemme) {
        this.naya = naya;
        this.nsoura = nsoura;
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

    public Integer getNaya() {
        return naya;
    }

    public void setNaya(Integer naya) {
        this.naya = naya;
    }

    public Integer getNsoura() {
        return nsoura;
    }

    public void setNsoura(Integer nsoura) {
        this.nsoura = nsoura;
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
// Ajoutez ici les getters et les setters pour les attributs
}
