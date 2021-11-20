package com.example.rrsolucoeshotel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.model.DadosHospede;

import java.util.List;

public class GastosAdapter extends RecyclerView.Adapter<GastosAdapter.MinhaViewHolder> {

    private List<DadosHospede> listaDadosH;

    public GastosAdapter(List<DadosHospede> lista) {
        this.listaDadosH = lista;
    }

    @NonNull
    @Override
    public MinhaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_gastos_activitie, parent, false);

        return new MinhaViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MinhaViewHolder holder, int position) {

        DadosHospede dadosHospede = listaDadosH.get(position);
        holder.dadosGasto.setText(dadosHospede.getDescricao() + " " + dadosHospede.getQuantidade()+
                " " + dadosHospede.getValor_Produto() + " "+ dadosHospede.getValor_Total()+ " "+
                dadosHospede.getData_Consumo());
    }

    @Override
    public int getItemCount() {
        return this.listaDadosH.size();
    }

    public class MinhaViewHolder extends RecyclerView.ViewHolder{

        TextView dadosGasto;

        public MinhaViewHolder(@NonNull View itemView) {
            super(itemView);

            dadosGasto = itemView.findViewById(R.id.txtListaGasto);
        }
    }
}
