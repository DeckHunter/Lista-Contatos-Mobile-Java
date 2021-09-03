package com.example.af_listadecontatos_2021.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.af_listadecontatos_2021.R;
import com.example.af_listadecontatos_2021.adapter.ContatoAdpter;
import com.example.af_listadecontatos_2021.helper.ContatosDAO;
import com.example.af_listadecontatos_2021.helper.DbHelper;
import com.example.af_listadecontatos_2021.helper.RecyclerItemClickListener;
import com.example.af_listadecontatos_2021.model.Contatos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContatoAdpter contatoAdpter;
    private List<Contatos> listaContatos = new ArrayList<>();
    private Contatos contatoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Configurando meu recycler
        recyclerView = findViewById(R.id.recyclerViewContatos);

        DbHelper db = new DbHelper(getApplicationContext());

        //Add evento de click
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Editar Contato
                        Contatos contatoSelecionado = listaContatos.get(position);

                        Intent intent = new Intent(MainActivity.this,AddContatoActivity.class);

                        intent.putExtra("ContatoSelecionada", contatoSelecionado);

                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        //Recuperar contato pra deletar
                        contatoSelecionado = listaContatos.get(position);

                        //Deletar Contato
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Confirmar Exclusão");
                        builder.setMessage("Deseja Excuir o Contato "+ contatoSelecionado.getNomeContato() + " ?");

                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ContatosDAO contatosDAO = new ContatosDAO(getApplicationContext());
                                if(contatosDAO.Deletar(contatoSelecionado)){
                                    carregarListaContatos();
                                    Toast.makeText(MainActivity.this, "Sucesso ao Deletar Contato", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this, "Erro ao Deletar Contato", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder.setNegativeButton("Não", null);

                        //Exibir o Dialog
                        builder.create();
                        builder.show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),AddContatoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void carregarListaContatos(){

        //Listar Tarefas
        ContatosDAO contatosDAO = new ContatosDAO(getApplicationContext());
        listaContatos = contatosDAO.Listar();

        //Configurar Adapter
        contatoAdpter = new ContatoAdpter(listaContatos);

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(contatoAdpter);
    }

    @Override
    protected void onStart() {
        carregarListaContatos();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
