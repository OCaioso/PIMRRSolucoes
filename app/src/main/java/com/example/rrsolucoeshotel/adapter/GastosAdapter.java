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
        holder.dadosDescricao.setText(dadosHospede.getDescricao());
        holder.dadosValor.setText("R$: " + dadosHospede.getValor_Produto());
        holder.dadosQuantidade.setText(dadosHospede.getQuantidade());
        holder.dadosVTotal.setText("R$: " + dadosHospede.getValor_Total());
    }

    @Override
    public int getItemCount() {
        return this.listaDadosH.size();
    }

    public class MinhaViewHolder extends RecyclerView.ViewHolder{

        TextView dadosDescricao, dadosQuantidade, dadosValor, dadosVTotal;

        public MinhaViewHolder(@NonNull View itemView) {
            super(itemView);

            dadosDescricao = itemView.findViewById(R.id.txtDescricao);
            dadosValor = itemView.findViewById(R.id.txtValor);
            dadosQuantidade = itemView.findViewById(R.id.txtQuantidade);
            dadosVTotal = itemView.findViewById(R.id.txtV_total);
        }
    }
}
