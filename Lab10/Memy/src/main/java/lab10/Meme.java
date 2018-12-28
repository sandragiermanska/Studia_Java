package lab10;

import java.io.File;

public class Meme {
    private File image;
    private String title;

    public Meme(File image, String title) {
        this.image = image;
        this.title = title;

    }

    public File getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
