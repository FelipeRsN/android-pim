package com.example.pim.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText nome;
    private EditText cpf;
    private EditText rua;
    private EditText numero;
    private EditText cep;
    private EditText bairro;
    private EditText cidade;
    private EditText estado;
    private EditText ddd;
    private EditText telefone;
    private Button salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Nova Pessoa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nome = findViewById(R.id.nome);
        cpf = findViewById(R.id.cpf);
        rua = findViewById(R.id.rua);
        numero = findViewById(R.id.numero);
        cep = findViewById(R.id.cep);
        bairro = findViewById(R.id.bairro);
        cidade = findViewById(R.id.cidade);
        estado = findViewById(R.id.estado);
        ddd = findViewById(R.id.ddd);
        telefone = findViewById(R.id.telefone);
        salvar = findViewById(R.id.salvar);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaDados();
            }
        });
    }

    private void validaDados() {
        if (nome.getText().toString().equals("")) {
            Toast.makeText(this, "Nome não pode ser vazio", Toast.LENGTH_SHORT).show();
        } else if (cpf.getText().toString().equals("")) {
            Toast.makeText(this, "CPF não pode ser vazio", Toast.LENGTH_SHORT).show();
        } else if (rua.getText().toString().equals("")) {
            Toast.makeText(this, "Rua não pode ser vazio", Toast.LENGTH_SHORT).show();
        } else if (numero.getText().toString().equals("")) {
            Toast.makeText(this, "Número não pode ser vazio", Toast.LENGTH_SHORT).show();
        } else if (cep.getText().toString().equals("")) {
            Toast.makeText(this, "CEP não pode ser vazio", Toast.LENGTH_SHORT).show();
        } else if (bairro.getText().toString().equals("")) {
            Toast.makeText(this, "Bairro não pode ser vazio", Toast.LENGTH_SHORT).show();
        } else if (cidade.getText().toString().equals("")) {
            Toast.makeText(this, "Cidade não pode ser vazio", Toast.LENGTH_SHORT).show();
        } else if (estado.getText().toString().equals("")) {
            Toast.makeText(this, "Estado não pode ser vazio", Toast.LENGTH_SHORT).show();
        } else if (ddd.getText().toString().equals("")) {
            Toast.makeText(this, "DDD não pode ser vazio", Toast.LENGTH_SHORT).show();
        } else if (telefone.getText().toString().equals("")) {
            Toast.makeText(this, "Telefone não pode ser vazio", Toast.LENGTH_SHORT).show();
        } else {
            devolvePessoa();
        }
    }

    private void devolvePessoa() {
        Intent intent = new Intent();

        intent.putExtra("nome", nome.getText().toString());
        intent.putExtra("cpf", cpf.getText().toString());
        intent.putExtra("rua", rua.getText().toString());
        intent.putExtra("numero", numero.getText().toString());
        intent.putExtra("cep", cep.getText().toString());
        intent.putExtra("bairro", bairro.getText().toString());
        intent.putExtra("cidade", cidade.getText().toString());
        intent.putExtra("estado", estado.getText().toString());
        intent.putExtra("dddTelefone", ddd.getText().toString());
        intent.putExtra("telefone", telefone.getText().toString());

        setResult(Activity.RESULT_OK, intent);
        finishAfterTransition();
    }
}