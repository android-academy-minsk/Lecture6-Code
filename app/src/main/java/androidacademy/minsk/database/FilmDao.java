package androidacademy.minsk.database;

import java.util.List;

import androidacademy.minsk.models.Film;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface FilmDao {

    @Query("SELECT * FROM film")
    List<Film> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Film... films);

    @Delete
    void delete(Film film);

    @Query("DELETE FROM film")
    void deleteAll();

    @Query("SELECT * FROM film WHERE title LIKE :title LIMIT 1")
    Film findByName(String title);
}
