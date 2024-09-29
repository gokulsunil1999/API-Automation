package maven_group.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class FancodeUserSteps {
    private List<Map<String, Object>> users;
    private List<Map<String, Object>> todos;

    // Base API URL
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com";

    // Latitude and longitude ranges for FanCode city
    private static final double LAT_MIN = -40;
    private static final double LAT_MAX = 5;
    private static final double LONG_MIN = 5;
    private static final double LONG_MAX = 100;

    @Given("the user list is fetched from the API")
    public void fetch_user_list_from_api() {
        // Fetch users from /users API
        Response response = RestAssured.get(BASE_URL + "/users");
        users = response.jsonPath().getList("$");
        Assert.assertNotNull("User list should not be null", users);
        
        System.out.println("Fetched Users:" + users); // Print the users fetched from the API for verification
    }

    @Given("user belongs to the city FanCode")
    public void filter_users_from_fancode_city() {
        // Filter users based on latitude and longitude range for FanCode city
        users.removeIf(user -> {
            Map<String, Object> address = (Map<String, Object>) user.get("address");
            Map<String, Object> geo = (Map<String, Object>) address.get("geo");

            double lat = Double.parseDouble((String) geo.get("lat"));
            double lon = Double.parseDouble((String) geo.get("lng"));

            // Check if lat/lon is not within FanCode's boundaries
            return !(lat >= LAT_MIN && lat <= LAT_MAX && lon >= LONG_MIN && lon <= LONG_MAX);
        });
        
        Assert.assertFalse("No users found from FanCode city", users.isEmpty());
        
        System.out.println("Filtered Users from Fancode city:" + users); // Print users that belong to FanCode city for verification
    }

    @Then("the user's completed task percentage should be greater than 50%")
    public void verify_user_task_completion() {
        // For each user from FanCode city, fetch their todos and verify task completion percentage
        for (Map<String, Object> user : users) {
            int userId = (Integer) user.get("id");

            // Fetch todos for the user
            Response todoResponse = RestAssured.get(BASE_URL + "/todos?userId=" + userId);
            todos = todoResponse.jsonPath().getList("$");

            long totalTodos = todos.size();
            long completedTodos = todos.stream().filter(todo -> (Boolean) todo.get("completed")).count();

            double completionPercentage = (double) completedTodos / totalTodos * 100;
            
            System.out.println("User ID " + userId + " has completed" + completionPercentage + "% of tasks"); // Print the completion percentage for each user for debugging

            // Assert that the user has completed more than 50% of their todos
            Assert.assertTrue("User ID " + userId + " has not completed more than 50% of tasks",
                    completionPercentage > 50);
        }
    }
}
