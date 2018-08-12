package androidacademy.minsk.database;

import java.util.List;

import androidacademy.minsk.models.Film;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FilmDao {

    @Query("SELECT * FROM film")
    List<Film> getAll();

    @Insert
    void insertAll(Film... films);

    @Delete
    void delete(Film film);

    @Query("DELETE FROM film")
    void deleteAll();
}
