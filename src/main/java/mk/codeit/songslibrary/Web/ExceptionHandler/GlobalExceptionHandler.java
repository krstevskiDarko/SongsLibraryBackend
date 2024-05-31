package mk.codeit.songslibrary.Web.ExceptionHandler;

import mk.codeit.songslibrary.Model.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidArgumentsException.class)
    public ResponseEntity<String> handleInvalidArgumentsException(InvalidArgumentsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidArtistIdException.class)
    public ResponseEntity<String> handleInvalidArtistIdException(InvalidArtistIdException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidSongIdException.class)
    public ResponseEntity<String> handleInvalidSongIdException(InvalidSongIdException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidPlaylistIdException.class)
    public ResponseEntity<String> handleInvalidPlaylistIdException(InvalidPlaylistIdException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PlaylistWithNoSongsException.class)
    public ResponseEntity<String> handlePlaylistWithNoSongsException(PlaylistWithNoSongsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
