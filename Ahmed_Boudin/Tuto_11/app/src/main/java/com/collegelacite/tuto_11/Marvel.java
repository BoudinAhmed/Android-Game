package com.collegelacite.tuto_11;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

// Classe implantant un descriptif de personnage de film
public class Marvel {
    private String nom = null;            // nom du personnage
    private String drawable = null;       // nom du fichier d'image du personnage
    private boolean dévoilé = false;      // indique si le nom doit être dévoilé ou pas

    // Constructeur par défaut
    public Marvel() {
        this.setNom(null);
        this.setDrawable(null);
    }

    // Constructeur paramétré
    public Marvel(String nom, String drawable) {
        this.setNom(nom);
        this.setDrawable(drawable);
    }

    // Accesseur de l'attribut nom
    public String getNom() {
        return nom;
    }

    // Mutateur de l'attribut nom
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Accesseur de l'attribut drawable
    public String getDrawable() {
        return drawable;
    }

    // Mutateur de l'attribut seawable
    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }

    // Accesseur de l'attribut dévoilé
    public boolean getDévoilé() {
        return dévoilé;
    }

    // Mutateur de l'attribut dévoilé
    public void setDévoilé(boolean valeur) {
        dévoilé = valeur;
    }

    // Retourne une chaîne décrivant brièvement le personnage
    @Override
    public String toString() {
        return this.getNom();
    }

    // Désérialiser une liste de personnages d'un fichier JSON
    public static ArrayList<Marvel> lireFichier(String nomFichier, Context contexte){
        final ArrayList<Marvel> liste = new ArrayList<>();

        try {
            // Charger les données dans un ArrayList
            String     jsonString  = lireJson(nomFichier, contexte);
            JSONObject json        = new JSONObject(jsonString);
            JSONArray  personnages = json.getJSONArray("marvel");

            // Lire chaque personnage du fichier
            for(int i = 0; i < personnages.length(); i++){
                Marvel p = new Marvel();

                p.nom      = personnages.getJSONObject(i).getString("nom");
                p.drawable = personnages.getJSONObject(i).getString("drawable");

                liste.add(p);
            }
        } catch (JSONException e) {
            // Une erreur s'est produite (on la journalise)
            e.printStackTrace();
        }

        return liste;
    }

    // Retourne une balise lue d'un fichier JSON
    private static String lireJson(String nomFichier, Context contexte) {
        String json = null;

        try {
            InputStream is = contexte.getAssets().open(nomFichier);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            // Une erreur s'est produite (on la journalise)
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
