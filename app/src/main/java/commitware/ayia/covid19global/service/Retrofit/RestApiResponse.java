package commitware.ayia.covid19global.service.Retrofit;

import java.util.List;

import commitware.ayia.covid19global.model.News;

public class RestApiResponse {

    private List<News> posts;
    private Throwable error;

    public RestApiResponse(List<News> posts) {
        this.posts = posts;
        this.error = null;
    }

    public RestApiResponse(Throwable error) {
        this.error = error;
        this.posts = null;
    }

    public List<News> getPosts() {
        return posts;
    }

    public void setPosts(List<News> posts) {
        this.posts = posts;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
