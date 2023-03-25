package com.example.atividadebanco1.viewModel;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.atividadebanco1.modelo.Registro;

public class RegistroListAdapter extends ListAdapter<Registro, RegistroViewHolder> {

    private RegistroViewModel mRegistroViewModel;

    public RegistroListAdapter(RegistroViewModel mRegistroViewModel) {
        super(DIFF_CALLBACK);

        this.mRegistroViewModel = mRegistroViewModel;
    }

    @Override
    public RegistroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RegistroViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RegistroViewHolder holder, int position) {
        Registro current = getItem(position);

        holder.nome.setText(current.getNome());
        holder.pensamento.setText(current.getPensamento());

        holder.deleteButton.setOnClickListener(view -> mRegistroViewModel.delete(current));

        holder.bind(current);
    }

    public static final DiffUtil.ItemCallback<Registro> DIFF_CALLBACK =
        new DiffUtil.ItemCallback<Registro>() {
            @Override
            public boolean areItemsTheSame(
                    @NonNull Registro oldReg, @NonNull Registro newReg) {
                return oldReg.getId() == newReg.getId();
            }
            @Override
            public boolean areContentsTheSame(
                    @NonNull Registro oldReg, @NonNull Registro newReg) {
                return oldReg.getNome() != null && oldReg.getNome().equals(newReg.getNome())
                        && oldReg.getPensamento() != null && oldReg.getPensamento().equals(newReg.getNome());
            }
        };
}