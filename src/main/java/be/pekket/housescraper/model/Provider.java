package be.pekket.housescraper.model;


import be.pekket.housescraper.provider.ProviderType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@Document(collection = "houses")
public class Provider {

    private ProviderType provider;
    private Map<String, String> cookies;

    public void addCookie( String name, String value ) {
        if ( cookies == null )
            cookies = new HashMap<>();
        if ( !StringUtils.isEmpty(name) && !StringUtils.isEmpty(value) )
            cookies.put(name, value);
    }
}
