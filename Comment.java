import java.util.UUID;

public class Comment {
    private Post post;
    private int likes;
    private int dislikes;
    private User creator;
    private UUID uuid ;
    private String text;


    public Comment(Post post , User creator, String text , UUID uuid) {
        this.creator = creator;
        this.text = text;
        this.uuid = uuid ;
        likes = 0;
        dislikes = 0;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
