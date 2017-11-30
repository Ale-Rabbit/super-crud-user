package consumer;

import java.util.List;

import pojo.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by alexandra on 28/11/17.
 */

public interface IUsuarioService {
    static final String URL_BASE = "http://192.168.1.6:8080/";

    // -------------------Cadastrar um Usuario com login e senha-------------------------------------------
    @POST("usuario/{login}/{senha}")
    Call<Usuario> postAutentica(@Path("login") String login, @Path("senha") String senha);

    // -------------------Cadastrar um Usuario-------------------------------------------
   @POST("usuario/")
    Call<Usuario> postCadastrar(@Body Usuario usuario);

    // -------------------Atualiza um Usuário-------------------------------------------
    @PUT("usuario/")
    Call<Void> putEditar(@Body Usuario usuario);

    // -------------------Retorna todos os Usuário-------------------------------------------
    @GET("usuario/")
    Call<List<Usuario>> buscar();

    // -------------------Exclui um Usuario-------------------------------------------
    @DELETE("usuario/{id}")
    Call<Void> delete(@Path("id") long id);


}
