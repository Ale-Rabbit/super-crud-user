package alexandra.fixacao.com.usuariofixacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import consumer.UsuarioConsumer;
import pojo.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


// tela inicial para logar
public class TelaLogin extends Activity {
    private TextView criar;
    private EditText login,senha;
    private Button logar;

    private Usuario usuario;
    private UsuarioConsumer usuarioConsumer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        this.inicializaComponentes();

        this.logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario.setLogin(login.getText().toString());
                usuario.setSenha(senha.getText().toString());
                //chamo método para autenticação
                chamaAutenticacaoWS(usuario.getLogin(), usuario.getSenha());

            }
        });

        this.criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // vai para tela de cadastro
                Intent telaCadastro = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivity(telaCadastro);
                finish();
            }
        });
    }

    private void chamaTelaLogado(){
        Intent telaLogado = new Intent(TelaLogin.this, TelaLogado.class);
        Bundle paramentros = new Bundle ();
        paramentros.putSerializable("usuario",usuario);
        telaLogado.putExtras(paramentros);
        startActivity(telaLogado);
        finish();
    }

    private Usuario chamaAutenticacaoWS(String login, String senha){
        // Implementa consumo do Web Service para a autenticação
        // enqueue é requisição assincrona (ganha velocidade). Que é basicamente uma requisição que não espera o retorno do WB. Ele faz tudo automático
        this.usuarioConsumer.postAutentica(login,senha).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    usuario = response.body();
                    chamaTelaLogado();
                } else{
                    try{
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(TelaLogin.this, jObjError.getString("errorMessage"), Toast.LENGTH_LONG).show();
                    } catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException E){
                        E.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(TelaLogin.this, "Algo deu errado", Toast.LENGTH_SHORT).show();
            }
        });

        return usuario;
    }


    private void inicializaComponentes(){
        this.criar = (TextView) findViewById(R.id.textViewCriar);
        this.login = (EditText) findViewById(R.id.editTextLogin);
        this.senha = (EditText) findViewById(R.id.editTextSenha);
        this.logar = (Button) findViewById(R.id.buttonEntrar);

        this.usuario = new Usuario();
        this.usuarioConsumer = new UsuarioConsumer();
    }
}
