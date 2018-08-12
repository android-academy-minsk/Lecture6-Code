package androidacademy.minsk.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface StarWarsApi {
    @GET("films/")
    Call<FilmsResponse> listFilms();

    @GET
    @Headers("Ocp-Apim-Subscription-Key: cb332be1249945fe9deb9c048487c6e7")
    Call<ImageSearchResponse> filmPicture(@Url String url);
}
