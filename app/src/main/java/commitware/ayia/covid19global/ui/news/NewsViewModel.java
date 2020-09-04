package commitware.ayia.covid19global.ui.news;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import commitware.ayia.covid19global.BuildConfig;
import commitware.ayia.covid19global.Controllers.AppController;
import commitware.ayia.covid19global.Utils.AppUtils;
import commitware.ayia.covid19global.objects.News;
import commitware.ayia.covid19global.objects.NewsResponse;
import commitware.ayia.covid19global.service.Retrofit.ApiNewsEndpoint;
import commitware.ayia.covid19global.service.Retrofit.RetrofitServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsViewModel extends ViewModel {


    private final MutableLiveData<List<News>> liveData = new MutableLiveData<>();

    public void getNewsViewModel(boolean isGetAll) {
        Retrofit retrofit = RetrofitServiceApi.getRetrofitServiceNews();
        ApiNewsEndpoint endpoint = retrofit.create(ApiNewsEndpoint.class);
        String code = AppController.getInstance().getCode();
        Call<NewsResponse> call;
        Response<NewsResponse> getResponse = null;

        if(isGetAll)
        {
            call = endpoint.getNews( code,"health", BuildConfig.API_NEWS);
        }
        else {
            if(code.equals(""))
            {
                call = endpoint.getNewsAll(AppUtils.getLanguage(),"health", BuildConfig.API_NEWS);
            }
            else {
                call = endpoint.getNews( code,"health", BuildConfig.API_NEWS);
            }
        }



        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                if (response.body() != null && response.isSuccessful() && response.body().getArticles() != null) {
                    liveData.setValue(response.body().getArticles());
                }

            }

            @Override
            public void onFailure(@NonNull Call<NewsResponse> call, Throwable t) {

            }
        });

    }

    public LiveData<List<News>> getNewsData() {
        return liveData;
    }
}