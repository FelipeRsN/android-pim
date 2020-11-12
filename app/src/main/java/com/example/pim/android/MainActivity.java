package com.example.pim.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView nome;
    private TextView cpf;
    private TextView rua;
    private TextView cep;
    private TextView bairro;
    private TextView cidade;
    private TextView estado;
    private TextView dddTelefone;
    private TextView telefone;
    private FloatingActionButton adicionarNovo;
    private Button buscar;
    private Button excluir;
    private EditText buscaPessoa;

    private ArrayList<Pessoa> listDePessoas = new ArrayList<>();
    private Pessoa pessoaBuscada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.nome);
        cpf = findViewById(R.id.cpf);
        rua = findViewById(R.id.rua);
        cep = findViewById(R.id.cep);
        bairro = findViewById(R.id.bairro);
        cidade = findViewById(R.id.cidade);
        estado = findViewById(R.id.estado);
        dddTelefone = findViewById(R.id.dddTelefone);
        telefone = findViewById(R.id.telefone);
        adicionarNovo = findViewById(R.id.adicionarNovo);
        buscar = findViewById(R.id.buscar);
        excluir = findViewById(R.id.excluir);
        buscaPessoa = findViewById(R.id.buscaPessoa);

        adicionarNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 333);
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buscaPessoa.getText().equals("")) {
                    Toast.makeText(MainActivity.this, "Digite o cpf", Toast.LENGTH_SHORT).show();
                    escondeDados();
                } else {
                    buscaPessoa(buscaPessoa.getText().toString());
                }
            }
        });

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
                dialog.setTitle("Excluir pessoa");
                dialog.setMessage("Tem certeza que deseja excluir esta pessoa?");
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Excluir",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                excluiPessoa();
                            }
                        });
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog.show();
            }
        });
    }

    private void buscaPessoa(String cpf) {
        boolean achouPessoa = false;
        for (int i = 0; i < listDePessoas.size(); i++) {
            Pessoa pessoa = listDePessoas.get(i);
            if (pessoa.getCpf().equals(cpf)) {
                achouPessoa = true;
                preencheDados(pessoa);
                break;
            }
        }

        if (!achouPessoa) {
            Toast.makeText(this, "Nenhuma pessoa encontrada", Toast.LENGTH_SHORT).show();
            escondeDados();
        }
    }

    private void preencheDados(Pessoa pessoa) {
        pessoaBuscada = pessoa;
        nome.setText("Nome: " + pessoa.getNome());
        cpf.setText("CPF: " + pessoa.getCpf());
        rua.setText("Endereço: " + pessoa.getRua() + ", " + pessoa.getNumero());
        cep.setText("CEP: " + pessoa.getCEP());
        bairro.setText("Bairro: " + pessoa.getBairro());
        cidade.setText("Cidade: " + pessoa.getCidade());
        estado.setText("Estado: " + pessoa.getEstado());
        dddTelefone.setText("DDD: " + pessoa.getDddTelefone());
        telefone.setText("Telefone: " + pessoa.getTelefone());

        mostraDados();
    }

    private void mostraDados() {
        nome.setVisibility(View.VISIBLE);
        cpf.setVisibility(View.VISIBLE);
        rua.setVisibility(View.VISIBLE);
        cep.setVisibility(View.VISIBLE);
        bairro.setVisibility(View.VISIBLE);
        cidade.setVisibility(View.VISIBLE);
        estado.setVisibility(View.VISIBLE);
        dddTelefone.setVisibility(View.VISIBLE);
        telefone.setVisibility(View.VISIBLE);
        excluir.setVisibility(View.VISIBLE);
    }

    private void escondeDados() {
        nome.setVisibility(View.GONE);
        cpf.setVisibility(View.GONE);
        rua.setVisibility(View.GONE);
        cep.setVisibility(View.GONE);
        bairro.setVisibility(View.GONE);
        cidade.setVisibility(View.GONE);
        estado.setVisibility(View.GONE);
        dddTelefone.setVisibility(View.GONE);
        telefone.setVisibility(View.GONE);
        excluir.setVisibility(View.GONE);
    }

    private void excluiPessoa() {
        for (int i = 0; i < listDePessoas.size(); i++) {
            Pessoa pessoa = listDePessoas.get(i);
            if (pessoa.getCpf().equals(pessoaBuscada.getCpf())) {
                listDePessoas.remove(i);
                Toast.makeText(this, "Pessoa excluida", Toast.LENGTH_SHORT).show();
                escondeDados();
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 333 && data != null) {
            Pessoa novaPessoa = new Pessoa();
            novaPessoa.setNome(data.getStringExtra("nome"));
            novaPessoa.setCpf(data.getStringExtra("cpf"));
            novaPessoa.setRua(data.getStringExtra("rua"));
            novaPessoa.setCEP(data.getStringExtra("cep"));
            novaPessoa.setNumero(data.getStringExtra("numero"));
            novaPessoa.setBairro(data.getStringExtra("bairro"));
            novaPessoa.setCidade(data.getStringExtra("cidade"));
            novaPessoa.setEstado(data.getStringExtra("estado"));
            novaPessoa.setDddTelefone(data.getStringExtra("dddTelefone"));
            novaPessoa.setTelefone(data.getStringExtra("telefone"));

            listDePessoas.add(novaPessoa);
            Toast.makeText(this, "Nova pessoa adicionada a lista", Toast.LENGTH_SHORT).show();
        }
    }
}