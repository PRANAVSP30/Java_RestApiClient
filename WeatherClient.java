import java.io.*;
import java.net.*;
import java.util.regex.Pattern;

public class WeatherClient {

    public static void main(String[] args) {

        System.out.println("TASK 2  = Rest api Client ");

        try {

            String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=12.97&longitude=77.59&current_weather=true";

            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("Response from API: " + response.toString());

            String json = response.toString();

            String lat = json.split("\"latitude\":")[1].split(",")[0];
            String lon = json.split("\"longitude\":")[1].split(",")[0];

            String weatherOnly = json.split("\"current_weather\":\\{")[1];
            weatherOnly = weatherOnly.split("}")[0];
            
            System.out.println("Debug weatherOnly: " + weatherOnly);

            String temp = weatherOnly.split("\"temperature\":")[1].split(",")[0];
            String wind = weatherOnly.split("\"windspeed\":")[1].split(",")[0];

            System.out.println("Weather Data in a structured way");
            System.out.println("Latitude = " + lat);
            System.out.println("Longitude = " + lon);
            System.out.println("Temperature= " + temp + "°C");
            System.out.println("Wind Speed = " + wind + "km/h");

        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}