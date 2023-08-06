package com.solr.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/search")
@CrossOrigin("*")
public class SearchController {
    String solrUrl = "http://host.docker.internal:8983/solr";
    String coreName = "zako";
    //String query = "*:*"; // Query to extract all documents

    SolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();


    @GetMapping
    public List<SolrDocument> search(@RequestParam("query") String query) throws SolrServerException, IOException {
        FileWriter fileWriter = new FileWriter("../query.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(query);
        bufferedWriter.close();
        //generer les lemmes
        net.oujda_nlp_team.ADATAnalyzer.getInstance().processLemmatization("../query.txt", "utf-8", "../query2.txt", "utf-8");
        ExtraireLemmes extraireLemmes = new ExtraireLemmes();
        //extraire les lemmes
        ArrayList<String> g = extraireLemmes.getTexte("../query2.txt");
        System.out.println(g);
        // faire la recherche par mot et par lemme en meme temps
        //String tout = "Nom :" + query + " OR Aya:\"" + query + "\"~1 OR Nom_S:\"" + query + "\"~1 OR Texte:\"" + query + "\"~1 OR";;
        String tout = " ";
        String t1 = "(";
        String t2 = "(";
        String t3 = "(";
        String t4 = "(";
        for(String qu : g) {
            t1 =t1+ "Nom :*" + qu+"* AND " ;
            t2 =t2+ "Aya :*" + qu+"* AND " ;
            t3 =t3+ "Nom_S :*" + qu+"* AND " ;
            t4 =t4+ "Texte :" + qu+" AND " ;
            // String quer = "Nom :" + qu + " OR Aya:\"" + qu + "\"~1 OR Nom_S:\"" + qu + "\"~1 OR Texte:\"" + qu + "\"~1";
        }
        t1= extraireLemmes.supprimerDernierMot(t1);
        t1=t1+")";
        t2= extraireLemmes.supprimerDernierMot(t2);
        t2=t2+")";
        t3= extraireLemmes.supprimerDernierMot(t3);
        t3=t3+")";
        t4= extraireLemmes.supprimerDernierMot(t4);
        t4=t4+")";
        tout= t1 +" OR "+t2+" OR "+t3+" OR "+t4;
        System.out.println(tout);
        SolrQuery solrQuery = new SolrQuery();

        // solrQuery.setRows(59011);//le nombre de document
        solrQuery.setRows(590);//le nombre de document

        solrQuery.setQuery(tout); // Définissez la requête de recherche

        // Définissez les champs que vous souhaitez rechercher (Nom, Aya, Nom_S)
        solrQuery.set("fl", "Nom_S,Nom,Aya,Texte");

        QueryResponse response = solrClient.query(coreName, solrQuery, SolrRequest.METHOD.GET);
        // QueryResponse response = solrClient.query(solrQuery); // Effectuez la recherche

        SolrDocumentList documents = response.getResults();
        System.out.println("Total documents found: " + documents.getNumFound());
        for (SolrDocument doc : documents){
            // System.out.println(doc);
        }
        return documents; // Renvoyez les résultats de la recherche
    }
}

