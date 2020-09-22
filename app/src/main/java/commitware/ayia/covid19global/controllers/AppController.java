package commitware.ayia.covid19global.controllers;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import commitware.ayia.covid19global.utils.ThemeController;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static AppController mInstance;


    String code;
    String continent;
    String country;
    String appType;

    private boolean isFirstStart;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        SharedPreferences getSharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);

        setFirstStart(getSharedPreferences.getBoolean("firstStart", true));

        setAppType(getSharedPreferences.getString("appType", "covidGlobal"));

        new ThemeController(getSharedPreferences.getString("theme", "FollowSystem"));

        setContinent(getSharedPreferences.getString("continent", null));

        setCountry(getSharedPreferences.getString("country", null));

        setCode(getSharedPreferences.getString("code", null));

    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public boolean isFirstStart() {
        return isFirstStart;
    }

    public void setFirstStart(boolean firstStart) {
        isFirstStart = firstStart;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAppType() {
        return appType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

}
