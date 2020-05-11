package be.pekket.housescraper.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    @Value("${iftt.webhook.event.name}")
    private String eventName;
    @Value("${iftt.webhook.event.id}")
    private String eventId;
    private static final String url = "https://maker.ifttt.com/trigger/%s/with/key/%s";
    private final RestTemplate restTemplate;

    public WebhookService( RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }

    public void send( int count ) {
        final String requestBody = "{\"value2\":\"" + count + "\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String postUrl = String.format(url, eventName, eventId);
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        restTemplate.postForEntity(postUrl, request, String.class);
    }
}