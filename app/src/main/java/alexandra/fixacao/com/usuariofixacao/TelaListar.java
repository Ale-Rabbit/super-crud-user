package alexandra.fixacao.com.usuariofixacao;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import consumer.UsuarioConsumer;
import pojo.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexandra on 29/11/17.
 */

public class TelaListar extends ListActivity {

    private List<Usuario> usuarios;
    private UsuarioConsumer usuarioConsumer;
    private Usuario usuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.usuarioConsumer = new UsuarioConsumer();
        this.usuarioConsumer.buscar().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()){
                    usuarios = response.body();
                    // para preencher a tela
                    ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(TelaListar.this,android.R.layout.simple_list_item_1, usuarios);
                    setListAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(TelaListar.this, "Falha em buscar usuários", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent irTelaEditar = new Intent(this, TelaEditar.class);
        Bundle paramentros = new Bundle();
        paramentros.putSerializable("usuario", usuarios.get(position));
        irTelaEditar.putExtras(paramentros);
        startActivity(irTelaEditar);

    }
}
