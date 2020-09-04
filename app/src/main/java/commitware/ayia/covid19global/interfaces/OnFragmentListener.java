package commitware.ayia.covid19global.interfaces;


import commitware.ayia.covid19global.objects.News;

public interface OnFragmentListener {
    void getListIntent(String intent, String argument);
    void getNewIntent(News news);
}
