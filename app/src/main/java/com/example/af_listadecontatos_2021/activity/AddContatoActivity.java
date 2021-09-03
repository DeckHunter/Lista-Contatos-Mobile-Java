package com.example.af_listadecontatos_2021.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.af_listadecontatos_2021.R;
import com.example.af_listadecontatos_2021.helper.ContatosDAO;
import com.example.af_listadecontatos_2021.model.Contatos;
import com.google.android.material.textfield.TextInputEditText;

public class AddContatoActivity extends AppCompatActivity {

    private TextInputEditText nome;
    private TextInputEditText apelido;
    private EditText email;
    private TextInputEditText numero;
    private TextInputEditText grupo;

    private Contatos contatoAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contato);

        nome = findViewById(R.id.textNome);
        apelido = findViewById(R.id.textApelido);
        email = findViewById(R.id.textEmail);
        numero = findViewById(R.id.TextNumero);
        grupo = findViewById(R.id.TextGrupo);

        //Recuperar Contato Selecionado
        contatoAtual = (Contatos) getIntent().getSerializableExtra("ContatoSelecionada");

        //Configurar Caixa De Texto
        if(contatoAtual != null){
            nome.setText(contatoAtual.getNomeContato());
            apelido.setText(contatoAtual.getApelidoContato());
            email.setText(contatoAtual.getEmailContato());
            numero.setText(contatoAtual.getNumeroContato());
            grupo.setText(contatoAtual.getGrupoContato());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_tarefa,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemSalvar:

                ContatosDAO contatosDAO = new ContatosDAO(getApplicationContext());

                if(contatoAtual != null) {
                    String nomeContato = nome.getText().toString();
                    String apelidoContato = apelido.getText().toString();
                    String emailContato = email.getText().toString();
                    String numeroContato = numero.getText().toString();
                    String grupoContato = grupo.getText().toString();

                    if(!nomeContato.isEmpty()) {

                        Contatos contatos = new Contatos();
                        contatos.setId(contatoAtual.getId());
                        contatos.setNomeContato(nomeContato);
                        contatos.setApelidoContato(apelidoContato);
                        contatos.setEmailContato(emailContato);
                        contatos.setGrupoContato(grupoContato);
                        contatos.setNumeroContato(numeroContato);

                        //Atualizar
                        if(contatosDAO.Atualizar(contatos)){
                            Toast.makeText(this, "Sucesso ao atualizar", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(this, "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                        }

                    }
                }else{//Salvar
                    String nomeContato = nome.getText().toString();
                    if(!nomeContato.isEmpty()) {
                        Contatos contatos = new Contatos();
                        contatos.setNomeContato(nome.toString());
                        contatos.setApelidoContato(apelido.toString());
                        contatos.setEmailContato(email.toString());
                        contatos.setGrupoContato(grupo.toString());
                        contatos.setNumeroContato(numero.toString());

                        //Executar Ação Para Salvar o Item

                        contatosDAO.Salvar(contatos);
                        finish();
                        break;
                    }else{
                        Toast.makeText(this, "Coloque um nome para o contato", Toast.LENGTH_SHORT).show();
                    }
                }
        }

        return super.onOptionsItemSelected(item);

    }
}
