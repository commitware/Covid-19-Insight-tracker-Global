package commitware.ayia.covid19global.interfaces;


import commitware.ayia.covid19global.model.News;

public interface OnFragmentListener {
    void getListIntent(String intent, String argument);
    void getNewIntent(News news);
}
