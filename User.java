import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String name ;
    private String user_name ; // all username should be different .
    private int password ;
    private String email ;
    private ArrayList<Comment> comments ;
    private ArrayList<User> friends ;
    private ArrayList<Subreddit> joined_subreddits ;
    private ArrayList<Post> up_voted_post ;
    private ArrayList<Subreddit> user_subreddits ;
    private ArrayList<Post> down_voted_post ;
    private UUID uuid ;
    private String bio ;
    private ArrayList<Post> created_posts ;
    private Post[] timeline ;

    public Post[] getTimeline() {
        return timeline;
    }

    public void setTimeline(Post[] timeline) {
        this.timeline = timeline;
    }

    public User(String name, String user_name, String password, String email , UUID uuid) {
        this.name = name;
        this.user_name = user_name;
        this.password = password.hashCode();
        this.email = email;
        this.uuid = uuid ;
        timeline = new Post[10] ;
        comments = new ArrayList<>() ;
        friends = new ArrayList<>() ;
        up_voted_post = new ArrayList<>() ;
        down_voted_post = new ArrayList<>() ;
        created_posts = new ArrayList<>() ;
    }

    public void Show_Admin_Subreddit()
    {
        for (Subreddit subreddit : user_subreddits)
        {
            System.out.print("  ");
            System.out.println(subreddit.getName());
        }
    }

    public void Show_Joined_Subreddits ()
    {
        for (Subreddit subreddit : joined_subreddits)
        {
            System.out.print("  *");
            System.out.println(subreddit.getName());
        }
    }

    public void Show_friends()
    {
        for (User user : friends)
        {
            System.out.print("  *");
            System.out.println(user.getUser_name());
        }
    }

    public void Show_up_voted_post()
    {
        for (Post post : up_voted_post)
        {
            System.out.print("  *");
            System.out.println(post.getTitle());
        }
    }
    public void Show_down_voted_post()
    {
        for (Post post : down_voted_post)
        {
            System.out.print("  *");
            System.out.println(post.getTitle());
        }
    }
    public String getUser_name() {
        return user_name;
    }

    public void Add_user_subreddits (Subreddit subreddit)
    {
        user_subreddits.add(subreddit) ;
    }

    public void Join_Subreddit (Subreddit subreddit){joined_subreddits.add(subreddit) ; }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ArrayList<Subreddit> getJoined_subreddits() {
        return joined_subreddits;
    }

    public void setJoined_subreddits(ArrayList<Subreddit> joined_subreddits) {
        this.joined_subreddits = joined_subreddits;
    }

    public ArrayList<Post> getUp_voted_post() {
        return up_voted_post;
    }

    public void setUp_voted_post(ArrayList<Post> up_voted_post) {
        this.up_voted_post = up_voted_post;
    }

    public ArrayList<Post> getDown_voted_post() {
        return down_voted_post;
    }

    public void setDown_voted_post(ArrayList<Post> down_voted_post) {
        this.down_voted_post = down_voted_post;
    }

    public ArrayList<Subreddit> getUser_subreddits() {
        return user_subreddits;
    }

    public void setUser_subreddits(ArrayList<Subreddit> user_subreddits) {
        this.user_subreddits = user_subreddits;
    }

    public void Add_Comments (Comment comment)
    {
        comments.add(comment);
    }

    public void Show_Timeline () // i should choose something for post that uniq
    {
        for (Post post : timeline )
        {
            System.out.println(post.getTitle() + "   likes : " + post.getLikes() + "   dislikes : " + post.getDislikes());
        }
    }

    public void Show_Profile ()
    {
        System.out.println(user_name);
        System.out.println("admin subreddit : ");
        Show_Admin_Subreddit();
        System.out.println("joined subreddit : ");
        Show_Joined_Subreddits();
        System.out.println("comments : ");
        Show_Comments();

    }

    public void Show_Comments ()
    {
        for (Comment comment : comments)
        {
            System.out.printf("%-40s", comment.getText()); // This will output "42        "
            System.out.printf("%-5s", comment.getLikes());
            System.out.print(" likes   ");
            System.out.printf("%-5s", comment.getDislikes());
            System.out.println(" dislikes");
        }
    }
}