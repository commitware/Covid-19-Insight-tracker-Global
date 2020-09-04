package commitware.ayia.covid19global.service.Retrofit;

import commitware.ayia.covid19global.Utils.AppUtils;
import commitware.ayia.covid19global.objects.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiNewsEndpoint {

    @GET(AppUtils.ENDPOINT_TOP_HEADLINE_NEWS)
    Call<NewsResponse> getNews(@Query("country") String country,
                               @Query("category") String category,
                               @Query("apiKey") String apiKey);

    @GET(AppUtils.ENDPOINT_TOP_HEADLINE)
    Call<NewsResponse> getNewsAll(@Query("language") String language,
                                  @Query("category") String category,
                                  @Query("apiKey") String apiKey);
}
