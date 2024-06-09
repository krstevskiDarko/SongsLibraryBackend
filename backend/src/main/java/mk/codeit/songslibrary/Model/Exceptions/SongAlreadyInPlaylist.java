package mk.codeit.songslibrary.Model.Exceptions;

public class SongAlreadyInPlaylist extends RuntimeException{
    public SongAlreadyInPlaylist(){
        super("Song already in playlist");
    }
}
