package com.example.atividadebanco1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.example.atividadebanco1.R;
import com.example.atividadebanco1.modelo.Registro;

public class FormActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editPensamento;
    private Button buttonSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editNome = findViewById(R.id.edit_nome);
        editPensamento = findViewById(R.id.edit_pensamento);
        buttonSave = findViewById(R.id.button_save);

        Registro reg = (Registro) getIntent().getSerializableExtra("registro");
        if (reg != null) {
            editNome.setText(reg.getNome());
            editPensamento.setText(reg.getPensamento());
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();

                if (TextUtils.isEmpty(editNome.getText()) || TextUtils.isEmpty(editPensamento.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String nome = editNome.getText().toString();
                    String pensamento = editPensamento.getText().toString();

                    if (reg != null) {
                        replyIntent.putExtra("id", reg.getId());
                    }

                    replyIntent.putExtra("nome", nome);
                    replyIntent.putExtra("pensamento", pensamento);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
