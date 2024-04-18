package com.example.coleodegibis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.coleodegibis.controllers.GibiControllerBD;
import com.example.coleodegibis.controllers.GibiControllerVetor;
import com.example.coleodegibis.database.GibiDAO;
import com.example.coleodegibis.entities.Gibi;
import com.example.coleodegibis.interfaces.IGibiController;

public class ActivityVisualizar extends AppCompatActivity {

    private ListView listViewGibis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        //IGibiController gibiController = GibiControllerVetor.getInstance();
        IGibiController gibiController = new GibiControllerBD(this);

        listViewGibis = findViewById(R.id.listViewGibis);
        /*
        ArrayAdapter<Gibi> arrayAdapterGibis = new ArrayAdapter<Gibi>(this,
                android.R.layout.simple_list_item_1,
                gibiController.read());
        listViewGibis.setAdapter(arrayAdapterGibis);
         */
        SimpleCursorAdapter cursorAdapterGibis = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                gibiController.getCursor(),
                new String[] {"titulo", "numero"},
                new int[] {android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
                );
        listViewGibis.setAdapter(cursorAdapterGibis);

        listViewGibis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), CadastroGibiActivity.class);
                intent.putExtra("i", (long) l);
                startActivity(intent);
            }
        });
    }

    public void adicionarGibi(View view){
        Intent intent = new Intent(this, CadastroGibiActivity.class);
        startActivity(intent);
    }
}