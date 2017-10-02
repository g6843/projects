package sample;

import java.io.Serializable;

public class ImageDTO implements Serializable
{
    private OutputDTO[] images;

    public OutputDTO[] getImages ()
    {
        return images;
    }

    public void setImages (OutputDTO[] images)
    {
        this.images = images;
    }

    @Override
    public String toString()
    {
        return "[images = "+images+"]";
    }
}
