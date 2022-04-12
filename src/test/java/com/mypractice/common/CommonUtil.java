package com.mypractice.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypractice.exception.NotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class CommonUtil {

    private CommonUtil(){}

    public  static <T> T loadFile(String fileName, Class<T> destination, ClassLoader classLoader) {

        return Optional.ofNullable(classLoader.getResourceAsStream(fileName))
                .map(InputStreamReader::new).map(BufferedReader::new).map(obj -> {
                    try {
                        return new ObjectMapper().readValue(obj, destination);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).orElseThrow(() -> new NotFoundException("something is missing"));
    }



}
