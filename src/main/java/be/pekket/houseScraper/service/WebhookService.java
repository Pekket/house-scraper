package be.pekket.houseScraper.service;

import be.pekket.houseScraper.exception.ScraperException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static be.pekket.houseScraper.zimmo.connector.ZimmoConnector.ZIMMO_SEARCH_URL;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Service
public class WebhookService {

    private static final String url = "https://maker.ifttt.com/trigger/newhouse/with/key/XXX";

    public void send(int count) throws ScraperException {
        try ( final CloseableHttpClient client = HttpClients.createDefault() ) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            final String requestBody = "{\"value2\":\"Aantal " +  count + "\",\"value1\":\"" + ZIMMO_SEARCH_URL + "\"}";

            httpPost.setEntity(new StringEntity(requestBody));
            client.execute(httpPost);
        } catch ( IOException e ) {
           throw new ScraperException("Error sending webhook");
        }
    }

}
