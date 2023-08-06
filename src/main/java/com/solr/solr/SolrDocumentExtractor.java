 package com.solr.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SolrDocumentExtractor {

    public static void main(String[] args) throws IOException {
        String solrUrl = "http://host.docker.internal:8983/solr";
        String coreName = "zako";
        String query = "  الإيمان والطاعة"; // Query to extract all documents

        SolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();


        try {
            FileWriter fileWriter = new FileWriter("../query.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(query);
            bufferedWriter.close();
            net.oujda_nlp_team.ADATAnalyzer.getInstance().processLemmatization("../query.txt", "utf-8", "../query2.txt", "utf-8");
ExtraireLemmes extraireLemmes = new ExtraireLemmes();
ArrayList<String> g = extraireLemmes.getTexte("../query2.txt");
            System.out.println(g);
            String tout = " ";
            String t1 = "(";
            String t2 = "(";
            String t3 = "(";
            String t4 = "(";
            for(String qu : g) {
                t1 =t1+ "Nom :*" + qu+"* AND " ;
                t2 =t2+ "Aya :" + qu+" AND " ;
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
              //  tout = tout +" "+quer;
              //  System.out.println(tout);
                SolrQuery solrQuery = new SolrQuery(tout);
            solrQuery.setRows(590);//le nombre de document
            solrQuery.setQuery(tout);
            QueryResponse response = solrClient.query(coreName, solrQuery, SolrRequest.METHOD.GET);


                SolrDocumentList documents = response.getResults();

                System.out.println("Total documents found: " + documents.getNumFound());

               // for (SolrDocument document : documents) {
                    // Process each document as needed

                    //   System.out.println( document.get("Texte"));
                    //  String Nom = (String) document.get("Texte");
                    // System.out.println(Nom);
                    // String title = (String) document.get("title");
                    // ... Access other fields
                    //  System.out.println("Document ID: " + id + ", Title: " + title);
             //   }

        } catch ( IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            throw new RuntimeException(e);
        } finally {
            solrClient.close();
        }
    }
}

