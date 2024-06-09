package mk.codeit.songslibrary.Model.Exceptions;

public class InvalidSongIdException extends RuntimeException{
    public InvalidSongIdException(Long id){
        super("Invalid song id " + id);
    }
}
