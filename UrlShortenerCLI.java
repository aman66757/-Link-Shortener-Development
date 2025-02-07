import java.util.Scanner;

public class UrlShortenerCLI {
    private static final UrlShortener urlShortener = new UrlShortener();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("URL Shortener CLI:");
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the long URL: ");
                    String longUrl = scanner.nextLine();
                    try {
                        if (urlShortener.isDuplicateLongUrl(longUrl)) {
                            System.out.println("This URL has already been shortened.");
                        } else {
                            String shortUrl = urlShortener.generateShortUrl(longUrl);
                            System.out.println("Shortened URL: " + shortUrl);
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter the short URL: ");
                    String shortUrl = scanner.nextLine();
                    try {
                        String expandedUrl = urlShortener.expandShortUrl(shortUrl);
                        System.out.println("Expanded URL: " + expandedUrl);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    exit = true;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option! Try again.");
            }
        }

        scanner.close();
    }
}
