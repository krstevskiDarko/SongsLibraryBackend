package mk.codeit.songslibrary.Web.ExceptionHandler;

import mk.codeit.songslibrary.Model.Exceptions.*;
import mk.codeit.songslibrary.Model.Song;
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

    @ExceptionHandler(SongAlreadyInPlaylist.class)
    public ResponseEntity<String> handleSongAlreadyInPlaylistException(SongAlreadyInPlaylist ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(SongsNotExisting.class)
    public ResponseEntity<String> handleSongsNotExistingForPlaylist(SongsNotExisting ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
