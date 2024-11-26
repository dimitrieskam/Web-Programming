package mk.finki.ukim.mk.lab.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class AlbumNotFoundException extends RuntimeException{
    public AlbumNotFoundException(Long albumId) {
        super(String.format("Album with id %d does not exist.", albumId));
    }
}
