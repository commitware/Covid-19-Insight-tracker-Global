package commitware.ayia.covid19global.ui.dashboard;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import commitware.ayia.covid19global.objects.Summary;
import commitware.ayia.covid19global.service.json.ContinentDataRequest;
import commitware.ayia.covid19global.service.json.CountryDataRequest;
import commitware.ayia.covid19global.service.json.GlobeDataRequest;


public class DashboardViewModel extends ViewModel {

    private MutableLiveData<Summary> mutableLiveData;

    public void getDashboardViewModel(String request, Context context) {
        switch (request) {
            case "country":
                CountryDataRequest countryDataRequest = new CountryDataRequest();
                mutableLiveData = countryDataRequest.parseJSON();
                break;
            case "continent":
                ContinentDataRequest continentDataRequest = new ContinentDataRequest();
                mutableLiveData = continentDataRequest.parseJSON();
                break;
            case "globe":
                GlobeDataRequest globeDataRequest = new GlobeDataRequest();
                mutableLiveData = globeDataRequest.parseJSON();
                break;
        }
    }

    public LiveData<Summary> getStatistics() {

        return mutableLiveData;
    }

}


