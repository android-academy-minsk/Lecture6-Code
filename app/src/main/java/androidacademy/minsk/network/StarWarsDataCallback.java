package androidacademy.minsk.network;

import androidacademy.minsk.models.Film;

import java.util.List;

public interface StarWarsDataCallback {

    void onDataReady(List<Film> films);

    void onError(String message);
}
