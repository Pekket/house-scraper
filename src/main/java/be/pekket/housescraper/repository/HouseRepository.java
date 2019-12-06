package be.pekket.housescraper.repository;

import be.pekket.housescraper.model.House;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends MongoRepository<House, String> {

    boolean existsHouseByAddress( String address );
}
