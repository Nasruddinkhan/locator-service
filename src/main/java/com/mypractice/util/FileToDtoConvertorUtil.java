package com.mypractice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypractice.exception.GenericException;
import com.mypractice.exception.NotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 *
 */
public final class FileToDtoConvertorUtil {
    private FileToDtoConvertorUtil() {
    }

    /**
     *
     * @param resource
     * @return
     */
    private static InputStream getInputStream(final Resource resource)  {
            try {
                return resource.getInputStream();
            } catch (IOException e) {
               throw new GenericException(e.getMessage(), e);
            }
    }

    /**
     *
     * @param resourceLoader
     * @param destination
     * @param filePath
     * @param <T>
     * @return
     */
    public static <T> T loadFile(final ResourceLoader resourceLoader, final Class<T> destination, final String filePath) {

        return Optional.ofNullable( resourceLoader.getResource("classpath:" + filePath))
                .map(FileToDtoConvertorUtil::getInputStream)
                .map(InputStreamReader::new)
                .map(m->mapObject(m, destination))
                .orElseThrow(()->new NotFoundException("file.error"));
    }

    /**
     *
     * @param source
     * @param destination
     * @param <T>
     * @return
     */
    public static <T> T mapObject(final InputStreamReader source, final Class<T> destination){
        try {
            return new ObjectMapper().readValue( source, destination);
        } catch (IOException e) {
            throw new GenericException(e.getMessage(), e);
        }
    }


}
