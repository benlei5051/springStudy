package org.andy.file.storage;

/**
 * @author: andy
 * @Date: 2017/9/21 10:09
 * @Description:
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
