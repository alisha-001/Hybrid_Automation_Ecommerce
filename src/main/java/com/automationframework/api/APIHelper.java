package com.automationframework.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.automationframework.config.ConfigurationManager;

import java.util.HashMap;
import java.util.Map;

public class APIHelper {
    private static final Logger logger = LogManager.getLogger(APIHelper.class);
    private String baseURL;
    private Map<String, String> headers;

    public APIHelper() {
        this.baseURL = ConfigurationManager.getProperty("api.baseURL", "https://reqres.in");
        this.headers = new HashMap<>();
        this.headers.put("Content-Type", "application/json");
        addConfigHeaders();
    }

    public APIHelper(String baseURL) {
        this.baseURL = baseURL;
        this.headers = new HashMap<>();
        this.headers.put("Content-Type", "application/json");
        addConfigHeaders();
    }

    private void addConfigHeaders() {
        String apiKey = ConfigurationManager.getProperty("api.key");
        if (apiKey != null && !apiKey.trim().isEmpty()) {
            this.headers.put("x-api-key", apiKey.trim());
        }
        String authToken = ConfigurationManager.getProperty("api.auth.token");
        if (authToken != null && !authToken.trim().isEmpty()) {
            this.headers.put("Authorization", "Bearer " + authToken.trim());
        }
    }

    /**
     * Add custom header
     */
    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    /**
     * Get RequestSpecification
     */
    private RequestSpecification getRequestSpecification() {
        RestAssured.baseURI = baseURL;
        RequestSpecification requestSpec = RestAssured.given();
        headers.forEach(requestSpec::header);
        return requestSpec;
    }

    /**
     * GET request
     */
    public Response get(String endpoint) {
        try {
            logger.info("GET request to: " + baseURL + endpoint);
            Response response = getRequestSpecification()
                    .when()
                    .get(endpoint);
            logger.info("GET response status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("GET request failed: " + e.getMessage());
            throw new RuntimeException("GET request failed", e);
        }
    }

    /**
     * POST request
     */
    public Response post(String endpoint, Object body) {
        try {
            logger.info("POST request to: " + baseURL + endpoint);
            logger.info("Request body: " + body);
            Response response = getRequestSpecification()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .post(endpoint);
            logger.info("POST response status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("POST request failed: " + e.getMessage());
            throw new RuntimeException("POST request failed", e);
        }
    }

    /**
     * POST request with String body
     */
    public Response postWithString(String endpoint, String body) {
        try {
            logger.info("POST request to: " + baseURL + endpoint);
            logger.info("Request body: " + body);
            Response response = getRequestSpecification()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .post(endpoint);
            logger.info("POST response status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("POST request failed: " + e.getMessage());
            throw new RuntimeException("POST request failed", e);
        }
    }

    /**
     * PUT request
     */
    public Response put(String endpoint, Object body) {
        try {
            logger.info("PUT request to: " + baseURL + endpoint);
            logger.info("Request body: " + body);
            Response response = getRequestSpecification()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .put(endpoint);
            logger.info("PUT response status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("PUT request failed: " + e.getMessage());
            throw new RuntimeException("PUT request failed", e);
        }
    }

    /**
     * PATCH request
     */
    public Response patch(String endpoint, Object body) {
        try {
            logger.info("PATCH request to: " + baseURL + endpoint);
            logger.info("Request body: " + body);
            Response response = getRequestSpecification()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .patch(endpoint);
            logger.info("PATCH response status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("PATCH request failed: " + e.getMessage());
            throw new RuntimeException("PATCH request failed", e);
        }
    }

    /**
     * DELETE request
     */
    public Response delete(String endpoint) {
        try {
            logger.info("DELETE request to: " + baseURL + endpoint);
            Response response = getRequestSpecification()
                    .when()
                    .delete(endpoint);
            logger.info("DELETE response status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("DELETE request failed: " + e.getMessage());
            throw new RuntimeException("DELETE request failed", e);
        }
    }

    /**
     * Get base URL
     */
    public String getBaseURL() {
        return baseURL;
    }

    /**
     * Set base URL
     */
    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }
}
