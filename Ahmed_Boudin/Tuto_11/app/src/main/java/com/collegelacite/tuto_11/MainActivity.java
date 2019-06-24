package com.collegelacite.tuto_11;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //Declaration des variables
    ArrayList<Marvel> sourceDonnées;
    //Les personnages randomized
    String nomUn;
    String nomDeux;
    String nomTrois;

    //Les personnages deviner par l'utilisateur
    String textUn;
    String textDeux;
    String textTrois;


//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

 //ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sourceDonnées = Marvel.lireFichier("marvel.json", this);

        //Variable random
        Random rand = new Random();

        //Creer un chiffre random entre 0 et le nombre des caracteres
        int text1 = rand.nextInt(sourceDonnées.size());
        int text2 = rand.nextInt(sourceDonnées.size());
        int text3 = rand.nextInt(sourceDonnées.size());

        //Chercher les textViews
        TextView textView1 = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView4);


        //Chercher le caractere a la position choisi(aleatoire)
        Marvel un = sourceDonnées.get(text1);
        Marvel deux = sourceDonnées.get(text2);
        Marvel trois = sourceDonnées.get(text3);

        //Stocker les nom des carateres dans une variable
        nomUn = un.getNom();
        nomDeux = deux.getNom();
        nomTrois = trois.getNom();

        //Les afficher dans leur textViews
        textView1.setText(nomUn);
        textView2.setText(nomDeux);
        textView3.setText(nomTrois);



    }//Fin du ONCREATE
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   //Le onCLICK pour la premiere image
    public void leclick(View view){

        Intent intention = new Intent(this, Main2Activity.class);
        startActivityForResult(intention,1);//le 1 est pour le distinguer du reste


    }
    //Le onCLICK pour la deuxieme image
    public void leclick2(View view){

        Intent intention = new Intent(this, Main2Activity.class);
        startActivityForResult(intention,2);//meme chose


    }
    //Le onCLICK pour la troisieme image
    public void leclick3(View view){

        Intent intention = new Intent(this, Main2Activity.class);
        startActivityForResult(intention,3);//meme


    }

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // Fonction membre traitant le retour d'une intention
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//Debut du onActivityResult
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK) {// Si on revient de l'autre activité du projet, et qu'un code de succès est fourni,
            // et un requestCode de 1 qui a ete assignier pour le premier onClick
            //Cherche la position de l'element clicker
            int position = data.getIntExtra("position", 0);
            //Cherher le caractere a cette position
            Marvel m = sourceDonnées.get(position);
            //Stocker le nom du caractere dans cette variable(va etre utiliser pour comparer au nom gener pour le valider)
            textUn = m.getNom();
            //Afficher l'image
            int id = this.getResources().getIdentifier(m.getDrawable(),"drawable", this.getPackageName());
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageResource(id);

        }

        if(requestCode==2 && resultCode==RESULT_OK) {
            // Si on revient de l'autre activité du projet, et qu'un code de succès est fourni,
            // pour le deuxieme onClick

            //Cherche la position de l'element clicker
            int position = data.getIntExtra("position", 0);
            //Cherher le caractere a cette position
            Marvel m = sourceDonnées.get(position);
            //Stocker le nom du caractere dans cette variable(va etre utiliser pour comparer au nom gener pour le valider)
            textDeux = m.getNom();
            //Afficher l'image
            int id = this.getResources().getIdentifier(m.getDrawable(),"drawable", this.getPackageName());
            ImageView imageView = findViewById(R.id.imageView3);
            imageView.setImageResource(id);

        }

        if(requestCode==3 && resultCode==RESULT_OK) {
            // Si on revient de l'autre activité du projet, et qu'un code de succès est fourni,
            // pour le troisieme onClick

            //Cherche la position de l'element clicker
            int position = data.getIntExtra("position", 0);
            //Cherher le caractere a cette position
            Marvel m = sourceDonnées.get(position);
            //Stocker le nom du caractere dans cette variable(va etre utiliser pour comparer au nom gener pour le valider)
            textTrois = m.getNom();
            //Afficher l'image
            int id = this.getResources().getIdentifier(m.getDrawable(),"drawable", this.getPackageName());
            ImageView imageView = findViewById(R.id.imageView2);//fait une erreur pour le id la nommer 2 au lieu du trois pas voulu le changer
            imageView.setImageResource(id);


        }



    }//Fin du onActivityResult

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //le onClick pour le button verifier
    public void verifier(View view){

        //C'est un compteur pour le nombre de fois que l'utilisateur a bien deviner
        int verifier = 0;
        //on regarde si le caractere choisi par l'utilisateur est egal a celui qui a ete gener de facon aleatoire
            if(nomUn == textUn ){
                verifier++;
            }
            else {//Si il n'a pas bien deviner, retourne l'image au question mark
                int id = this.getResources().getIdentifier("question_mark","drawable", this.getPackageName());
                ImageView imageView1 = findViewById(R.id.imageView);
                imageView1.setImageResource(id);


            }
        //on regarde si le caractere choisi par l'utilisateur est egal a celui qui a ete gener de facon aleatoire

        if(nomDeux==textDeux){
                verifier++;
            }
            else {//Si il n'a pas bien deviner, retourne l'image au question mark
            int id = this.getResources().getIdentifier("question_mark","drawable", this.getPackageName());
            ImageView imageView1 = findViewById(R.id.imageView3);
            imageView1.setImageResource(id);

        }
        //on regarde si le caractere choisi par l'utilisateur est egal a celui qui a ete gener de facon aleatoire

        if ( nomTrois==textTrois){
                verifier++;
            }
            else{//Si il n'a pas bien deviner, retourne l'image au question mark
            int id = this.getResources().getIdentifier("question_mark","drawable", this.getPackageName());
            ImageView imageView1 = findViewById(R.id.imageView2);
            imageView1.setImageResource(id);

        }
        //on regarde si le caractere choisi par l'utilisateur est egal a celui qui a ete gener de facon aleatoire

        if(verifier==3)  {//verifier sera egal a 3 si l'utilsateur a bien deviner tout les trois
                Toast.makeText(getApplicationContext(),"Vous avez reussi!", Toast.LENGTH_SHORT).show();//Fait un message qui dit "Vous avez reussi!"
            }



    }//Fin du onClick pour le boutton verifier



//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void nouvellePartie(View view){//Debut du onClick pour le boutton nouvelle partie

        //Variable random
        Random rand = new Random();

        //Creer un chiffre random entre 0 et le nombre des caracteres
        int text1 = rand.nextInt(sourceDonnées.size());
        int text2 = rand.nextInt(sourceDonnées.size());
        int text3 = rand.nextInt(sourceDonnées.size());

        //Chercher les textViews
        TextView textView1 = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView4);


        //Chercher le caractere a la position choisi(aleatoire)
        Marvel un = sourceDonnées.get(text1);
        Marvel deux = sourceDonnées.get(text2);
        Marvel trois = sourceDonnées.get(text3);

        //Stocker les carateres dans une variable
        nomUn = un.getNom();
        nomDeux = deux.getNom();
        nomTrois = trois.getNom();

        //Les afficher
        textView1.setText(nomUn);
        textView2.setText(nomDeux);
        textView3.setText(nomTrois);

        //Enlever les images et les remplacer a des questions mark
        int id = this.getResources().getIdentifier("question_mark","drawable", this.getPackageName());
        ImageView imageView1 = findViewById(R.id.imageView);
        ImageView imageView2 = findViewById(R.id.imageView3);
        ImageView imageView3 = findViewById(R.id.imageView2);

        imageView1.setImageResource(id);
        imageView2.setImageResource(id);
        imageView3.setImageResource(id);





    }//Fin du onClick pour nouvelle partie
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------




}
