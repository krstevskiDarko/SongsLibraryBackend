package mk.codeit.songslibrary.Model.Exceptions;

public class InvalidPlaylistIdException extends RuntimeException {
    public InvalidPlaylistIdException(Long id) {
        super("Invalid playlist id: " + id);
    }
}
