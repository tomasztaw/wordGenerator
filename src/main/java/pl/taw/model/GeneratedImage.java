/**
 * Created by tomasz_taw
 * Date: 06.11.2023
 * Time: 21:09
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.model;

import java.util.List;

public class GeneratedImage {

    private List<ImageUrl> data;

    public List<ImageUrl> getData() {
        return data;
    }

    public void setData(List<ImageUrl> data) {
        this.data = data;
    }

    public static class ImageUrl {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
