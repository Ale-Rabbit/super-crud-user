package consumer;

import java.util.ArrayList;
import java.util.List;

import pojo.Usuario;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexandra on 28/11/17.
 */

public class UsuarioConsumer  {
    List<Usuario> array = new ArrayList<Usuario>();

    private IUsuarioService usuarioService;
    private Retrofit retrofit;

    public UsuarioConsumer (){
        this.retrofit = new Retrofit.Builder().baseUrl(IUsuarioService.URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();
        this.usuarioService = retrofit.create(IUsuarioService.class);
    }

    public Call <Usuario> postAutentica(String login, String senha){
        return this.usuarioService.postAutentica(login, senha);
    }

    public Call <Usuario> postCadastra(Usuario usuario){
        return this.usuarioService.postCadastrar(usuario);
    }

    public Call <List<Usuario>> buscar(){
        return this.usuarioService.buscar();
    }

    public Call<Void> putEditar(Usuario usuario) {
        return this.usuarioService.putEditar(usuario);
    }

    public Call<Void> delete(long id) {
        return this.usuarioService.delete(id);
    }
}
