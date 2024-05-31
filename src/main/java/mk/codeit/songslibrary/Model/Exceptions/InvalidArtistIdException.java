package mk.codeit.songslibrary.Model.Exceptions;

public class InvalidArtistIdException extends RuntimeException{
    public InvalidArtistIdException(Long id){
        super("Invalid artist id: " + id);
    }
}
