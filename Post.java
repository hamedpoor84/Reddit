import java.util.ArrayList;
import java.util.UUID;

public class Post {
    private String title ;
    private String description ;
    private User creator ;
    private ArrayList<String> tags = new ArrayList<>();

    private ArrayList<Comment> comments = new ArrayList<>();

    private ArrayList<User> voters = new ArrayList<>();
    private ArrayList<Integer> votes = new ArrayList<>();
    private UUID uuid ;

    private int likes ;

    private int dislikes ;
    private Subreddit subreddit ;

    public Post(String title, String description, User creator, Subreddit subreddit , UUID uuid) {
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.subreddit = subreddit;
        this.uuid = uuid;
    }

    public void ShowComments()
    {
        for (Comment comment : comments)
        {
            System.out.print("  ");
            System.out.println(comment.getText() +"   "+ comment.getLikes() + "likes   " + comment.getDislikes());
        }
    }

    public void ShowTags()
    {
        for (String tag : tags)
        {
            System.out.println(tag);
        }
    }

    public void Add_Tag (String tag)
    {
        tags.add(tag);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public Subreddit getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(Subreddit subreddit) {
        this.subreddit = subreddit;
    }

    public void AddComment (Comment comment)
    {
        comments.add(comment) ;
    }

    public void Show ()
    {
        System.out.println("Title : -" + title + "-");
        System.out.println("text : " + description);
        System.out.println(likes + " likes   " + dislikes + " dislikes Code : " + uuid );
        ShowComments();
    }

    public void upVote (User user){
        for (User user1 : voters)
        {
            if (user.equals(user1)){
                if (votes.get(voters.indexOf(user1)) == 1){
                    votes.set(voters.indexOf(user1) , 0) ;
                    likes -= 1 ;
                    return;
                } else if (votes.get(voters.indexOf(user1)) == 0) {
                    votes.set(voters.indexOf(user1) , 1) ;
                    likes += 1 ;
                    return;
                } else if (votes.get(voters.indexOf(user1)) == -1) {
                    votes.set(voters.indexOf(user1) , 1) ;
                    dislikes -= 1 ;
                    likes += 1 ;
                    return;
                }
            }
        }
        voters.add(user);
        votes.add(1);
        likes += 1;
    }

    public void downVote(User user)
    {
        for (User user1 : voters)
        {
            if (user.equals(user1)){
                if (votes.get(voters.indexOf(user1)) == 1){
                    votes.set(voters.indexOf(user1) , -1) ;
                    likes -= 1 ;
                    dislikes += 1 ;
                    return;
                } else if (votes.get(voters.indexOf(user1)) == 0) {
                    votes.set(voters.indexOf(user1) , -1) ;
                    dislikes += 1 ;
                    return;
                } else if (votes.get(voters.indexOf(user1)) == -1) {
                    votes.set(voters.indexOf(user1) , 0) ;
                    dislikes -= 1 ;
                    return;
                }
            }
        }
        voters.add(user);
        votes.add(-1);
        dislikes += 1 ;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
