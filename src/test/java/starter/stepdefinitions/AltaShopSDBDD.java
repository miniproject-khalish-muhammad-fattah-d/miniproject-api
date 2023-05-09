package starter.stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import starter.data.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyString;

public class AltaShopSDBDD {
    String baseUrl = "https://altashop-api.fly.dev/api";

    User user = new User();

    @Given("{actor} call an API {string} with method {string} with payload below")
    public void userCallAnAPIWithMethodWithPayloadBelow(Actor actor, String path, String method, DataTable table) {
        actor.whoCan(CallAnApi.at(baseUrl));

        // Create request body json instance
        JSONObject bodyRequest = new JSONObject();

        // Get headers
        List<List<String>> rowsList = table.asLists(String.class);
        List<String> headerList = rowsList.get(0);

        // Get values
        List<Map<String, String>> rowsMap = table.asMaps(String.class, String.class);
        Map<String, String> valueList = rowsMap.get(0);

        for (int i = 0; i < valueList.size(); i++) {
            Faker faker = new Faker(new Locale("in-ID"));

            String key = headerList.get(i);

            List<Integer> listCategories = new ArrayList<>();
            listCategories.add(0, 8822);
            listCategories.add(1, 5938);
            listCategories.add(2, 5939);

            switch (valueList.get(key)) {
                case "randomEmail" -> {
                    String randomEmail = faker.internet().emailAddress();
                    bodyRequest.put(key, randomEmail);
                    user.setEmail(randomEmail);
                }
                case "randomPassword" -> {
                    String randomPassword = faker.internet().password();
                    bodyRequest.put(key, randomPassword);
                    user.setPassword(randomPassword);
                }
                case "randomFullname" -> bodyRequest.put(key, faker.name().fullName());
                case "randomProductName" -> bodyRequest.put(key, faker.commerce().productName());
                case "randomDescription" -> bodyRequest.put(key, faker.lorem().sentence());
                case "randomProductPrice" -> bodyRequest.put(key, Math.round(Float.parseFloat(faker.commerce().price())));
                case "randomProductCategories" -> bodyRequest.put(key, listCategories);
                case "randomCategory" -> bodyRequest.put(key, faker.pokemon().name());
                case "userEmail" -> bodyRequest.put(key, user.getEmail());
                case "userPassword" -> bodyRequest.put(key, user.getPassword());
                default -> bodyRequest.put(key, valueList.get(key));
            }
        }

        switch (method) {
            case "GET" -> actor.attemptsTo(Get.resource(path));
            case "POST" -> actor.attemptsTo(Post.to(path).with(request -> request.body(bodyRequest).log().all()));
            case "PUT" -> actor.attemptsTo(Put.to(path).with(request -> request.body(bodyRequest)));
            case "DELETE" -> actor.attemptsTo(Delete.from(path));
            default -> throw new IllegalStateException("Unknown method");
        }
    }

    @Then("{actor} verify status code is {int}")
    public void userVerifyStatusCodeIs(Actor actor, int statusCode) {
        Response response = SerenityRest.lastResponse();
        response.then().statusCode(statusCode).log().all();
    }

    @Then("{actor} verify response is match with json schema {string}")
    public void userVerifyResponseIsMatchWithJsonSchema(Actor actor, String schema) {
        Response response = SerenityRest.lastResponse();
        response.then().body(matchesJsonSchemaInClasspath(schema));
    }

    @Then("{actor} verify {string} is exist")
    public void userVerifyIsExist(Actor actor, String data) {
        Response response = SerenityRest.lastResponse();
        response.then().body(data, not(emptyString()));
    }

    @Given("{actor} call an API {string} with method {string} and specific token")
    public void userCallAnAPIWithMethodAndSpecificToken(Actor actor, String path, String method) {
        actor.whoCan(CallAnApi.at(baseUrl));

        switch (method) {
            case "GET" -> actor.attemptsTo(Get.resource(path).with(request -> request.header("Authorization", "Bearer " + user.getToken())));
            case "POST" -> actor.attemptsTo(Post.to(path));
            case "PUT" -> actor.attemptsTo(Put.to(path));
            case "DELETE" -> actor.attemptsTo(Delete.from(path));
            default -> throw new IllegalStateException("Unknown method");
        }
    }

    @Given("{actor} call an API {string} with method {string}")
    public void userCallAnAPIWithMethod(Actor actor, String path, String method) {
        actor.whoCan(CallAnApi.at(baseUrl));

        switch (method) {
            case "GET" -> actor.attemptsTo(Get.resource(path));
            case "POST" -> actor.attemptsTo(Post.to(path));
            case "PUT" -> actor.attemptsTo(Put.to(path));
            case "DELETE" -> actor.attemptsTo(Delete.from(path));
            default -> throw new IllegalStateException("Unknown method");
        }
    }

    @Given("{actor} call an API {string} with method {string} with payload below and specific token")
    public void userCallAnAPIWithMethodWithPayloadBelowAndSpecificToken(Actor actor, String path, String method, DataTable table) {
        actor.whoCan(CallAnApi.at(baseUrl));

        // Create request body json instance
        JSONObject bodyRequest = new JSONObject();

        // Get headers
        List<List<String>> rowsList = table.asLists(String.class);
        List<String> headerList = rowsList.get(0);

        // Get values
        List<Map<String, String>> rowsMap = table.asMaps(String.class, String.class);
        Map<String, String> valueList = rowsMap.get(0);

        for (int i = 0; i < valueList.size(); i++) {
            Faker faker = new Faker(new Locale("in-ID"));

            String key = headerList.get(i);

            List<Integer> listCategories = new ArrayList<>();
            listCategories.add(0, 8822);
            listCategories.add(1, 5938);
            listCategories.add(2, 5939);

            List<Integer> listProductId = new ArrayList<>();
            listCategories.add(0, 12887);
            listCategories.add(1, 12888);
            listCategories.add(2, 12889);

            switch (valueList.get(key)) {
                case "randomEmail" -> {
                    String randomEmail = faker.internet().emailAddress();
                    bodyRequest.put(key, randomEmail);
                    user.setEmail(randomEmail);
                }
                case "randomPassword" -> {
                    String randomPassword = faker.internet().password();
                    bodyRequest.put(key, randomPassword);
                    user.setPassword(randomPassword);
                }
                case "randomFullname" -> bodyRequest.put(key, faker.name().fullName());
                case "randomProductName" -> bodyRequest.put(key, faker.commerce().productName());
                case "randomDescription" -> bodyRequest.put(key, faker.lorem().sentence());
                case "randomProductPrice" -> bodyRequest.put(key, Math.round(Float.parseFloat(faker.commerce().price())));
                case "randomProductCategories" -> bodyRequest.put(key, listCategories);
                case "randomProductId" -> bodyRequest.put(key, 14394);
                case "randomQuantity" -> bodyRequest.put(key, faker.number().randomDigitNotZero());
                case "randomRating" -> bodyRequest.put(key, faker.number().numberBetween(1, 5));
                case "userEmail" -> bodyRequest.put(key, user.getEmail());
                case "userPassword" -> bodyRequest.put(key, user.getPassword());
                default -> bodyRequest.put(key, valueList.get(key));
            }
        }

        JSONArray arrayRequest = new JSONArray();
        arrayRequest.add(bodyRequest);

        switch (method) {
            case "GET" -> actor.attemptsTo(Get.resource(path));
            case "POST" -> actor.attemptsTo(Post.to(path)
                    .with(request -> request.header("Authorization", "Bearer " + user.getToken())
                            .body(bodyRequest).log().all()));
            case "POSTarray" -> actor.attemptsTo(Post.to(path)
                    .with(request -> request.header("Authorization", "Bearer " + user.getToken())
                            .body(arrayRequest).log().all()));
            case "PUT" -> actor.attemptsTo(Put.to(path).with(request -> request.body(bodyRequest)));
            case "DELETE" -> actor.attemptsTo(Delete.from(path));
            default -> throw new IllegalStateException("Unknown method");
        }
    }

    @And("{actor} get new token")
    public void userGetNewToken(Actor actor) {
        Response response = SerenityRest.lastResponse();
        user.setToken(response.path("data"));
    }
}
