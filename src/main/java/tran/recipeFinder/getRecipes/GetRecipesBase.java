package tran.recipeFinder.getRecipes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Properties;

/**
 * This is a parent class that holds two methos that are shared between the get recipe classes.
 * @author Todd
 */
public abstract class GetRecipesBase {

	protected String sAPIkey;
	
	public GetRecipesBase() {}
	
	/**
	 * helper method to get the API key stored in the keys.property file.
	 * http://www.mkyong.com/java/java-getresourceasstream-in-static-method/
	 */
	protected void readAPIKey() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = GetRecipes.class.getResourceAsStream("/properties/keys.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			sAPIkey = prop.getProperty("foodToForkKey");
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		} 
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
     * Helper method to make a food2fork search and get query depending on the string passed in.
     * @param sQuery The string to make an API call using the food2fork API.
     * @return an unparsed json object.
     */
	protected String makeAPIRequest(String sQuery) {
    	StringBuilder sbResult = new StringBuilder();
    	
        URL url = null;
        HttpURLConnection conn = null;
        BufferedReader rd = null;
        String sLine = null;
    	try {
			url = new URL(sQuery);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((sLine = rd.readLine()) != null) {
				sbResult.append(sLine);
		    }
		} 
		catch (MalformedURLException e) {
			e.printStackTrace(); // URL is unsupported or cannot be parsed.
		}
		catch (ProtocolException e) {
			// from http://developer.android.com/reference/java/net/ProtocolException.html
			// Signals that either a connection attempt to a socket of the wrong type, the application of an unsupported operation or that a general error in the underlying protocol has occurred.
			e.printStackTrace();
		} 
		catch (IOException e) {
			/**
			 * error with opening the connection
			 * error with being able to read from the connection
			 */
			e.printStackTrace();
		}
        finally {
        	if(rd != null) {
        		try {
					rd.close();
				} 
        		catch (IOException e) {
					e.printStackTrace(); // an error closing.
				}
        	}
        }
    	return sbResult.toString();
    }
}
