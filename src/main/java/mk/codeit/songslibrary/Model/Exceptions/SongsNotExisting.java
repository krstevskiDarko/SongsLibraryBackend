package mk.codeit.songslibrary.Model.Exceptions;

public class SongsNotExisting extends RuntimeException{
    public SongsNotExisting(){
        super("Songs that are provided don't exist.");
    }
}
