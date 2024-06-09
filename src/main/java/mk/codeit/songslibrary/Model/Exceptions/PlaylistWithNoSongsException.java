package mk.codeit.songslibrary.Model.Exceptions;

public class PlaylistWithNoSongsException extends RuntimeException{
    public PlaylistWithNoSongsException(){
        super("Playlist does not contain any songs");
    }
}
