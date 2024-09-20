package org.example.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception thrown when a requested resource is not found.
 *
 * <p>This exception returns a {@link HttpStatus#NOT_FOUND} (404) response status
 * when thrown, indicating that the resource could not be found.</p>
 *
 * @see RuntimeException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
