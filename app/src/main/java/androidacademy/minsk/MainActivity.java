package androidacademy.minsk;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidacademy.minsk.models.Film;
import androidacademy.minsk.network.NetworkManager;
import androidacademy.minsk.network.StarWarsDataCallback;

import java.util.List;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements StarWarsDataCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkManager.getInstance().getData(this);
    }

    @Override
    public void onDataReady(final List<Film> filmList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateUI(filmList);
            }
        });

    }


    @Override
    public void onError(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast(message);
            }
        });
    }


    @MainThread
    private void updateUI(List<Film> filmList) {
        findViewById(R.id.pb_am_loading).setVisibility(View.GONE);

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        MyAdapter adapter = new MyAdapter(filmList, MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    @MainThread
    private void showToast(String message) {
        Toast.makeText(MainActivity.this, "Unable to download data with following reason: " + message, Toast.LENGTH_LONG).show();
    }
}