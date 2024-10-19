package br.developergiulia.tasks;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class APITest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }

    @Test
    public void deveRetornarTarefastest() {
        RestAssured.given()
                .when()
                .get("/todo")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void deveSalvarTarefatest() {
        RestAssured.given()
                .body("{\"task\" : \"Teste via API\", \"dueDate\": \"2024-12-30\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/todo")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void naoDeveSalvarTarefaTest() {
        RestAssured.given()
                .body("{\"task\" : \"Teste via API\", \"dueDate\": \"2020-12-30\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/todo")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }


}
