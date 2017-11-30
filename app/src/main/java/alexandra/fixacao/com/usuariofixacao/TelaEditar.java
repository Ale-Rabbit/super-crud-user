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
 * Created by alexandra on 29/11/17.
 */

public class TelaEditar extends Activity {

    private EditText nome, login, senha;
    private Button cadastra, excluir;
    private Usuario usuario;
    private UsuarioConsumer usuarioConsumer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        inicializaComponentes();

        this.usuario = (Usuario)getIntent().getExtras().getSerializable("usuario");

        this.cadastra.setText("EDITAR");

        this.nome.setText(usuario.getNome());
        this.login.setText(usuario.getLogin());
        this.senha.setText(usuario.getSenha());

        this.cadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario.setNome(nome.getText().toString());
                usuario.setLogin(login.getText().toString());
                usuario.setSenha(senha.getText().toString());

                usuarioConsumer.putEditar(usuario).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Intent IrTelaLogado = new Intent(TelaEditar.this, TelaLogado.class);
                            startActivity(IrTelaLogado);
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(TelaEditar.this, "Falha ao atualizar!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        this.excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuarioConsumer.delete(usuario.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(TelaEditar.this, "Usuário excluído com sucesso!", Toast.LENGTH_LONG).show();
                            Intent  voltar = new Intent(TelaEditar.this, TelaLogin.class);
                            startActivity(voltar);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(TelaEditar.this, "Erro ao excluir", Toast.LENGTH_LONG).show();
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
        this.excluir = (Button) findViewById(R.id.buttonExcluir);
        excluir.setVisibility(View.VISIBLE);

        this.usuario = new Usuario();
        this.usuarioConsumer = new UsuarioConsumer();
    }
}
