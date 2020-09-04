package commitware.ayia.covid19global.interfaces;


import commitware.ayia.covid19global.objects.CountryLocal;
import commitware.ayia.covid19global.objects.CountryServer;

public interface OnFragmentInteractionListener {
    void listItemClickServer(CountryServer countryServer);
    void listItemClickSetting(CountryLocal countryLocal, String location, String listType);
}
