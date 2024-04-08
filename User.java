import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class User {
    private String name ;
    private String user_name ; // all username should be different .
    private int password ;
    private String email ;
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<User> friends = new ArrayList<>();
    private ArrayList<Subreddit> joined_subreddits = new ArrayList<>();
    private ArrayList<Post> up_voted_post = new ArrayList<>();
    private ArrayList<Subreddit> user_subreddits = new ArrayList<>();
    private ArrayList<Post> down_voted_post = new ArrayList<>();
    private UUID uuid ;
    ArrayList<Post> created_posts = new ArrayList<>();
    private String bio ;

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
    }
    public ArrayList<Post> getCreatedPosts() {
        return created_posts;
    }

    public void setCreatedPosts(ArrayList<Post> created_posts) {
        this.created_posts = created_posts;
    }


    public void showAdminSubreddit()
    {
        int i = 1 ;
        if (!user_subreddits.isEmpty()) {
            for (Subreddit subreddit : user_subreddits) {
                System.out.print("   " + i );
                System.out.println(subreddit.getName());
                i++;
            }
        } else {
            System.out.println("nothing yet");
        }
    }

    public void showJoinedSubreddits ()
    {
        int i = 1;
        if (!joined_subreddits.isEmpty()) {
            for (Subreddit subreddit : joined_subreddits) {
                System.out.print("   " + i);
                System.out.println(subreddit.getName());
                i++ ;
            }
        } else {
            System.out.println("    nothing yet");
        }
    }

    public void showFriends()
    {
        int i = 1 ;
        if (!friends.isEmpty()) {
            for (User user : friends) {
                System.out.print("   " + i);
                System.out.println(user.getUserName());
                i++;
            }
        } else {
            System.out.println("    nothing yet");
        }
    }

    public void showUpVotedPost()
    {
        int i = 1 ;
        if (!up_voted_post.isEmpty()) {
            for (Post post : up_voted_post) {
                System.out.print("   " + i);
                System.out.println(post.getTitle());
                i++ ;
            }
        } else {
            System.out.println("    nothing yet");
        }
    }
    public void showDownVotedPost()
    {
        int i = 1 ;
        if (down_voted_post.isEmpty()) {
            for (Post post : down_voted_post) {
                System.out.print("   " + i);
                System.out.println(post.getTitle());
                i++;
            }
        } else {
            System.out.println("    nothing yet");
        }
    }
    public String getUserName() {
        return user_name;
    }

    public void addUserSubreddits(Subreddit subreddit)
    {
        joined_subreddits.add(subreddit) ;
    }

    public void joinSubreddit(Subreddit subreddit){joined_subreddits.add(subreddit) ; }

    public void setUserName(String user_name) {
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

    public void setUserSubreddits(ArrayList<Subreddit> user_subreddits) {
        this.user_subreddits = user_subreddits;
    }

    public void addComments(Comment comment)
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

    public void showProfile()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name : " + name);
        System.out.println("user_name : " + user_name);
        System.out.println("email : " + email);
        System.out.println("bio : " + bio);
        System.out.println("admin subreddit : ");
        showAdminSubreddit();
        System.out.println("joined subreddit : ");
        showJoinedSubreddits();
        System.out.println("comments : ");
        showComments();
        System.out.println("1- admin subreddit");
        System.out.println("2- joined subreddit");
        System.out.println("3- comments");
        String options = scanner.next();
    }

    public void showComments ()
    {
        int i = 1 ;
        if (!comments.isEmpty()) {
            for (Comment comment : comments) {
                System.out.println("subreddit : " + comment.getPost().getSubreddit().getName());
                System.out.printf("%-40s", comment.getText()); // This will output "42        "
                System.out.printf("%-5s", comment.getLikes());
                System.out.println(" likes   ");
                System.out.printf("%-5s", comment.getDislikes());
                System.out.println(" dislikes");
            }
        } else {
            System.out.println("    nothing yet");
        }
    }

    public void addUserPost (Post post)
    {
        created_posts.add(post) ;
    }
}