import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

public class Subreddit {
    private String name ;
    private User creator ;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();
    private ArrayList<User> admins = new ArrayList<>();
    private UUID uuid ;
    private String info;
    private int member_number ;

    public Subreddit(String name, User creator , String info , UUID uuid) {
        this.info = info;
        this.name = name;
        this.creator = creator;
        admins.add(creator) ;
        this.uuid = uuid ;
        member_number = 1 ;
    }

    public void ShowPosts ()
    {
        int i = 1 ;
        if (!posts.isEmpty()) {
            for (Post post : posts) {
                System.out.print("   " + i + "- " );
                post.Show();
                i++ ;
            }
        } else {
            System.out.println("nothing yet");
        }
    }
    public void AddAdmin (User user)
    {
        if (UserExistence(user))
        {
            if (AdminExistence(user))
            {
                System.out.println("This user is already an admin .");
            } else {
            admins.add(user) ;
            }
        }
        else {
            System.out.println("User not found .");
        }
    }

    public void AddUser (User user)
    {
        if (UserExistence(user))
        {
            if (AdminExistence(user))
            {
                System.out.println("This user is already joined .");
            }
        } else {
            users.add(user) ;
            member_number++ ;
        }
    }

    public void RemoveUser (User user)
    {
        if (UserExistence(user))
        {
            users.remove(user) ;
        }
    }
    public boolean UserExistence (User target_user) // in the subreddit
    {
        for (User user : users)
        {
            if (user.equals(target_user))
            {
                return true ;
            }
        }
        return false ;
    }

    public boolean AdminExistence (User target_user) // in the subreddit
    {
        for (User user : admins)
        {
            if (user.equals(target_user))
            {
                return true ;
            }
        }
        return false ;
    }
    public User FindAdmin (String user_name) // for find and use users
    {
        for (User user : admins)
        {
            if (user.getUserName().equals(user_name))
            {
                return user ;
            }
        }
        return users.getFirst() ; // this is for no return statement but do nothing .
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<User> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<User> admins) {
        this.admins = admins;
    }

    public int getMember_number() {
        return member_number;
    }

    public void setMember_number(int member_number) {
        this.member_number = member_number;
    }

    public void AddPost (Post post)
    {
        posts.add(post);
        for (User user : users)
        {
            Post[] update_timeline = user.getTimeline();
            for (int i = 8 ; i >= 0 ; i--)
            {
                if (i != 0 )
                {
                    update_timeline[i+1] = update_timeline[i] ;
                }
                else {update_timeline[i] = post ;}
            }
        }
    }

    public void forShowSubreddits ()
    {
        System.out.printf("%10s" , name);
        System.out.println(member_number + " member" + "Code : " + uuid);
        System.out.println("Info : " + info);
        System.out.print("popular post : ");
        Post post = posts.stream()
                    .max(Comparator.comparingInt(Post::getLikes))
                .orElse(null);
        assert post != null; // for checking that this condition will be true .
        System.out.println(post.getTitle() + "  " + post.getLikes() + " likes" + post.getDislikes() + " dislike Code : " + post.getUuid());
    }

    public void Show (){
        System.out.print("name : ");
        System.out.printf("%-10s" , name);
        System.out.println(member_number + " member   " + "Code : " + uuid);
        System.out.println("Info : " + info);
        System.out.println("Posts : ");
        ShowPosts();
    }




}