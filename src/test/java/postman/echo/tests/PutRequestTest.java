package postman.echo.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class PutRequestTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    @DisplayName("PUT Request - Проверка кода ответа и тела")
    public void testPutRequest() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1);
        requestBody.put("name", "Jane Doe");
        requestBody.put("age", 25);

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
        System.out.println("✓ Код ответа: " + response.getStatusCode());

        String contentType = response.getContentType();
        assertNotNull(contentType);
        assertTrue(contentType.contains("application/json"));
        System.out.println("✓ Content-Type: " + contentType);

        Integer id = response.jsonPath().getInt("json.id");
        String name = response.jsonPath().getString("json.name");
        Integer age = response.jsonPath().getInt("json.age");

        assertEquals(1, id);
        assertEquals("Jane Doe", name);
        assertEquals(25, age);

        System.out.println("✓ Полученные данные: id=" + id + ", name=" + name + ", age=" + age);

        String url = response.jsonPath().getString("url");
        assertNotNull(url);
        assertTrue(url.contains("/put"));
        System.out.println("✓ URL: " + url);

        System.out.println("\n=== PUT Request тест пройден успешно! ===");
    }
}