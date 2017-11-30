package alexandra.fixacao.com.usuariofixacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import consumer.UsuarioConsumer;
import pojo.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexandra on 28/11/17.
 */

public class TelaCadastro extends Activity {
    private EditText nome, login, senha;
    private Button cadastra;
    private Usuario usuario;
    private UsuarioConsumer usuarioConsumer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        inicializaComponentes();

        this.cadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario.setNome(nome.getText().toString());
                usuario.setLogin(login.getText().toString());
                usuario.setSenha(senha.getText().toString());

                // chama método cadastrar

                usuarioConsumer.postCadastra(usuario).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if(response.isSuccessful()){
                            usuario = response.body();
                            Intent irTelaInicial = new Intent(TelaCadastro.this, TelaLogado.class);
                            // envia dados de novo usuário
                            Bundle parametros = new Bundle();
                            parametros.putSerializable("usuario",usuario);
                            irTelaInicial.putExtras(parametros);
                            startActivity(irTelaInicial);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(TelaCadastro.this, "Falha em cadastrar", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });
    }


    private void inicializaComponentes(){
        this.nome = (EditText) findViewById(R.id.editTextNome);
        this.login = (EditText) findViewById(R.id.editTextLogin);
        this.senha = (EditText) findViewById(R.id.editTextSenha);
        this.cadastra = (Button) findViewById(R.id.buttonCadastrar);

        this.usuario = new Usuario();
        this.usuarioConsumer = new UsuarioConsumer();
    }
}
