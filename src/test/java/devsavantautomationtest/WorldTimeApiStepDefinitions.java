package devsavantautomationtest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class WorldTimeApiStepDefinitions {

	private static final String BASE_URL = "https://worldtimeapi.org/api";
	private static final String TIMEZONE_ENDPOINT = "/timezone";
	private static final String IP_ENDPOINT = "/ip";
	private static final HttpClient client = HttpClient.newHttpClient();
	private static final JSONParser json = new JSONParser();
	private HttpResponse<String> currentResponse;
	private JSONArray timeZones;

	@Given("User obtains the list of time zones from the API")
	public void obtainsTheListOfTimeZonesFromTheAPI() {
		var request = HttpRequest.newBuilder()
			.uri(URI.create(BASE_URL + TIMEZONE_ENDPOINT))
			.GET()
			.build();
		try {
			currentResponse = client.send(request, BodyHandlers.ofString());
			timeZones = (JSONArray) json.parse(currentResponse.body());
		} catch (InterruptedException | IOException e) {
			currentResponse = null;
			fail("Failed to make request to the API");
		} catch (ParseException e) {
			fail("Failed to parse response body");
		}
	}

	@When("gets the current time for the time zone at index {int}")
	public void getsTheCurrentTimeForTheTimeZoneAtIndex(int index) {
		var request = HttpRequest.newBuilder()
			.uri(URI.create(BASE_URL + TIMEZONE_ENDPOINT + "/" + timeZones.get(index)))
			.GET()
			.build();
		try {
			currentResponse = client.send(request, BodyHandlers.ofString());
		} catch (InterruptedException | IOException e) {
			currentResponse = null;
			fail("Failed to make request to the API");
		}
	}

	@Then("the response should have status code {int}")
	public void theResponseShouldHaveStatusCodeAndMessage(int code) {
		assertEquals(code, currentResponse.statusCode());
	}

	@Then("the response should have a valid schema")
	public void theResponseShouldHaveStatusCodeAndMessage() {
		var clientIpRegex = "^(((1?\\d?\\d)|(2[0-4]\\d)|(25[0-5]))\\.){3}((1?\\d?\\d)|(2[0-4]\\d)|(25[0-5]))$";
		var utcDatetimeRegex = "^\\d{4}-[01]\\d-([0-2]\\d|30|31)T([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d\\.\\d*[+-]\\d\\d:\\d\\d$";
		JSONObject responseBody = null;
		try {
			responseBody = (JSONObject) json.parse(currentResponse.body());
		} catch (ParseException e) {
			fail("Failed to parse response body");
		}
		assertTrue(responseBody.get("utc_datetime").toString().matches(utcDatetimeRegex));
		assertTrue(responseBody.get("client_ip").toString().matches(clientIpRegex));
	}


	@When("User obtains the time for the ip {string}")
	public void obtainsTheTimeForTheIpIp(String ip) {
		var request = HttpRequest.newBuilder()
			.uri(URI.create(BASE_URL + IP_ENDPOINT + "/" + ip))
			.GET()
			.build();
		try {
			currentResponse = client.send(request, BodyHandlers.ofString());
		} catch (InterruptedException | IOException e) {
			currentResponse = null;
			fail("Failed to make request to the API");
		}
	}

	@Then("the response should have the error message {string}")
	public void theResponseShouldHaveTheErrorMessage(String expectedMessage) {
		JSONObject responseBody = null;
		try {
			responseBody = (JSONObject) json.parse(currentResponse.body());
		} catch (ParseException e) {
			fail("Failed to parse response body");
		}
		assertEquals(expectedMessage, responseBody.get("error"));
	}
}
