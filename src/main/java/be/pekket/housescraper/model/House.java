package be.pekket.housescraper.model;

import be.pekket.housescraper.provider.Provider;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "houses")
public class House {

    @Id
    private String id;
    private long timestamp;
    private Provider provider;

    private String title;
    private String agency;
    private String address;
    private String price;
    private String url;
    private String imgUrl;
}
