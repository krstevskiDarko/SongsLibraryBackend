package mk.codeit.songslibrary.Model.Exceptions;

public class SongsNotExisting extends RuntimeException{
    public SongsNotExisting(){
        super("Songs do not exist.");
    }
}
