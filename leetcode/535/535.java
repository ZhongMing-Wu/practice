public class Codec {

    Random r = new Random();
    int key = r.nextInt(Integer.MAX_VALUE);
    Map<String, String> toLongUrl = new HashMap<>();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        while(toLongUrl.containsKey(key)) {
            key = r.nextInt(Integer.MAX_VALUE);
        }
        toLongUrl.put(String.valueOf(key), longUrl);
        return "http://tinyurl.com/" + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String suffix = shortUrl.replace("http://tinyurl.com/", "");
        String longUrl = toLongUrl.get(suffix);
        return longUrl;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));