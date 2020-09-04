package commitware.ayia.covid19global.ui.news;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import commitware.ayia.covid19global.Adapter.RecyclerViewAdapterNews;
import commitware.ayia.covid19global.BuildConfig;
import commitware.ayia.covid19global.Controllers.AppController;
import commitware.ayia.covid19global.R;
import commitware.ayia.covid19global.Utils.AppUtils;
import commitware.ayia.covid19global.interfaces.OnFragmentListener;
import commitware.ayia.covid19global.interfaces.RecyclerViewClickListener;
import commitware.ayia.covid19global.listeners.RecyclerViewTouchListener;
import commitware.ayia.covid19global.objects.News;
import commitware.ayia.covid19global.objects.NewsResponse;
import commitware.ayia.covid19global.service.Retrofit.ApiNewsEndpoint;
import commitware.ayia.covid19global.service.Retrofit.RetrofitServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentNews extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private OnFragmentListener mListener;
    private SwipeRefreshLayout swipe;
    private RecyclerViewAdapterNews adapter;
    private TextView tvEmpty;
    private RelativeLayout errorLayout;
    private ImageView errorImage;
    private TextView errorTitle, errorMessage;
    private Button btnRetry;
    private List<News> articles = new ArrayList<>();


    RecyclerView rv;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // newsViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(NewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        swipe = root.findViewById(R.id.swipeRefreshNews);
        rv = root.findViewById(R.id.newsRecycler);

        errorLayout = root.findViewById(R.id.errorLayout);
        errorImage =root. findViewById(R.id.errorImage);
        errorTitle = root.findViewById(R.id.errorTitle);
        errorMessage = root.findViewById(R.id.errorMessage);
        btnRetry = root.findViewById(R.id.btnRetry);

        adapter = new RecyclerViewAdapterNews(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);

        loadNewsData();
        rv.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), rv, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                final News news = adapter.getNews().get(position);
                startIntent(news);
            }
            @Override
            public void onLongClick(View view, final int position) {

            }
        }));
        return root;
    }


   /* private void loadNewsData() {

        NewsViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(NewsViewModel.class);
        viewModel.getNewsViewModel(false);
        refreshNews(true);
        viewModel.getNewsData().observe(getViewLifecycleOwner(), newsModels -> {
            if (newsModels == null) {
                tvEmpty.setVisibility(View.VISIBLE);
                refreshNews(false);
            }
            else  {
              // Toast.makeText(getActivity(), "nulldww"+newsModels.size(), Toast.LENGTH_LONG).show();
                if(newsModels.size()== 0)
                {
                    viewModel.getNewsViewModel(true);
                }
                else {
                    adapter.setNews(newsModels);
                    refreshNews(false);
                }

            }


        });
        swipe.setOnRefreshListener(this);
    }

    */



    private void loadNewsData() {
        errorLayout.setVisibility(View.GONE);
        refreshNews(true);
        Retrofit retrofit = RetrofitServiceApi.getRetrofitServiceNews();
        ApiNewsEndpoint endpoint = retrofit.create(ApiNewsEndpoint.class);
        String code = AppController.getInstance().getCode();
        Call<NewsResponse> call;

            if(code.equals(""))
            {
                call = endpoint.getNewsAll(AppUtils.getLanguage(),"health", BuildConfig.API_NEWS);
            }
            else {
                call = endpoint.getNews(code,"health", BuildConfig.API_NEWS);
            }

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                if (response.body() != null && response.isSuccessful() && response.body().getArticles() != null) {

                    if (!articles.isEmpty()) {
                        articles.clear();
                    }
                    articles = response.body().getArticles();
                    adapter = new RecyclerViewAdapterNews(getActivity());
                    rv.setAdapter(adapter);
                    adapter.setNews(articles);
                    refreshNews(false);
                }
                else {
                    refreshNews(false);
                    String errorCode;
                    switch (response.code()) {
                        case 404:
                            errorCode = "404 not found";
                            break;
                        case 500:
                            errorCode = "500 server broken";
                            break;
                        default:
                            errorCode = "unknown error";
                            break;
                    }

                    showErrorMessage(
                            R.drawable.no_result,
                            "No Result",
                            "Please Try Again!\n"+
                                    errorCode);
                }

            }

            @Override
            public void onFailure(@NonNull Call<NewsResponse> call, Throwable t) {
                refreshNews(false);
                showErrorMessage(
                        R.drawable.oops,
                        "Oops..",
                        "Network failure, Please Try Again");

            }
        });
        swipe.setOnRefreshListener(this);

    }

    private void refreshNews(boolean isRefresh) {
        if (isRefresh) {
            swipe.setRefreshing(true);
        } else {
            swipe.setRefreshing(false);
        }
    }




    @Override
    public void onRefresh() {

        loadNewsData();
    }

    private void showErrorMessage(int imageView, String title, String message){

        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
        }

        errorImage.setImageResource(imageView);
        errorTitle.setText(title);
        errorMessage.setText(message);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListener) {
            mListener = (OnFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // Now we can fire the event when the user selects something in the fragment
    private void startIntent(News news) {
        if (mListener != null) {
            mListener.getNewIntent(news);
        }
    }

}