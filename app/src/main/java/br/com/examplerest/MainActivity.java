package br.com.examplerest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import br.com.examplerest.adapter.PhotoAdapter;
import br.com.examplerest.entity.Photo;
import br.com.examplerest.service.PhotoService;
import br.com.examplerest.service.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializa a Listview
        listPhotos = (ListView) findViewById(R.id.lstItens);

        //Cria o objeto de request
        PhotoService photoService = RetrofitService.getInstance().create(PhotoService.class);

        final WeakReference<Context> weakReference = new WeakReference<>(getBaseContext());

        //Efetua a chamada ao método find all
        photoService.findAll().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                //Inicia o adapter
                PhotoAdapter photoAdapter = new PhotoAdapter(response.body(), weakReference);

                //Seta o adapter na listview
                listPhotos.setAdapter(photoAdapter);

            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Não foi possível carregar os dados! ", Toast.LENGTH_LONG).show();
            }
        });
    }


}
