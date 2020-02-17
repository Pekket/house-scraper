package be.pekket.housescraper.repository;

import be.pekket.housescraper.model.Provider;
import be.pekket.housescraper.provider.ProviderType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends MongoRepository<Provider, String> {

    Provider findByProvider( ProviderType type );

    boolean existsByProvider(ProviderType type);
}
