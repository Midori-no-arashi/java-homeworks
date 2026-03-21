package postman.echo.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class PatchRequestTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    @DisplayName("PATCH Request - Проверка кода ответа и тела")
    public void testPatchRequest() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Updated Name");
        requestBody.put("age", 35);

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
        System.out.println("✓ Код ответа: " + response.getStatusCode());

        String contentType = response.getContentType();
        assertNotNull(contentType);
        assertTrue(contentType.contains("application/json"));
        System.out.println("✓ Content-Type: " + contentType);

        String name = response.jsonPath().getString("json.name");
        Integer age = response.jsonPath().getInt("json.age");

        assertEquals("Updated Name", name);
        assertEquals(35, age);

        System.out.println("✓ Обновленные поля: name=" + name + ", age=" + age);

        String url = response.jsonPath().getString("url");
        assertNotNull(url);
        assertTrue(url.contains("/patch"));
        System.out.println("✓ URL: " + url);

        System.out.println("\n=== PATCH Request тест пройден успешно! ===");
    }
}