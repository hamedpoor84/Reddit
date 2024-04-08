import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reddit {
    private ArrayList<User> users;
    private ArrayList<Post> posts;
    private ArrayList<Subreddit> subreddits;
    private ArrayList<UUID> uuids;
    public boolean LogInUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("email : ");
        String email = scanner.next();
        System.out.print("password : ");
        String password = scanner.next();
        for (User user : users) {
            if (email.equals(user.getEmail())) {
                if (password.hashCode() == user.getPassword()) {
                    return true;
                }
                System.out.println("your password is incorrect .");
                return false;
            }
        }
        System.out.println("your email not found . ");
        return false;
    }

    public void SignUpUser() {
        UUID uuid = UUID.randomUUID() ;
        uuids.add(uuid);
        Scanner scanner = new Scanner(System.in);
        System.out.print("name : ");
        String name = scanner.next();
        System.out.print("user_name : ");
        String user_name = scanner.next();
        System.out.print("email : ");
        String email = scanner.next();
        System.out.print("password : ");
        String password = scanner.next();
        if (CheckUsername(user_name) && CheckEmail(email) && IsValidEmail(email)) {
            User user = new User(name, user_name, password, email , uuid);
            users.add(user);
        } else {
            System.out.println("Your signup does not successful");
        }
    }

    public static boolean IsValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean CheckUsername(String user_name) {
        for (User user : users) {
            if (user.getUser_name().equals(user_name)) {
                return false;
            }
        }
        return true;
    }

    public boolean CheckEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public boolean UserExistence(String user_name) {
        for (User user : users) {
            if (user.getUser_name().equals(user_name)) {
                return true;
            }
        }
        return false;
    }

    public User FindUser(String user_name) // for find and use users // i don't know should I use password or no .
    {
        for (User user : users) {
            if (user.getUser_name().equals(user_name)) {
                return user;
            }
        }
        return users.getFirst(); // this is for no return statement but do nothing
    }

    public boolean SubredditExistence(String name) {
        for (Subreddit subreddit : subreddits) {
            if (subreddit.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Subreddit findSubreddit(String name) // for find and use subreddit
    {
        for (Subreddit subreddit : subreddits) {
            if (subreddit.getName().equals(name)) {
                return subreddit;
            }
        }
        return subreddits.getFirst();// this is for no return statement but do nothing
    }

    public Post findPost(String Title) // for find and use post
    {
        for (Post post : posts) {
            if (post.getTitle().equals(Title)) {
                return post;
            }
        }
        return posts.getFirst();// this is for no return statement but do nothing
    }

    public boolean postExistence(String Title) {
        for (Post post : posts) {
            if (post.getTitle().equals(Title)) {
                return true;
            }
        }
        return false;
    }


    public void addAdmin(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("subreddit name : ");
        String name = scanner.next();
        System.out.print("user_name: ");
        String user_name = scanner.next();
        if (SubredditExistence(name) && UserExistence(user_name) && findSubreddit(name).AdminExistence(user)) {
                findSubreddit(name).AddAdmin(FindUser(user_name));
                FindUser(user_name).Add_user_subreddits(findSubreddit(name));
        } else {
            System.out.println("Add admin not successful .");
        }
    }

    public void creatSubreddit(User creator) {
        UUID uuid = UUID.randomUUID() ;
        uuids.add(uuid);
        Scanner scanner = new Scanner(System.in);
        System.out.print("name: ");
        String name = scanner.next();
        System.out.print("info : ");
        String info = scanner.nextLine();
        if (SubredditExistence(name)) {
            System.out.println("subreddit with this name is already exist .");
        } else {
            Subreddit subreddit = new Subreddit(name, creator , info ,uuid);
            creator.Add_user_subreddits(subreddit);
            subreddits.add(subreddit);
        }
    }

    public void joinSubreddit(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("subreddit you want to join: ");
        String name = scanner.next();
        if (SubredditExistence(name)) {
            findSubreddit(name).AddUser(user);
            user.Add_user_subreddits(findSubreddit(name));
            subreddits.add(findSubreddit(name));
        } else {
            System.out.println("subreddit doesn't exist .");
        }
    }

    public void changePersonalInfo(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("what do you want to change ?");
        System.out.println("1-user_name    2-password    3-email    4-name    5-bio");
        switch (scanner.nextInt()) {
            case 1:
                System.out.print("New username :");
                user.setUser_name(scanner.next());
                break;
            case 2:
                System.out.print("New password :");
                user.setPassword(scanner.next().hashCode());
                break;
            case 3:
                System.out.print("New email :");
                user.setEmail(scanner.next());
                break;
            case 4:
                System.out.print("New name :");
                user.setName(scanner.next());
                break;
            case 5:
                System.out.println("New bio :");
                user.setBio(scanner.nextLine());
                break;
        }
    }

    public void creatComment(String Title, User user, String text)
    {
        if (postExistence(Title))
        {
            UUID uuid = UUID.randomUUID() ;
            uuids.add(uuid);
            Comment comment = new Comment(findPost(Title) , user , text , uuid) ;
            findPost(Title).AddComment(comment);
            user.Add_Comments(comment);
        } else {
            System.out.println("post doesn't exist");
        }
    }

    public void creatPost(User creator)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("subreddit : ");
        String name = scanner.next();
        if (SubredditExistence(name))
        {
            if (findSubreddit(name).UserExistence(creator))
            {
                UUID uuid = UUID.randomUUID() ;
                uuids.add(uuid);
                System.out.print("title : ");
                String title = scanner.next();
                System.out.print("description : ");
                String description = scanner.nextLine();
                Post post = new Post(title ,description ,creator , findSubreddit(name) , uuid) ;
                posts.add(post) ;
                creator.Add_user_subreddits(findSubreddit(name));
                creator.Join_Subreddit(findSubreddit(name));
                findSubreddit(name).AddPost(post);
            } else {
                System.out.println("you are not joined to this subreddit .");
            }
        } else {
            System.out.println("subreddit doesn't exist .");
        }
    }

    public ArrayList<String> searchbyName(String query)
    {
        ArrayList<String> result = new ArrayList<>() ;
        result.addAll(searchsubredditsbyname(query)) ;
        result.addAll(searchUsersbyName(query)) ;
        result.addAll(searchPostsbyName(query)) ;
        return (result) ;
    }


    public List<String> searchsubredditsbyname(String query) {
        List<String> results = new ArrayList<>();
        for (Subreddit subreddit : subreddits) {
            if (subreddit.getName().toLowerCase().contains(query.toLowerCase())) {
                results.add("S/" + subreddit.getName());
            }
        }
        return results;
    }

    public List<String> searchPostsbyName(String query) {
        List<String> results = new ArrayList<>();
        for (Post post : posts) {
            if (post.getTitle().toLowerCase().contains(query.toLowerCase())) {
                results.add("P/" + post.getTitle());
            }
        }
        return results;
    }

    public List<String> searchUsersbyName(String query) {
        List<String> results = new ArrayList<>();
        for (User user : users) {
            if (user.getUser_name().toLowerCase().contains(query.toLowerCase())) {
                results.add(user.getUser_name());
            }
        }
        return results;
    }

    public void SearchbyUUID (String query)
    {
        if (!searchSubredditsbyUUID(query).equals(null)){
            searchSubredditsbyUUID(query).forShowSubreddit();
        } else if (!searchPostsbyUUID(query).equals(null)){
            searchPostsbyUUID(query).Show();
        } else if (!searchUsersbyNameUUID(query).equals(null)){
            searchUsersbyNameUUID(query).Show_Profile();
        }
    }


    public Subreddit searchSubredditsbyUUID(String query) {
        for (Subreddit subreddit : subreddits) {
            if (subreddit.getUuid().toString().equals(query)) {
                return (subreddit);
            }
        }
        return null;
    }

    public Post searchPostsbyUUID(String query) {
        for (Post post : posts) {
            if (post.getUuid().toString().equals(query)) {
                return (post);
            }
        }
        return null;
    }

    public User searchUsersbyNameUUID(String query) {
        for (User user : users) {
            if (user.getUuid().toString().equals(query)) {
                return (user);
            }
        }
        return null;
    }
}
