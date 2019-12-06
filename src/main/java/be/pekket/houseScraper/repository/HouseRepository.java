package be.pekket.houseScraper.repository;

import be.pekket.houseScraper.model.House;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends MongoRepository<House, String> {

    boolean existsHouseByAddress( String address );
}
