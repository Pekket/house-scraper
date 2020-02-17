package be.pekket.housescraper.service;

import be.pekket.housescraper.model.Provider;
import be.pekket.housescraper.provider.ProviderType;
import be.pekket.housescraper.repository.ProviderRepository;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderService( ProviderRepository providerRepository ) {
        this.providerRepository = providerRepository;
    }

    public String getCookie( ProviderType providerType, String cookie ) {
        Provider provider = providerRepository.findByProvider(providerType);
        return provider != null ? provider.getCookies().get(cookie) : null;
    }


    public void storeCookie( ProviderType providerType, String cookieName, String cookieValue ) {
        Provider provider;

        if ( providerRepository.existsByProvider(providerType) ) {
            provider = providerRepository.findByProvider(providerType);
        } else {
            provider = Provider.builder().provider(providerType).build();
        }

        provider.addCookie(cookieName, cookieValue);
        providerRepository.save(provider);
    }
}
