package com.jmserver.redirecturlshortner;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main implements RequestHandler<Map<String, Object>, Map<String, Object>> {

    private final S3Client s3Client = S3Client.builder().build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Map<String, Object> handleRequest(Map<String, Object> input, Context context) {
        String pathParameters = input.get("rawPath").toString();
        String shortUrlCode = pathParameters.replace("/", "");



        if (shortUrlCode == null || shortUrlCode.isEmpty()){
            throw new IllegalArgumentException("Invalid input: 'shortUrlCode' is required. ");
        }

        GetObjectRequest getObjectRequest = GetObjectRequest.builder().
                bucket("urlshortenerstoragejm")
                .key(shortUrlCode + ".json")
                .build();

        InputStream s3ObjectStream;
        try{
            s3ObjectStream = s3Client.getObject(getObjectRequest);

        } catch (Exception e) {
            throw new RuntimeException("Error fetching URL data from S3: " + e.getMessage(), e);
        }

        UrlData urlData;
        try{
            urlData = objectMapper.readValue(s3ObjectStream, UrlData.class);

        } catch (Exception e) {
            throw new RuntimeException("Error deserialize URL data: " + e.getMessage(), e);
        }

        Map<String, Object> response = new HashMap<>();

        long currentTimeInSeconds = System.currentTimeMillis() / 1000;
        if (urlData.getExpirationTime() < currentTimeInSeconds ){
            response.put("statusCode", 410);
            response.put("body", "this URL has expired.");
            return response;
        }

        response.put("statusCode", 302);
        Map<String, String> header = new HashMap<>();
        header.put("Location", urlData.getOriginalUrl());
        response.put("headers", header);

        return response;
    }
}