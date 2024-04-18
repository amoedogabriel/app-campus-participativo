package com.example.coleodegibis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coleodegibis.controllers.GibiControllerBD;
import com.example.coleodegibis.controllers.GibiControllerVetor;
import com.example.coleodegibis.entities.Gibi;
import com.example.coleodegibis.interfaces.IGibiController;

public class CadastroGibiActivity extends AppCompatActivity {

    EditText etNomeRevista;
    EditText etTituloRevista;
    EditText etNumeroRevista;
    EditText etEditoraRevista;
    IGibiController gibiController;
    ImageButton buttonSave;
    ImageButton buttonDelete;
    long i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNomeRevista = findViewById(R.id.editTextRevista);
        etTituloRevista = findViewById(R.id.editTextTitulo);
        etNumeroRevista = findViewById(R.id.editTextNumero);
        etEditoraRevista = findViewById(R.id.editTextEditora);
        buttonSave = findViewById(R.id.imageButtonSave);
        buttonDelete = findViewById(R.id.imageButtonDelete);

        //gibiController = GibiControllerVetor.getInstance();
        gibiController = new GibiControllerBD(this);

        Intent intent = getIntent();
        if(intent.hasExtra("i")) {
            i = intent.getLongExtra("i", -1);
            Gibi gibi = gibiController.read(i);
            etNomeRevista.setText(gibi.getNome());
            etTituloRevista.setText(gibi.getTitulo());
            etNumeroRevista.setText(gibi.getNumero());
            etEditoraRevista.setText(gibi.getEditora());
            buttonDelete.setVisibility(View.VISIBLE);
            buttonSave.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    gibiController.update(i,obterGibiTela());
                }
            });
        }
        else{
            buttonDelete.setVisibility(View.INVISIBLE);
            buttonSave.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    gibiController.create(obterGibiTela());
                }
            });
        }


    }

    public void excluirGibi(View view){
        gibiController.delete(i);
        limparGibiTela(view);
    }

    private Gibi obterGibiTela(){
        Gibi gibi = new Gibi();
        gibi.setTitulo(etTituloRevista.getText().toString());
        gibi.setNome(etNomeRevista.getText().toString());
        gibi.setNumero(etNumeroRevista.getText().toString());
        gibi.setEditora(etEditoraRevista.getText().toString());
        return gibi;
    }

    public void limparGibiTela(View view){
        etNomeRevista.setText("");
        etTituloRevista.setText("");
        etNumeroRevista.setText("");
        etEditoraRevista.setText("");
    }
}