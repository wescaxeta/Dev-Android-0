package com.example.atividadebanco1.viewModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.atividadebanco1.FormActivity;
import com.example.atividadebanco1.MainActivity;
import com.example.atividadebanco1.R;
import com.example.atividadebanco1.modelo.Registro;

import static com.example.atividadebanco1.MainActivity.EDIT_REG_ACTIVITY_REQUEST_CODE;

public class RegistroViewHolder extends RecyclerView.ViewHolder {

    public TextView nome;
    public TextView pensamento;
    public ImageButton editButton;
    public ImageButton deleteButton;

    public RegistroViewHolder(View itemView) {
        super(itemView);

        nome = itemView.findViewById(R.id.text_nome);
        pensamento = itemView.findViewById(R.id.text_pensamento);
        editButton = itemView.findViewById(R.id.button_edit);
        deleteButton = itemView.findViewById(R.id.button_delete);
    }

    public void bind(Registro registro) {
        nome.setText(registro.getNome());
        pensamento.setText(registro.getPensamento());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), FormActivity.class);
                intent.putExtra("registro", registro);
                ((Activity)itemView.getContext()).startActivityForResult(intent, EDIT_REG_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    static RegistroViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new RegistroViewHolder(view);
    }
}
