package com.example.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.databinding.AnimalsViewHolderBinding;

import java.util.ArrayList;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.MyViewHolder> {
    private List<Animal> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public AnimalAdapter(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AnimalsViewHolderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.animals_view_holder, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateAnimalList(List<Animal> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        AnimalsViewHolderBinding binding;

        public MyViewHolder(@NonNull AnimalsViewHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind() {
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.callAnimalDetails(list.get(getAdapterPosition()));
                }
            });
            binding.setAnimal(list.get(getAdapterPosition()));
        }
    }

    interface OnItemClickListener{
        void callAnimalDetails(Animal animal);
    }
}

