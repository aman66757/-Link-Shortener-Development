import java.util.HashMap;
import java.util.Map;

public class UrlShortener {
    private static final String BASE_URL = "http://short.ly/";
    private Map<String, String> urlMap;
    private Map<String, String> reverseMap;

    public UrlShortener() {
        urlMap = new HashMap<>();
        reverseMap = new HashMap<>();
    }

    public String generateShortUrl(String longUrl) {
        if (reverseMap.containsKey(longUrl)) {
            return reverseMap.get(longUrl);
        }

        String shortUrl = BASE_URL + Integer.toHexString(longUrl.hashCode());
        if (urlMap.containsKey(shortUrl)) {
            throw new RuntimeException("Hash collision occurred! Try again.");
        }
        
        urlMap.put(shortUrl, longUrl);
        reverseMap.put(longUrl, shortUrl);
        return shortUrl;
    }

    public String expandShortUrl(String shortUrl) {
        String longUrl = urlMap.get(shortUrl);
        if (longUrl == null) {
            throw new IllegalArgumentException("Invalid short URL.");
        }
        return longUrl;
    }
    
    public boolean isDuplicateLongUrl(String longUrl) {
        return reverseMap.containsKey(longUrl);
    }

    public Map<String, String> getUrlMappings() {
        return urlMap;
    }
}
