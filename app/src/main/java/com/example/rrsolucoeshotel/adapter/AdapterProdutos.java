package com.example.rrsolucoeshotel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.model.ProdutosServicosHotel;

import java.util.List;

public class AdapterProdutos extends RecyclerView.Adapter<AdapterProdutos.MinhaViewHolder> {

    private List<ProdutosServicosHotel> listaProdutos;

    public AdapterProdutos(List<ProdutosServicosHotel> lista) { this.listaProdutos = lista; }

    @NonNull
    @Override
    // Configura o layout e a view da lista de produtos
    public MinhaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista_produtos, parent, false);

        return new MinhaViewHolder(itemLista);
    }

    @Override
    // Cria a lista com os diferentes itens
    public void onBindViewHolder(MinhaViewHolder holder, int position) {

        ProdutosServicosHotel produto = listaProdutos.get(position);

        holder.titulo.setText(produto.getTitulo());
        holder.valor.setText("R$ " + produto.getValor());
        holder.descrição.setText(produto.getDescricao());
    }

    @Override
    // Cria a contagem de quantos itens vão ser apresentados na tela usando o tamanho da lista
    public int getItemCount() { return listaProdutos.size(); }

    public class MinhaViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, descrição, valor;

        public MinhaViewHolder(View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.textProduto);
            valor = itemView.findViewById(R.id.textValor);
            descrição = itemView.findViewById(R.id.textDescricao);
        }
    }
}
