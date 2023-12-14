package Repository;

import Model.Event;
import Model.Location;
import Model.User;

import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    private static final String absolutePath = new File("identifier.sqlite").getAbsolutePath();
    private static final String DATABASE_URL = "jdbc:sqlite:" + absolutePath;

    //establishes connection to the database, call at the beginning of functions within DatabaseHandler
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    //searches database for matching user, using in create user
    public static boolean userExists(String username, String password) {
        try (Connection connection = getConnection()) {
            System.out.println("Connected to database: " + DATABASE_URL);

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            System.out.println("SQL: " + sql);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                System.out.println("test");
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle the exception appropriately in your application
        }
    }

    //creates a new user, inserts into database
    public static boolean createUser(String username, String password, boolean isAdmin) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO users (username, password, is_admin) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setInt(3, isAdmin ? 1 : 0); // Convert boolean to integer (1 for true, 0 for false)

                statement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //attempts to find a user in database matching login info, if yes then call user.login() to grab certain fields
    //otherwise will return null
    public static User login(String username, String password) {
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int userId = resultSet.getInt("user_id");
                        int isAdmin = resultSet.getInt("is_admin");

                        User user = User.getInstance();
                        user.login(userId, username, isAdmin);

                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    //create an event
    public static boolean createEvent(String eventName, String eventDesc, String dateTime, String location, double lat, double longi, String club) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO events (event_name, event_description, event_datetime, event_location, latitude, longitude, event_club) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, eventName);
                statement.setString(2, eventDesc);
                statement.setString(3, dateTime);
                statement.setString(4, location);
                statement.setDouble(5, lat);
                statement.setDouble(6, longi);
                statement.setString(7, club);

                statement.executeUpdate();

                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //grabs all events from events table
    public static ArrayList<Event> getAllEvents() {
        ArrayList<Event> eventList = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM events";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int eventId = resultSet.getInt("event_id");
                        String name = resultSet.getString("event_name");
                        String description = resultSet.getString("event_description");
                        String dateTimeString = resultSet.getString("event_datetime");
                        // Define the format of the date and time in the string
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                        // Parse the string to a LocalDateTime object
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

                        String eventLocation = resultSet.getString("event_location");
                        String club = resultSet.getString("event_club");
                        double latitude = resultSet.getDouble("latitude");
                        double longitude = resultSet.getDouble("longitude");

                        Event event = new Event(eventId, name, description, dateTime, latitude, longitude, club, eventLocation);
                        eventList.add(event);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventList;
    }

    public static ArrayList<Event> getUserEvents(int user_id) {
        ArrayList<Event> eventList = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String sql = "SELECT events.* FROM events " +
                    "JOIN user_events ON events.event_id = user_events.event_id " +
                    "WHERE user_events.user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, user_id);  // Replace 1 with the actual user_id

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int eventId = resultSet.getInt("event_id");
                        String name = resultSet.getString("event_name");
                        String description = resultSet.getString("event_description");
                        String dateTimeString = resultSet.getString("event_datetime");
                        // Define the format of the date and time in the string
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                        // Parse the string to a LocalDateTime object
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

                        String eventLocation = resultSet.getString("event_location");
                        String club = resultSet.getString("event_club");
                        double latitude = resultSet.getDouble("latitude");
                        double longitude = resultSet.getDouble("longitude");

                        Event event = new Event(eventId, name, description, dateTime, latitude, longitude, club, eventLocation);
                        eventList.add(event);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventList;
    }

    public static boolean saveEvent(int user_id, int event_id) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO user_events (user_id, event_id) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, user_id);
                statement.setInt(2, event_id);

                statement.executeUpdate();

                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean unfollowEvent(int user_id, int event_id) {
        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM user_events WHERE user_id = ? AND event_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, user_id);
                statement.setInt(2, event_id);

                statement.executeUpdate();

                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteEvent(int event_id) {
        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM events WHERE event_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, event_id); // Set the parameter value

                statement.executeUpdate();
            }

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return  false;
    }

    public static ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList<Location>();
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM locations";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String name = resultSet.getString("location_name");
                        double latitude = resultSet.getDouble("latitude");
                        double longitude = resultSet.getDouble("longitude");

                        Location loc = new Location(name, latitude, longitude);
                        locations.add(loc);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locations;
    }

}
