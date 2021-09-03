package com.example.af_listadecontatos_2021.adapter;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.af_listadecontatos_2021.R;
import com.example.af_listadecontatos_2021.model.Contatos;

import java.util.List;

public class ContatoAdpter extends RecyclerView.Adapter<ContatoAdpter.MyViewHolder> {

    private List<Contatos> listaContatos;

    public ContatoAdpter(List<Contatos> lista) {
        this.listaContatos = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.lista_contato_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Contatos contatos = listaContatos.get(position);
        holder.contato.setText(contatos.getNomeContato());

    }

    @Override
    public int getItemCount() {
        return this.listaContatos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView contato;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            contato = itemView.findViewById(R.id.nomeContato);
        }
    }
}
