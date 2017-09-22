package org.andy.file.storage;

/**
 * @author: andy
 * @Date: 2017/9/21 10:04
 * @Description:
 */
public class StorageException extends RuntimeException {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
