package be.pekket.housescraper.repository;

import be.pekket.housescraper.model.House;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends MongoRepository<House, String> {

    boolean existsHouseByAddress( String address );

    List<House> findTop20ByOrderByTimestampDesc();
}
