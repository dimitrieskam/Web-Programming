package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SongNotFoundException extends RuntimeException{
    public SongNotFoundException(Long songId) {
        super(String.format("Song with id %d does not exist.", songId));
    }
}
