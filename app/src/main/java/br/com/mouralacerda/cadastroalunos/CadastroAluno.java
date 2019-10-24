package br.com.mouralacerda.cadastroalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroAluno extends AppCompatActivity {

    Aluno a;

    Spinner cursos;
    EditText ra;
    EditText nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        cursos = findViewById(R.id.spnCurso);

        String[] strCursos = getResources().getStringArray(R.array.cursos);

        ArrayAdapter<String> adp = new ArrayAdapter<>
                (this, R.layout.support_simple_spinner_dropdown_item, strCursos);

        cursos.setAdapter(adp);


        ra = findViewById(R.id.edtRa);
        nome = findViewById(R.id.edtNome);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro_aluno, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuCancelar: {
                finish();
                break;
            }
            case R.id.menuSalvarAluno: {
                salvarAluno();
                finish();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void salvarAluno() {

        a = new Aluno();
        a.setRa(ra.getText().toString());
        a.setNome(nome.getText().toString());
        a.setCurso(cursos.getSelectedItem().toString());

        Toast.makeText(this, "Salvando aluno... " + a, Toast.LENGTH_SHORT).show();

        Intent i = new Intent();
        i.putExtra("aluno", a);
        setResult(1, i);
    }
}
