package be.pekket.housescraper.repository;

import be.pekket.housescraper.model.House;
import be.pekket.housescraper.provider.Provider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends MongoRepository<House, String> {

    boolean existsHouseByAddress( String address );

    boolean existsHouseByUrl( String url );

    List<House> findTop30ByOrderByTimestampDesc();

    List<House> findTop30ByProviderInOrderByTimestampDesc( List<Provider> providers );

    List<House> findTop30ByAddressContainingIgnoreCaseAndProviderInOrderByTimestampDesc(String addressQuery, List<Provider> providers);
}
