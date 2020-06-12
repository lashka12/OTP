package otp;

import java.net.*;
import java.util.Base64;
import java.util.Random;
import java.io.*;

public class BulkSMS {

	static private String userName;
	static private String password;
	static private String uri = "https://api.bulksms.com/v1/messages";

	public static void authenticate(String user, String pass) {

		if (user != null && pass != null) {
			userName = user;
			password = pass;

		} else {
			System.out.println("please use a valid account username and data");
		}

	}

	public static String generateOTP(int digits, boolean alphanumeric) {

		Random rand = new Random();
		String otp = "";

		for (int i = 0; i < digits; i++)
			if (alphanumeric)
				if (rand.nextInt(2) == 0)
					otp += (char) (rand.nextInt(26) + 'a');

				else
					otp += rand.nextInt(10);

			else
				otp += rand.nextInt(10);

		return otp;

	}


	public static void sendSms(String countryCode, String phoneNumber, String body) {

		String myData = "{" + "to: \"" + countryCode + phoneNumber + "\"," + " body: \"" + body + "\"" + "}";

		try {

			URL url = new URL(uri);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.setDoOutput(true);

			// supply the credentials
			String authStr = userName + ":" + password;
			String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
			request.setRequestProperty("Authorization", "Basic " + authEncoded);

			// we want to use HTTP POST
			request.setRequestMethod("POST");
			request.setRequestProperty("Content-Type", "application/json");

			// write the data to the request
			OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
			out.write(myData);
			out.close();

			try {
				// make the call to the API
				InputStream response = request.getInputStream();
				BufferedReader in = new BufferedReader(new InputStreamReader(response));
				String replyText;
				while ((replyText = in.readLine()) != null) {
					System.out.println(replyText);
				}
				in.close();
			} catch (IOException ex) {
				System.out.println("An error occurred:" + ex.getMessage());

				BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));

				// print the detail that comes with the error
				String replyText;
				while ((replyText = in.readLine()) != null) {
					System.out.println(replyText);
				}
				in.close();
			}
			request.disconnect();

		} catch (Exception e) {
			System.out.println("An error occurred:" + e.getMessage());
		}

	}
	
	public static String getUserName() {
		return userName;
	}


	public static String getPassword() {
		return password;
	}


	public static String getUri() {
		return uri;
	}

	public static void setUri(String uri) {
		BulkSMS.uri = uri;
	}
}
