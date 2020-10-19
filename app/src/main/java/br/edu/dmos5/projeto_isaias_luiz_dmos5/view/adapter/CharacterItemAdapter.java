package br.edu.dmos5.projeto_isaias_luiz_dmos5.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.dmos5.projeto_isaias_luiz_dmos5.R;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.Character;

public class CharacterItemAdapter extends RecyclerView.Adapter<CharacterItemAdapter.ViewHolder> {

    private List<Character> list;

    private static RecyclerItemClickListener clickListener;

    public CharacterItemAdapter(@NonNull List<Character> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setClickListener(RecyclerItemClickListener clickListener) {
        CharacterItemAdapter.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_recycler_view_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onItemClick(getAdapterPosition());
        }
    }
}