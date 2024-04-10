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
    Subreddit subreddit = new Subreddit("fun" , user , "this subreddit is for fun meme " , UUID.randomUUID());
    Subreddit subreddit1 = new Subreddit("function" , user , "java programmers " , UUID.randomUUID());
    Subreddit subreddit2 = new Subreddit("moon" , user , "the beauty of night " , UUID.randomUUID());
    Subreddit subreddit3 = new Subreddit("lifestyle" , user1 , "best possible life for us" , UUID.randomUUID());
    Subreddit subreddit4 = new Subreddit("life" , user1 , "All news of every where" , UUID.randomUUID());
    Subreddit subreddit5 = new Subreddit("game" , user1 , "Analysis the best newest game in the world  " , UUID.randomUUID());
    Subreddit subreddit6 = new Subreddit("internet" , user2 , " explain of the abillities of internet  " , UUID.randomUUID());
    Subreddit subreddit7 = new Subreddit("soccer" , user2 , "football 120  " , UUID.randomUUID());
    Subreddit subreddit8 = new Subreddit("sport" , user2 , "news of sports " , UUID.randomUUID());
        user.addUserSubreddits(subreddit);
        subreddits.add(subreddit);
        user.addUserSubreddits(subreddit1);
        subreddits.add(subreddit1);
        user.addUserSubreddits(subreddit2);
        subreddits.add(subreddit2);
        user1.addUserSubreddits(subreddit3);
        subreddits.add(subreddit3);
        user1.addUserSubreddits(subreddit4);
        subreddits.add(subreddit4);
        user1.addUserSubreddits(subreddit5);
        subreddits.add(subreddit5);
        user2.addUserSubreddits(subreddit6);
        subreddits.add(subreddit6);
        user2.addUserSubreddits(subreddit7);
        subreddits.add(subreddit7);
        user2.addUserSubreddits(subreddit8);
        subreddits.add(subreddit8);

    Post post = new Post("ronaldo" , "what's th ebest advise for next generation ? BELIVE " , user , subreddit7 , UUID.randomUUID());
    Post post1 = new Post("moon light" , "the moon light is the couse of ocean " , user , subreddit2 , UUID.randomUUID());
    Post post2 = new Post("mancity" , "man city wins all its last nine game " , user , subreddit7 , UUID.randomUUID());
    Post post3 = new Post("chelsy" , "in this season chelsi has very bad conditions " , user1 , subreddit7 , UUID.randomUUID());
    Post post4 = new Post("catch moon" , "the catch moon style now is a fameous mode of pictures " , user1 , subreddit2 , UUID.randomUUID());
    Post post5 = new Post("reach the moon" , "america for the first time in the world travel to the moon " , user1 , subreddit2 , UUID.randomUUID());
    Post post6 = new Post("fortnite" , "fort has the very good graphic " , user2 , subreddit5 , UUID.randomUUID());
    Post post7 = new Post("fifa" , "fifa no longer support the fifa soccer game . " , user2 , subreddit5 , UUID.randomUUID());
    Post post8 = new Post("pubg" , "pubg was introduced as the best shouting game in this month " , user2 , subreddit5 , UUID.randomUUID());
        posts.add(post) ;
        user.addUserPost(post);
        subreddit7.AddPost(post);
        posts.add(post1) ;
        user.addUserPost(post1);
        subreddit2.AddPost(post1);
        posts.add(post2) ;
        user.addUserPost(post2);
        subreddit7.AddPost(post2);
        posts.add(post3) ;
        user1.addUserPost(post3);
        subreddit7.AddPost(post3);
        posts.add(post4) ;
        user1.addUserPost(post4);
        subreddit2.AddPost(post4);
        posts.add(post5) ;
        user1.addUserPost(post5);
        subreddit2.AddPost(post5);
        posts.add(post6) ;
        user2.addUserPost(post6);
        subreddit5.AddPost(post6);
        posts.add(post7) ;
        user2.addUserPost(post7);
        subreddit5.AddPost(post7);
        posts.add(post8) ;
        user2.addUserPost(post8);
        subreddit5.AddPost(post8);


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
        if (!subreddit.UserExistence(user) && !subreddit.AdminExistence(user)) {
            Scanner scanner = new Scanner(System.in);
            subreddit.AddUser(user);
            user.addUserSubreddits(subreddit);
            subreddits.add(subreddit);
            subreddit.setMember_number(subreddit.getMember_number()+1);
            System.out.println("You have successfully join");
        } else {
            System.out.println("You are already a member");
        }
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

    public void commentForPost ( User user , Post post)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Text : ");
        String text = scanner.nextLine();
        UUID uuid = UUID.randomUUID() ;
        uuids.add(uuid);
        Comment comment = new Comment(post , user , text , uuid) ;
        post.AddComment(comment);
        user.addComments(comment);
    }

    public void commentForComment (User user , Comment comment)
    {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String text = scanner.nextLine();
        UUID uuid = UUID.randomUUID() ;
        uuids.add(uuid);
        Comment newComment = new Comment(comment.getPost() , user , text , uuid) ;
        comment.AddComment(newComment);
        user.addComments(comment);
    }


    public void creatPost(User creator , Subreddit subreddit)
    {
        Scanner scanner = new Scanner(System.in);
        UUID uuid = UUID.randomUUID() ;
        uuids.add(uuid);
        System.out.print("title : ");
        String title = scanner.next();
        System.out.print("description : ");
        scanner.nextLine();
        String description = scanner.nextLine();
        Post post = new Post(title ,description ,creator , subreddit , uuid) ;
        posts.add(post) ;
        creator.addUserPost(post);
        subreddit.AddPost(post);
        System.out.println("the post was created successfully .");
    }


    public ArrayList<Subreddit> searchsubredditsbyname(String query) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Subreddit> results = new ArrayList<>();
        for (Subreddit subreddit : subreddits) {
            if (subreddit.getName().toLowerCase().contains(query.toLowerCase())) {
                results.add(subreddit);
            }
        }
        return results ;
    }

    public ArrayList<Post> searchPostsbyName(String query) {
        ArrayList<Post> results = new ArrayList<>();
        for (Post post : posts) {
            if (post.getTitle().toLowerCase().contains(query.toLowerCase())) {
                results.add(post);
            }
        }
        return results ;
    }

    public ArrayList<User> searchUsersbyName(String query) {
        ArrayList<User> results = new ArrayList<>();
        for (User user : users) {
            if (user.getUserName().toLowerCase().contains(query.toLowerCase())) {
                results.add(user);
            }
        }
        return results ;
    }

    public ArrayList<Subreddit> getSubreddits() {
        return subreddits;
    }

    public ArrayList<Post> getPosts() {
        return posts;
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


    public void deletePost(Post post) {
        // Remove the post from the subreddit's list of posts
        post.getSubreddit().getPosts().remove(post);

        // Remove the post from the list of all posts
        posts.remove(post);

        // Remove the post from the creator's created_posts list
        post.getCreator().getCreatedPosts().remove(post);

        // Remove the post from the creator's down_voted_post list
        post.getCreator().getDownVotedPosts().remove(post);

        // Remove the post from the creator's up_voted_post list
        post.getCreator().getUpVotedPosts().remove(post);

        // Set the reference to the post to null (optional)
        post = null;
    }



    public void deleteSubreddit (Subreddit subreddit )
    {
        for (User user : subreddit.getUsers())
        {
            ArrayList<Subreddit> update = user.getJoined_subreddits();
            update.remove(subreddit);
            user.setJoined_subreddits(update);
        }
        for (User user : subreddit.getUsers())
        {
            ArrayList<Subreddit> update = user.getUserSubreddits();
            update.remove(subreddit);
            user.setUserSubreddits(update);
        }
        subreddit = null ;
    }
}
