package com.collegelacite.tuto_11;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    ArrayList<Marvel> sourceDonnées;
    AdaptateurMarvel adaptateur;
    private Intent intention;

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 //ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // Populer la source de données pour l'adaptateur du GridView
        sourceDonnées = Marvel.lireFichier("marvel.json", this);

        // Créer l'adaptateur du GridView
        adaptateur = new AdaptateurMarvel(this, sourceDonnées);

        // Connecter le GridView à son adaptateur
        final GridView gridView = findViewById(R.id.gridViewId);
        gridView.setAdapter(adaptateur);


        // Installer un listener sur le GridView afin de gérer la sélection d'items
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {


                Intent i =new Intent();

                //stocker la position du caractere selectionner et le mettre dans un Extra pour pouvoir le chercher de l'autre Activité
                i.putExtra("position",position );

                setResult(RESULT_OK,i);
                finish();
            }
        });
    }//Fin du ONCREATE
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

}
