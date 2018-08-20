package androidacademy.minsk;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidacademy.minsk.models.Film;
import androidacademy.minsk.network.NetworkManager;
import androidacademy.minsk.network.StarWarsDataCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements StarWarsDataCallback {
    private static final String KEY_FILM_LIST = "film_list";

    private ArrayList<Film> filmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            filmList = savedInstanceState.getParcelableArrayList(KEY_FILM_LIST);
            updateUI(filmList);
        } else {
            NetworkManager.getInstance().getData(this);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(KEY_FILM_LIST, filmList);
    }

    @Override
    public void onDataReady(final List<Film> films) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                filmList = new ArrayList<>(films);
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
    private void updateUI(List<Film> films) {
        findViewById(R.id.pb_am_loading).setVisibility(View.GONE);

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        MyAdapter adapter = new MyAdapter(films, MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    @MainThread
    private void showToast(String message) {
        Toast.makeText(MainActivity.this, "Unable to download data with following reason: " + message, Toast.LENGTH_LONG).show();
    }
}
