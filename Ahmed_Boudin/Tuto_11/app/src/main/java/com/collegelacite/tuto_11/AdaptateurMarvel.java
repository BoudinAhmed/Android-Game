package com.collegelacite.tuto_11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mlavoi on 2018-04-02.
 */

public class AdaptateurMarvel extends BaseAdapter {
    private Context contexte;
    private LayoutInflater inflater;
    private ArrayList<Marvel> sourceDonnées;

    public AdaptateurMarvel(Context context, ArrayList<Marvel> items) {
        contexte = context;
        sourceDonnées = items;
        inflater = (LayoutInflater) contexte.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return sourceDonnées.size();    }

    @Override
    public Object getItem(int i) {
        return sourceDonnées.get(i);    }

    @Override
    public long getItemId(int i) {
        return i;    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = inflater.inflate(R.layout.marvel_grid_item, viewGroup, false);

        Marvel p = (Marvel) getItem(i);

        // On affiche le nom du personnage seulement s"il doit être dévoilé
        TextView nomTextView = rowView.findViewById(R.id.nomId);
        if (p.getDévoilé()) {
            nomTextView.setText(p.getNom());
        }
        else {
            nomTextView.setText(null);
        }

        // Afficher l'image du personnage
        int id = contexte.getResources().getIdentifier(p.getDrawable(),"drawable", contexte.getPackageName());
        ImageView imageView = rowView.findViewById(R.id.photoId);
        imageView.setImageResource(id);

        return rowView;
    }
}
