package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeviceApiTest {
    @Test
    public void addNewDevice() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://api.restful-api.dev";

        // Define the request payload
        String requestBody = "{ \"name\": \"Apple Max Pro 1TB\", " +
                "\"data\": { " +
                "\"year\": 2023, " +
                "\"price\": 7999.99, " +
                "\"CPU model\": \"Apple ARM A7\", " +
                "\"Hard disk size\": \"1 TB\" } }";

        // Send a POST request to add a new device
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/objects");

        // Validate the response
        Assert.assertEquals(response.getStatusCode(), 200, "Response code should be 200");

        // Validate the response body
        String id = response.jsonPath().getString("id");
        String createdAt = response.jsonPath().getString("createdAt");
        Assert.assertNotNull(id, "ID should not be null");
        Assert.assertNotNull(createdAt, "CreatedAt should not be null");

        // Additional validation of other fields
        String name = response.jsonPath().getString("name");
        int year = response.jsonPath().getInt("data.year");
        double price = response.jsonPath().getDouble("data.price");
        Assert.assertEquals(name, "Apple Max Pro 1TB", "Device name should match");
        Assert.assertEquals(year, 2023, "Year should match");
        Assert.assertEquals(price, 7999.99, "Price should match");

        // Log the added device details for debugging
        System.out.println("Device Added: ID = " + id + ", Created At = " + createdAt + ", Name = " + name);
    }
}
