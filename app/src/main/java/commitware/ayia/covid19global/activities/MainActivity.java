package commitware.ayia.covid19global.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import commitware.ayia.covid19global.R;
import commitware.ayia.covid19global.interfaces.OnFragmentListener;
import commitware.ayia.covid19global.model.News;

import static commitware.ayia.covid19global.Utils.AppUtils.LIST_INTENT;
import static commitware.ayia.covid19global.Utils.AppUtils.LIST_REQUEST;
import static commitware.ayia.covid19global.Utils.AppUtils.LIST_TYPE;
import static commitware.ayia.covid19global.Utils.AppUtils.LIST_TYPE_SERVER;
import static commitware.ayia.covid19global.Utils.AppUtils.SLIDER_INTENT;

public class MainActivity extends AppCompatActivity implements OnFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        AppBarConfiguration appBarConfiguration;

            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_dashboard, R.id.navigation_info, R.id.navigation_news)
                    .build();
            navView.getMenu().removeItem(R.id.navigation_helpline);



        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(navView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {


        });
    }




    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            Intent it = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(it);
            overridePendingTransition(0,0);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getListIntent(String intent, String arguement) {
        if (intent.equals(LIST_INTENT))
        {
            Intent it = new Intent(MainActivity.this, ListActivity.class);
            it.putExtra(LIST_REQUEST, arguement);
            it.putExtra(LIST_TYPE, LIST_TYPE_SERVER);
            startActivity(it);
            overridePendingTransition(0,0);
        }
        if (intent.equals(SLIDER_INTENT))
        {
            Intent it = new Intent(MainActivity.this, SliderActivity.class);
            it.putExtra("sliderRequest", arguement);
            startActivity(it);
            overridePendingTransition(0,0);
        }


    }

    @Override
    public void getNewIntent(News news) {
        Intent it = new Intent(MainActivity.this, NewsDetailsActivity.class);
        it.putExtra(NewsDetailsActivity.PARCELABLE_PARSING_DATA, news);
        startActivity(it);
        overridePendingTransition(0,0);
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

    }

}
