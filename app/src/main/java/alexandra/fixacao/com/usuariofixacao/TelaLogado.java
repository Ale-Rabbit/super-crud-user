package alexandra.fixacao.com.usuariofixacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pojo.Usuario;

/**
 * Created by alexandra on 29/11/17.
 */

public class TelaLogado extends Activity {
    private Usuario usuario;
    private Button editar, listar, sair;
    private TextView welcome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_logado);

        iniciaCompornentes();

        // Mensagem de Bem vindo + nome de usuário
        Bundle parametros = getIntent().getExtras();
        if(parametros!=null){
            this.usuario = (Usuario) parametros.getSerializable("usuario");
            welcome.setText("Bem vindo "+this.usuario.getNome());

        }

        // tem problema
        this.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irTelaEditar = new Intent(TelaLogado.this, TelaEditar.class);
                Bundle paramentros = new Bundle();
                paramentros.putSerializable("usuario", usuario);
                irTelaEditar.putExtras(paramentros);
                startActivity(irTelaEditar);
            }
        });

        this.listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irParaLista = new Intent(TelaLogado.this, TelaListar.class);
                startActivity(irParaLista);
            }
        });

        this.sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sair = new Intent(TelaLogado.this, TelaLogin.class);
                startActivity(sair);
                finish();
            }
        });


    }

    private void iniciaCompornentes(){
        this.editar = (Button) findViewById(R.id.buttonEditar);
        this.listar = (Button) findViewById(R.id.buttonListar);
        this.sair = (Button) findViewById(R.id.buttonDeslogar);
        this.welcome = (TextView) findViewById(R.id.textViewWelcome);

        this.usuario = new Usuario();
    }
}
