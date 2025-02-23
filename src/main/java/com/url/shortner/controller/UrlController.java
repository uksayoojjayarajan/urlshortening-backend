package com.url.shortner.controller;


import com.url.shortner.model.Url;
import com.url.shortner.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/shorturl")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UrlController {
    private final UrlService urlService;
    @PostMapping("/shorten")
    public Map<String, String> shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("longUrl"); // ✅ Use "longUrl" instead of "url"
        String shortUrl = urlService.shortenUrl(originalUrl);
        return Map.of("shortUrl", shortUrl);
    }

//@PostMapping("/shorten")
//public Map<String, Object> shortenUrl(@RequestBody Map<String, String> request) {
//    String originalUrl = request.get("url");
//    Url url = urlService.shortenUrl(originalUrl);  // Modified method
//
//    return Map.of(
//            "originalUrl", url.getOriginalUrl(),
//            "shortUrl", url.getShortUrl(),
//            "createdAt", url.getCreatedAt().toString(),
//            "category", url.getCategory()
//    );
//}


    @GetMapping("/expand/{shortUrl}")
    public Map<String, String> expandUrl(@PathVariable String shortUrl) {
        String originalUrl = urlService.expandUrl(shortUrl);
        return Map.of("originalUrl", originalUrl);
    }
}

