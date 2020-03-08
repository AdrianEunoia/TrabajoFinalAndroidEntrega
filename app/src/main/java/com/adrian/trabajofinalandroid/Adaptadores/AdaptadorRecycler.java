package com.adrian.trabajofinalandroid.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adrian.trabajofinalandroid.R;
import com.adrian.trabajofinalandroid.Utiles.Titulos;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.HolderAdapterPropio>{
    // Elementos
    private Context context;

    public AdaptadorRecycler(Context context, ArrayList<Titulos> listaTitulos) {
        this.context = context;
        this.listaTitulos = listaTitulos;
    }

    private ArrayList<Titulos> listaTitulos;

    @NonNull
    @Override
    public HolderAdapterPropio onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_layout, parent, false);
        return new HolderAdapterPropio(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAdapterPropio holder, int position) {
        holder.idtitulomostrar.setText(listaTitulos.get(position).getAutor());
        holder.idautormostrar.setText(listaTitulos.get(position).getAutor());
        holder.idlanzamientomostrar.setText(listaTitulos.get(position).getLanzamiento());
        holder.idpreciomostar.setText(listaTitulos.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return listaTitulos.size();
    }

    class HolderAdapterPropio extends RecyclerView.ViewHolder{
        // Elementos
        private TextView idtitulomostrar, idautormostrar, idlanzamientomostrar, idpreciomostar;
        // Getters
        public TextView getIdtitulomostrar() {
            return idtitulomostrar;
        }

        public TextView getIdautormostrar() {
            return idautormostrar;
        }

        public TextView getIdlanzamientomostrar() {
            return idlanzamientomostrar;
        }

        public TextView getIdpreciomostar() {
            return idpreciomostar;
        }

        public HolderAdapterPropio(@NonNull View itemView) {
            super(itemView);
            idtitulomostrar = itemView.findViewById(R.id.idtitulomostrar);
            idautormostrar = itemView.findViewById(R.id.idautormostrar);
            idlanzamientomostrar = itemView.findViewById(R.id.idañomostrar);
            idpreciomostar = itemView.findViewById(R.id.idpreciomostrar);
        }
    }

}
