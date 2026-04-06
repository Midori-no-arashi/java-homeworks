package postman.echo.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class PostRequestTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    @DisplayName("POST Request - Проверка кода ответа и тела")
    public void testPostRequest() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "John Doe");
        requestBody.put("email", "john@example.com");
        requestBody.put("age", 30);

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode(), "Код ответа должен быть 200");
        System.out.println("✓ Код ответа: " + response.getStatusCode());

        String contentType = response.getContentType();
        assertNotNull(contentType, "Content-Type не должен быть null");
        assertTrue(contentType.contains("application/json"),
                "Content-Type должен быть application/json, но был: " + contentType);
        System.out.println("✓ Content-Type: " + contentType);

        String name = response.jsonPath().getString("json.name");
        String email = response.jsonPath().getString("json.email");
        Integer age = response.jsonPath().getInt("json.age");

        assertNotNull(name, "Поле name не найдено в ответе");
        assertNotNull(email, "Поле email не найдено в ответе");
        assertNotNull(age, "Поле age не найдено в ответе");

        assertEquals("John Doe", name, "name должен быть 'John Doe'");
        assertEquals("john@example.com", email, "email должен быть 'john@example.com'");
        assertEquals(30, age, "age должен быть 30");

        System.out.println("✓ Полученные данные: name=" + name + ", email=" + email + ", age=" + age);

        String data = response.jsonPath().getString("data");
        assertNotNull(data, "Поле data не найдено в ответе");
        assertTrue(data.contains("John Doe"), "data должно содержать отправленные данные");
        System.out.println("✓ data содержит отправленные данные");

        String url = response.jsonPath().getString("url");
        assertNotNull(url, "URL не найден в ответе");
        assertTrue(url.contains("/post"), "URL должен содержать /post");
        System.out.println("✓ URL: " + url);

        System.out.println("\n=== POST Request тест пройден успешно! ===");
    }

    @Test
    @DisplayName("POST Request - Проверка с текстовыми данными")
    public void testPostRequestWithTextData() {
        String requestBody = "Simple text for sending";

        Response response = RestAssured
                .given()
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode(), "Код ответа должен быть 200");
        System.out.println("✓ Код ответа: " + response.getStatusCode());

        String contentType = response.getContentType();
        assertNotNull(contentType);
        assertTrue(contentType.contains("application/json"));
        System.out.println("✓ Content-Type: " + contentType);

        String data = response.jsonPath().getString("data");
        assertNotNull(data, "Поле data не найдено в ответе");
        System.out.println("✓ data: " + data);

        boolean containsText = data.contains(requestBody);
        assertTrue(containsText, "data должно содержать отправленный текст. data=" + data + ", text=" + requestBody);
        System.out.println("✓ data содержит отправленный текст");

        String url = response.jsonPath().getString("url");
        assertNotNull(url, "URL не найден в ответе");
        assertTrue(url.contains("/post"), "URL должен содержать /post");
        System.out.println("✓ URL: " + url);

        System.out.println("\n=== POST Request с текстовыми данными тест пройден! ===");
    }

    @Test
    @DisplayName("POST Request - Проверка с JSON данными")
    public void testPostRequestWithJsonData() {
        String jsonBody = "{\"key\":\"value\",\"number\":123}";

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/post")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());

        String key = response.jsonPath().getString("json.key");
        Integer number = response.jsonPath().getInt("json.number");

        assertEquals("value", key);
        assertEquals(123, number);

        System.out.println("POST Request с JSON данными тест пройден!");
    }
}