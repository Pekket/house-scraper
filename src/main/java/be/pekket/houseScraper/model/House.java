package be.pekket.houseScraper.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

@Document(collection = "houses")
public class House {

    @Id
    private String id;

    private String title;
    private String agency;
    private String address;
    private String price;
    private String url;

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle( String title ) {
        this.title = title;
    }

    public String getAgency() {
        return agency;
    }

    private void setAgency( String agency ) {
        this.agency = agency;
    }

    public String getAddress() {
        return address;
    }

    private void setAddress( String address ) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    private void setPrice( String price ) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    private void setUrl( String url ) {
        this.url = url;
    }

    public static class Builder {

        private final House house;

        public Builder() {
            house = new House();
        }

        public House.Builder title( final String title ) {
            if ( !StringUtils.isEmpty(title) )
                house.setTitle(title);
            return this;
        }

        public House.Builder agency( final String agency ) {
            if ( !StringUtils.isEmpty(agency) )
                house.setAgency(agency);
            return this;
        }

        public House.Builder address( final String Address ) {
            if ( !StringUtils.isEmpty(Address) )
                house.setAddress(Address);
            return this;
        }

        public House.Builder price( final String price ) {
            if ( !StringUtils.isEmpty(price) )
                house.setPrice(price);
            return this;
        }

        public House.Builder url( final String url ) {
            if ( !StringUtils.isEmpty(url) )
                house.setUrl(url);
            return this;
        }

        public House build() {
            return house;
        }
    }
}
