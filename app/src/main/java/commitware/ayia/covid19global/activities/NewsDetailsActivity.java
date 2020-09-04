package commitware.ayia.covid19global.activities;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import commitware.ayia.covid19global.R;
import commitware.ayia.covid19global.fragments.NewsDetailFragment;
import commitware.ayia.covid19global.interfaces.OnFragmentListenerNewsDetail;
import commitware.ayia.covid19global.objects.News;

public class NewsDetailsActivity extends AppCompatActivity implements OnFragmentListenerNewsDetail {
    public static final String PARCELABLE_PARSING_DATA = "parcelable_parsing_data" ;

    private void beginSliderTransaction(News news)
    {

        NewsDetailFragment fragment = NewsDetailFragment.newInstance(news);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment)
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        News model = getIntent().getParcelableExtra(PARCELABLE_PARSING_DATA);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
        beginSliderTransaction(model);

    }

    @Override
    public void webIntent(String url) {

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
