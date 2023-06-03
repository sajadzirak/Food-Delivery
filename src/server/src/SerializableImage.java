package server.src;

import java.io.Serializable;

import javafx.scene.image.Image;

public class SerializableImage extends Image implements Serializable{
    public SerializableImage(String path){
        super(path);
    }
}
