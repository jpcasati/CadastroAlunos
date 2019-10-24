package br.com.mouralacerda.cadastroalunos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListagemAlunos extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    ListView listaAlunos;
    List<Aluno> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_alunos);

        alunos = new ArrayList<>();

        listaAlunos = findViewById(R.id.lstAlunos);

        listaAlunos.setOnItemLongClickListener(this);

        carregarLista();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);


        dialog.setTitle("Apagar Aluno");
        dialog.setMessage("Deseja apagar o Aluno "
                + alunos.get(position).getNome()
                + " ?");
        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alunos.remove(position);
                carregarLista();
            }
        });
        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Aluno a = (Aluno)data.getSerializableExtra("aluno");

        alunos.add(a);

        carregarLista();
    }

    private void carregarLista() {

        ArrayAdapter<Aluno> adp = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alunos);

        listaAlunos.setAdapter(adp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listagem_alunos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.menuFecharApp: {
                finish();
                break;
            }
            case R.id.menuNovoAluno: {
                Intent i = new Intent(this, CadastroAluno.class);
//                startActivity(i);
                startActivityForResult(i, 1);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }
}
