package com.sdainfo.bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btSalvar = findViewById(R.id.button_salvar);
        EditText etNome = findViewById(R.id.et_nome);
        TextView tvRecebeNome = findViewById(R.id.textView_recebe_nome);

        try {

            //cria banco
            SQLiteDatabase bancoDados = openOrCreateDatabase("bancoDados", MODE_PRIVATE, null);

            //cria tabela
            bancoDados.execSQL("create table if not exists pessoas (id integer primary key autoincrement not null, nome varchar , idade int(3))");

            //inserir dados
            //bancoDados.execSQL("insert into pessoas(nome, idade) values ('Sérgio', 46)");
            //bancoDados.execSQL("insert into pessoas(nome, idade) values ('Isadora', 8)");
            //bancoDados.execSQL("insert into pessoas(nome, idade) values ('Eduarda', 13)");
            //bancoDados.execSQL("insert into pessoas(nome, idade) values ('Mariane', 19)");

            //atualizar tabela
            bancoDados.execSQL("update pessoas set idade = 19 where nome = 'Mariane'");

            //apagar tabela
            bancoDados.execSQL("drop table pessoas");

            //apagar registro
            bancoDados.execSQL("delete from pessoas where id = 2");

            //ler os dados tudo
            String  consulta = "select nome, idade from pessoas";

            //ler local especifico
            String  consulta1 = "select nome, idade from pessoas where nome = 'Sérgio'";

            //ler onde tem
            String  consulta2 = "select nome, idade from pessoas where nome = 'Sérgio' and idade = 30";

            //ler onde tem
            String  consulta3 = "select nome, idade from pessoas where idade >= 30 or idade = 18";

           // ler onde tenha
            String  consulta4 = "select nome, idade from pessoas where idade in(18,35)";

            // ler por
            String  consulta5 = "select nome, idade from pessoas where nome in('Isadora','Sérgio')";

            // ler entre
            String  consulta6 = "select nome, idade from pessoas where idade between 30 and 35";

            // ler como
            String  consulta7 = "select nome, idade from pessoas where nome like 'Sérgio'";

            // ler tenha antes
            String  consulta8 = "select nome, idade from pessoas where nome like 'Sér%'";

            // ler tenha depois
            String  consulta9 = "select nome, idade from pessoas where nome like '%Sér'";

            // ler tenha qualquer lugar
            String  consulta10 = "select nome, idade from pessoas where nome like '%Sér'";

            // ler tenha parametro
            String buscar = "onde buscra";
            String  consulta11 = "select nome, idade from pessoas where nome like '% " + buscar +"";

            //ordenar
            String  consulta12 = "select nome, idade from pessoas where idade = 30 order by idade asc ";

            //ordenar
            String  consulta13 = "select nome, idade from pessoas where idade = 30 order by nome desc";

            //ordenar e limitar
            //String  consulta14 = "select nome, idade from pessoas where idade = 30 order by nome desc limit 2";


            Cursor cursor = bancoDados.rawQuery(consulta13, null);



            //verificar indices da tabel
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while (cursor != null){

                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);

                System.out.println("Nome: " +  nome + " : " + idade);
                Log.i(nome, idade);

                cursor.moveToNext();
            }


        }catch (Exception e){
            e.printStackTrace();
        }




        }
    }
