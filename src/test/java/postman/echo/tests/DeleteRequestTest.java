package postman.echo.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteRequestTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    @DisplayName("DELETE Request - Проверка кода ответа и тела")
    public void testDeleteRequest() {
        Response response = RestAssured
                .given()
                .param("id", "123")
                .when()
                .delete("/delete")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();

        assertTrue(statusCode == 200 || statusCode == 202 || statusCode == 204,
                "Код ответа должен быть 200, 202 или 204. Фактический: " + statusCode);
        System.out.println("✓ Код ответа: " + statusCode);

        if (statusCode != 204) {
            String contentType = response.getContentType();
            assertNotNull(contentType);
            assertTrue(contentType.contains("application/json"));
            System.out.println("✓ Content-Type: " + contentType);

            String id = response.jsonPath().getString("args.id");
            assertEquals("123", id);
            System.out.println("✓ Параметр id: " + id);

            String url = response.jsonPath().getString("url");
            assertNotNull(url);
            assertTrue(url.contains("/delete"));
            System.out.println("✓ URL: " + url);
        } else {
            System.out.println("✓ Тело ответа пустое (204 No Content)");
        }

        System.out.println("\n=== DELETE Request тест пройден успешно! ===");
    }

    @Test
    @DisplayName("DELETE Request - Проверка с разными параметрами")
    public void testDeleteRequestWithDifferentParams() {
        Response response = RestAssured
                .given()
                .param("resource", "user")
                .param("id", "456")
                .when()
                .delete("/delete")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 200 || statusCode == 202 || statusCode == 204);

        if (statusCode != 204) {
            assertEquals("user", response.jsonPath().getString("args.resource"));
            assertEquals("456", response.jsonPath().getString("args.id"));
        }

        System.out.println("DELETE Request с параметрами тест пройден!");
    }
}