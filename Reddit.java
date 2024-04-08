import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Reddit {
    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Post> posts = new ArrayList<>();
    private final ArrayList<Subreddit> subreddits = new ArrayList<>();
    private final ArrayList<UUID> uuids = new ArrayList<>();
    public User LogInUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("email : ");
        String email = scanner.nextLine();
        System.out.print("password : ");
        int password = scanner.nextLine().hashCode();
        for (User user : users) {
            if (email.equals(user.getEmail())) {
                if (password == user.getPassword()) {
                    return  user;
                }
                System.out.println("your password is incorrect .");
                return null;
            }
        }
        System.out.println("your email not found . ");
        return null;
    }


    public User SignUpUser() {
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
            return user;
        } else {
            System.out.println("Your signup does not successful");
            return null;
        }
    }


    public void viewSubreddits ()
    {
        for (int i = 0 ; i < subreddits.size() ; i++)
        {
            System.out.println(i+1 + "- " + subreddits.get(i).getName());
        }
    }


    public void fakeRed (){
    User user = new User("hamed" , "Hamedpoor84" , "hamed2384" , "hamedpoor84@gmail.com" , UUID.randomUUID());
    User user1 = new User("mobin" , "mobin84" , "mmmmmmmm" , "mobin@gmail.com" , UUID.randomUUID());
    User user2 = new User("amin" , "amin83" , "aaaaaaaa" , "aminghu@gmail.com" , UUID.randomUUID());
    users.add(user);
    users.add(user1);
    users.add(user2);
    }


    public static boolean IsValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean CheckUsername(String user_name) {
        for (User user : users) {
            if (user.getUserName().equals(user_name)) {
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
            if (user.getUserName().equals(user_name)) {
                return true;
            }
        }
        return false;
    }

    public User findUser(String user_name) // for find and use users // i don't know should I use password or no .
    {
        for (User user : users) {
            if (user.getUserName().equals(user_name)) {
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
                findSubreddit(name).AddAdmin(findUser(user_name));
                findUser(user_name).addUserSubreddits(findSubreddit(name));
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
        scanner.nextLine();
        String info = scanner.nextLine();
        if (SubredditExistence(name)) {
            System.out.println("subreddit with this name is already exist .");
        } else {
            Subreddit subreddit = new Subreddit(name, creator , info ,uuid);
            creator.addUserSubreddits(subreddit);
            subreddits.add(subreddit);
            System.out.println("subreddit creat successfully");
        }
    }

    public void joinSubreddit(User user , Subreddit subreddit) {
        Scanner scanner = new Scanner(System.in);
            subreddit.AddUser(user);
            user.addUserSubreddits(subreddit);
            subreddits.add(subreddit);
    }

    public void changePersonalInfo(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("what do you want to change ?");
        System.out.println("1-user_name    2-password    3-email    4-name    5-bio");
        switch (scanner.nextInt()) {
            case 1:
                System.out.print("New username :");
                user.setUserName(scanner.next());
                System.out.println("your user name change successfully");
                break;
            case 2:
                System.out.print("New password :");
                user.setPassword(scanner.next().hashCode());
                System.out.println("your password change successfully");
                break;
            case 3:
                System.out.print("New email :");
                user.setEmail(scanner.next());
                System.out.println("your email change successfully");
                break;
            case 4:
                System.out.print("New name :");
                user.setName(scanner.next());
                System.out.println("your name change successfully");

                break;
            case 5:
                System.out.println("New bio :");
                scanner.nextLine();
                user.setBio(scanner.nextLine());
                System.out.println("your bio change successfully");
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
            user.addComments(comment);
        } else {
            System.out.println("post doesn't exist");
        }
    }

    public void creatPost(User creator , Subreddit subreddit)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("subreddit : ");
        String name = scanner.next();
        if (SubredditExistence(name))
        {
            if (findSubreddit(name).UserExistence(creator) || findSubreddit(name).AdminExistence(creator))
            {
                UUID uuid = UUID.randomUUID() ;
                uuids.add(uuid);
                System.out.print("title : ");
                String title = scanner.next();
                System.out.print("description : ");
                scanner.nextLine();
                String description = scanner.nextLine();
                Post post = new Post(title ,description ,creator , findSubreddit(name) , uuid) ;
                posts.add(post) ;
                creator.addUserPost(post);
                creator.joinSubreddit(findSubreddit(name));
                findSubreddit(name).AddPost(post);
            } else {
                System.out.println("you are not joined to this subreddit .");
            }
        } else {
            System.out.println("subreddit doesn't exist .");
        }
    }

    public void searchbyName(String query)
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> result = new ArrayList<>() ;
        result.addAll(searchsubredditsbyname(query)) ;
        result.addAll(searchUsersbyName(query)) ;
        result.addAll(searchPostsbyName(query)) ;
       for (int i = 0 ; i < result.size() ; i++)
       {
           System.out.println(i+1 + "- " + result.get(i));
       }
        System.out.print("Select : ");
        int index = scanner.nextInt() - 1;
        String target = result.get(index);
        if ( target.charAt(0) == 'U'){
            findUser(target.substring(2)).showProfile();
        } else if (target.charAt(0) == 'S'){
            findSubreddit(target.substring(2)).Show();
        } else if (target.charAt(0) == 'P'){
            findPost(target.substring(2)).Show();
        }
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
            if (user.getUserName().toLowerCase().contains(query.toLowerCase())) {
                results.add("U/" + user.getUserName());
            }
        }
        return results;
    }

    public void SearchbyUUID (String query)
    {
        if (searchSubredditsbyUUID(query) != null){
            searchSubredditsbyUUID(query).Show();
        } else if (searchPostsbyUUID(query) != null){
            searchPostsbyUUID(query).Show();
        } else if (searchUsersbyNameUUID(query) != null){
            searchUsersbyNameUUID(query).showProfile();
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
