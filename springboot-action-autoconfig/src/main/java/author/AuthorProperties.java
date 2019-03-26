package author;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom")
public class AuthorProperties {
    
	public static final String DEFAULT_AUTHOR = "LiangGzone";

	public String author = DEFAULT_AUTHOR;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
