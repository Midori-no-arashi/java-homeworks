package postman.echo.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetRequestTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    @DisplayName("GET Request - Проверка кода ответа и тела")
    public void testGetRequest() {
        Response response = RestAssured
                .given()
                .param("foo1", "bar1")
                .param("foo2", "bar2")
                .when()
                .get("/get")
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

        String body = response.getBody().asString();
        assertNotNull(body, "Тело ответа не должно быть null");
        assertTrue(body.contains("args"), "Ответ должен содержать поле 'args'");
        assertTrue(body.contains("headers"), "Ответ должен содержать поле 'headers'");
        assertTrue(body.contains("url"), "Ответ должен содержать поле 'url'");
        System.out.println("✓ Тело ответа содержит все необходимые поля");

        String foo1 = response.jsonPath().getString("args.foo1");
        String foo2 = response.jsonPath().getString("args.foo2");

        assertNotNull(foo1, "Параметр foo1 не найден в ответе");
        assertNotNull(foo2, "Параметр foo2 не найден в ответе");
        assertEquals("bar1", foo1, "foo1 должен быть 'bar1'");
        assertEquals("bar2", foo2, "foo2 должен быть 'bar2'");
        System.out.println("✓ Параметры: foo1=" + foo1 + ", foo2=" + foo2);

        String url = response.jsonPath().getString("url");
        assertNotNull(url, "URL не найден в ответе");
        assertTrue(url.contains("/get"), "URL должен содержать /get");
        assertTrue(url.contains("foo1=bar1"), "URL должен содержать параметр foo1=bar1");
        assertTrue(url.contains("foo2=bar2"), "URL должен содержать параметр foo2=bar2");
        System.out.println("✓ URL: " + url);

        assertTrue(response.getTime() < 2000, "Время ответа должно быть меньше 2000мс");
        System.out.println("✓ Время ответа: " + response.getTime() + " мс");

        System.out.println("\n=== GET Request тест пройден успешно! ===");
    }

    @Test
    @DisplayName("GET Request - Проверка с разными параметрами")
    public void testGetRequestWithDifferentParams() {
        Response response = RestAssured
                .given()
                .param("name", "John")
                .param("age", "30")
                .param("city", "Moscow")
                .when()
                .get("/get")
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());

        assertEquals("John", response.jsonPath().getString("args.name"));
        assertEquals("30", response.jsonPath().getString("args.age"));
        assertEquals("Moscow", response.jsonPath().getString("args.city"));

        System.out.println("GET Request с параметрами тест пройден!");
        System.out.println("Параметры: name=John, age=30, city=Moscow");
    }
}