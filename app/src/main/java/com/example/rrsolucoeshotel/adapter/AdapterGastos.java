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

public class AdapterGastos extends RecyclerView.Adapter<AdapterGastos.MinhaViewHolder> {

    private List<DadosHospede> listaDadosHospede;

    public AdapterGastos(List<DadosHospede> lista) {
        this.listaDadosHospede = lista;
    }

    @NonNull
    @Override
    // Configura o layout e a view da lista de dados do hospede
    public MinhaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista_gastos, parent, false);

        return new MinhaViewHolder(itemLista);
    }

    @Override
    // Cria a lista com os itens
    public void onBindViewHolder(@NonNull MinhaViewHolder holder, int position) {

        DadosHospede dadosHospede = listaDadosHospede.get(position);

        holder.dadosDescricao.setText(dadosHospede.getNomeProduto());
        holder.dadosValor.setText("R$: " + dadosHospede.getValor_Produto());
        holder.dadosQuantidade.setText(dadosHospede.getQuantidade());
        holder.dadosVTotal.setText("R$: " + dadosHospede.getValor_Total());
    }

    @Override
    // Cria a contagem de quantos itens vão ser apresentados na tela usando o tamanho da lista
    public int getItemCount() {
        return this.listaDadosHospede.size();
    }

    public class MinhaViewHolder extends RecyclerView.ViewHolder{

        TextView dadosDescricao, dadosQuantidade, dadosValor, dadosVTotal;

        public MinhaViewHolder(@NonNull View itemView) {
            super(itemView);

            dadosDescricao = itemView.findViewById(R.id.txtDescricao);
            dadosValor = itemView.findViewById(R.id.txtPreco);
            dadosQuantidade = itemView.findViewById(R.id.txtQuantidade);
            dadosVTotal = itemView.findViewById(R.id.txtV_total);
        }
    }
}
