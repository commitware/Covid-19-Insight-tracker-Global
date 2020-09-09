package commitware.ayia.covid19global.interfaces;


import commitware.ayia.covid19global.model.CountryLocal;
import commitware.ayia.covid19global.model.CountryServer;

public interface OnFragmentInteractionListener {
    void listItemClickServer(CountryServer countryServer);
    void listItemClickSetting(CountryLocal countryLocal, String location, String listType);
}
