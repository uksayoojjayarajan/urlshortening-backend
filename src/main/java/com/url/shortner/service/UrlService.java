package com.url.shortner.service;

import com.url.shortner.model.Url;
import com.url.shortner.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;
    private final AiService aiService;

    private static final String ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    @Transactional
    public String shortenUrl(String originalUrl) {
        Optional<Url> existingUrl = urlRepository.findByOriginalUrl(originalUrl);
        if (existingUrl.isPresent()) {
            return existingUrl.get().getShortUrl();
        }
//        Optional<Url> existingUrl = urlRepository.findByOriginalUrl(originalUrl);
//        if (existingUrl.isPresent()) {
//            return existingUrl.get();  // Correct way to retrieve the value
//        }


        String shortUrl = generateShortUrl();
        //String category = aiService.categorizeUrl(originalUrl);

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        url.setCreatedAt(LocalDateTime.now());
        url.setCategory("");

        urlRepository.save(url);
        return url.getShortUrl();
    }

    public String expandUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));
    }

    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            shortUrl.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        }
        return shortUrl.toString();
    }
}
