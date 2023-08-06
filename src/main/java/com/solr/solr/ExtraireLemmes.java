package com.solr.solr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ExtraireLemmes {
    public ArrayList<String> getTexte(String path) throws IOException {
        ArrayList<String> Tous = new ArrayList<>();
        String yoyo ="";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.contains("<lemma>") && !line.matches(".*\\d+.*") && !line.contains("EOL") && !line.contains(" ")) {
               // System.out.println(line + "------------premier----------\n");
                String text = line.replaceAll("<[^>]*>", "");
              //  System.out.println(text + "-----------dexieme------------\n");
                text = text.replaceAll("[:,.]", "");
                  text = text.replaceAll("[^\\p{L}\\p{Nd}]+", "");
                //  System.out.println(text+"---------------troisieme--------------------");


                if (!text.trim().isEmpty()) {
                   yoyo= yoyo.concat(text);
                    Tous.add(text);
                    // lignesUniques.add(text);


                }


            }
        }return Tous;
    }
    public  String supprimerDernierMot(String chaine) {
        // Trouver la position du dernier espace
        int dernierEspace = chaine.lastIndexOf(" ");

        // Extraire la sous-chaîne sans le dernier mot
        String resultat = chaine.substring(0, dernierEspace-3);

        return resultat;
    }
}
