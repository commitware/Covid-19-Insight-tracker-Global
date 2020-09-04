package commitware.ayia.covid19global.service.Retrofit;

import java.util.concurrent.TimeUnit;

import commitware.ayia.covid19global.Utils.AppUtils;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceApi {
    private static final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build();


    public static Retrofit getRetrofitServiceNews(){
        return new Retrofit.Builder()
                .baseUrl(AppUtils.BASE_URL_NEWS)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();
    }

}
