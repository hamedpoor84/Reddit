import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Main{
    public static Reddit reddit = new Reddit();
    public static void main(String[] args) {
        reddit.fakeRed();
        while (true) {
            User user = LogInSignUp();
            if (user.getUserName().equals("Exit")) {
                System.out.println("thanks for your time :+");
                return;
            } else {
                System.out.println("your sign up successful");
                RunReddit(user);
            }
        }
    }
    public static User LogInSignUp () {
        User user = new User("Exit" , "Exit" , "exitExit" , "E@E.E" , UUID.randomUUID() ) ;
        boolean access = false;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1- Log in");
            System.out.println("2- Sign up");
            System.out.println("3- Exit");
            String option = scanner.next();
            switch (option) {
                case "1" -> {
                    user = reddit.LogInUser();
                    if (user != null) {
                        return user;
                    }
                }
                case "2" -> {
                    user = reddit.SignUpUser();
                    if (user != null) {
                        return user;
                    }
                }
                case "3" -> {
                    return user ;
                }default -> System.out.println("choose an option please");
            }
        }
    }

    public static void RunReddit (User user){
        while (true) {
            user.setCondition("Online");
            user.startTime = System.currentTimeMillis();
            Scanner scanner = new Scanner(System.in);
            System.out.println("1- Profile");
            System.out.println("2- Creat Subreddit");
            System.out.println("3- Search");
            System.out.println("4- Change personal info");
            System.out.println("5- my Page"); // I have this option in subreddit . should I have this here ?
            System.out.println("6- View Subreddits");
            System.out.println("7- My Subreddits ");
            System.out.println("8- Add admin"); // TODO
            System.out.println("9- Exit");
            String option = scanner.next();
            switch (option) {
                case "1" -> User(user , user ) ;
                case "2" -> reddit.creatSubreddit(user);
                case "3" -> {
                    System.out.println("Search for what : ");
                    System.out.println("1- Subreddit");
                    System.out.println("2- User");
                    System.out.println("3- Post");
                    String option1 = scanner.next();
                    System.out.print("target : ");
                    String target = scanner.next();
                    if (option1.equals("1"))
                    {
                        Subreddit(user , selectSubreddit(user , reddit.searchsubredditsbyname(target)));
                    } else if (option1.equals("2")) {
                        User(user , selectUser(user , reddit.searchUsersbyName(target)));
                    } else if (option1.equals("3")) {
                        Post(user , selectPost(user , reddit.searchPostsbyName(target)));
                    }
                }
                case "4" -> reddit.changePersonalInfo(user);
                case "5" -> timeLine(user);
                case "6" -> {
                    reddit.viewSubreddits();
                    System.out.println("1- Select Subreddit");
                    System.out.println("2- Menu");
                    String option1 = scanner.next();
                    if (option1.equals("1")){
                        Subreddit(user , selectSubreddit(user , reddit.getSubreddits()));
                    } else if (option1.equals("2")) {
                        RunReddit(user);
                    }
                }
                case "7" -> {
                    Subreddit(user , selectSubreddit(user , user.getJoined_subreddits()));
                }
                case "8" -> {
                    reddit.addAdmin(user);
                }
                case "9" -> {
                    user.startTime = System.currentTimeMillis();
                    return;
                } default -> System.out.println("choose an option please");
            }
            System.out.println("______________________________________");
        }
    }
    public static void Post(User user, Post post)
    {
        newPage();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            post.Show();
            System.out.println("1- Add Comment\n2- Up vote\n3- Down vote\n4- creator\n5- subreddit\n6- select comment\n7- previous page\n8- Explore\n9- Menu");
            if (post.getSubreddit().AdminExistence(user))
            {
                System.out.println("options for Admin : ");
                System.out.println("11- Delete");
            }
            if (user.equals(post.getCreator()))
            {
                System.out.println("options for creator : ");
                System.out.println("10- Edit");
                System.out.println("11- Delete");
            }
            String option = scanner.next();
            switch (option) {
                case "1" -> reddit.commentForPost(user, post);
                case "2" -> post.upVote(user);
                case "3" -> post.downVote(user);
                case "4" -> User(user, post.getCreator());
                case "5" -> Subreddit(user, post.getSubreddit());
                case "6" -> Comment(user, Objects.requireNonNull(selectComment(user, post.getComments())));
                case "7" -> {
                    return;
                }
                case "8" -> timeLine(user);
                case "9" -> RunReddit(user);
                case "10" -> {
                    post.Edit();
                }
                case "11" -> {
                    reddit.deletePost(post);
                }
                default -> System.out.println("choose an option please");
            }
        }
    }

    public static void Subreddit(User user, Subreddit subreddit)
    {
        newPage();
        while (true) {
//            if (!subreddit.AdminExistence(user)) {
                Scanner scanner = new Scanner(System.in);
                subreddit.Show();
                System.out.println("options :");
                System.out.println("1- Join");
                System.out.println("2- Creat Post");
                System.out.println("3- Select Post ");
                System.out.println("4- previous page");
                System.out.println("5- Explore");
                System.out.println("6- Menu");
                String option = scanner.next();
                switch (option) {
                    case "1" -> reddit.joinSubreddit(user, subreddit);
                    case "2" -> reddit.creatPost(user, subreddit);
                    case "3" -> {
                        Post(user, selectPost(user, subreddit.getPosts()));
                    }
                    case "4" -> {
                        return;
                    }
                    case "5" -> {
                        timeLine(user);
                    }
                    case "6" -> RunReddit(user);
                    default -> System.out.println("choose an option please");
//                }
//            } else {
//                Scanner scanner = new Scanner(System.in);
//                subreddit.Show();
//                System.out.println("options :");
//                System.out.println("1- Creat Post");
//                System.out.println("2- Select Post ");
//                System.out.println("3- Users");
//                System.out.println("4- Admins");
//                System.out.println("5- delete User");
//                System.out.println("6- delete Admin");
//                //System.out.println("5- delete comment");
//                System.out.println("7- previous page");
//                System.out.println("8- Explore");
//                System.out.println("9- Menu");
//                String option = scanner.next();
//                switch (option) {
//                    case "1" -> reddit.joinSubreddit(user, subreddit);
//                    case "2" -> reddit.creatPost(user, subreddit);
//                    case "3" -> {
//                        Post(user, selectPost(user, subreddit.getPosts()));
//                    }
//                    case "4" -> {
//                    }
//                    case "5" -> {
//
//                    }
//                    case "6" -> {
//
//
//                    }
//                    case "7" -> {
//                        return;
//                    }
//                    case "8" -> {
//                        timeLine(user);
//                    }
//                    case "9" -> RunReddit(user);
//                    default -> System.out.println("choose an option please");
//                }
            }
        }
    }

    public static void Comment(User user, Comment comment)
    {
        newPage();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            comment.show();
            comment.showComments();
            System.out.println("options : ");
            System.out.println("1- Creator");
            System.out.println("2- Post");
            System.out.println("3- Subreddit");
            System.out.println("4- Reply");
            System.out.println("5- previous page");
            System.out.println("6- Explore");
            System.out.println("7- Menu");
            String option = scanner.next();
            switch (option) {
                case "1" -> User(user, comment.getCreator());
                case "2" -> Post(user, comment.getPost());
                case "3" -> Subreddit(user, comment.getPost().getSubreddit());
                case "4" -> reddit.commentForComment(user, comment);
                case "5" -> {
                    return;
                }
                case "6" -> timeLine(user);
                case "7" -> RunReddit(user);
                default -> System.out.println("choose an option please");
            }
        }
    }

    public static void User(User main_user, User user){
        newPage();
        while (true) {
            if (!main_user.equals(user))
            {
                long EndTime = System.currentTimeMillis();
                long Time = EndTime - user.startTime;
                user.setCondition(Time + " ago ");
            }
            Scanner scanner = new Scanner(System.in);
            user.showProfile();
            System.out.println("options :");
            if (main_user.equals(user))
            {

            }
            else if (main_user.followingExistence(user)) {
                System.out.println("1- UnFollow");
            } else {
                System.out.println("1- Follow");
            }
            System.out.println("2- Select Post ");
            System.out.println("3- Select Comment");
            System.out.println("4- Select subreddit from UserSubreddit");
            System.out.println("5- Select Subreddit from joinSubreddit");
            System.out.println("6- Followers");
            System.out.println("7- Followings");
            System.out.println("8- previous page");
            System.out.println("9- Explore");
            System.out.println("10- Menu");
            String option = scanner.next();
            switch (option) {
                case "1" -> {

                    if (main_user.equals(user))
                    {
                        System.out.println("you don't have this option .");
                    }
                    else if (main_user.followingExistence(user)) {
                        main_user.unFollow(user);
                        user.unFollower(main_user);
                    } else {
                        main_user.Follow(user);
                        user.Follower(main_user);
                    }
                }
                case "2" -> Post(main_user, selectPost(user, user.created_posts));
                case "3" -> Comment(main_user, selectComment(main_user, user.getComments()));
                case "4" -> Subreddit(main_user, selectSubreddit(main_user, user.getUserSubreddits()));
                case "5" -> Subreddit(main_user, selectSubreddit(main_user, user.getJoined_subreddits()));
                case "6" ->{
                    user.showFollowers();
                    System.out.println("1- Select\n2- Back");
                    String option1 = scanner.next();
                    if (option1.equals("1"))
                    {
                        User( main_user ,selectUser(main_user , user.getFollowers()));
                    } else if (option1.equals("2")) {

                    }
                }
                case "7" ->{
                    user.showFollowings();
                    System.out.println("1- Select\n2- Back");
                    String option1 = scanner.next();
                    if (option1.equals("1"))
                    {
                        User( main_user ,selectUser(main_user , user.getFollowings()));
                    } else if (option1.equals("2")) {

                    }
                }
                case "8" -> {
                    return;
                }
                case "9" -> timeLine(main_user);
                case "10" -> RunReddit(main_user);
                default -> System.out.println("choose an option please");
            }
        }
    }

    public static void timeLine(User user)
    {
        newPage();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            user.showTimeline();
            System.out.println("Option : ");
            System.out.println("1- Choose Post");
            System.out.println("2- Run Menu");
            String option = scanner.next();
            if (option.equals("1")) {
                System.out.print("index : ");
                int index = scanner.nextInt() - 1;
                if (user.getTimeline()[index] != null && index < 11 )
                    Post(user, user.getTimeline()[index]);
                else {
                    System.out.println("index out of range");
                }
            } else if (option.equals("2")) {
                RunReddit(user);
            } else {
                System.out.println("choose an option please");
            }
        }
    }

    public static User selectUser (User user , ArrayList<User> users){ // for,use it your list must have on member
        int i = 0;
        for (User user1 : users)
        {
            System.out.print(i+1 + "- ");
            System.out.println(user1.getUserName());
            i++;
            System.out.println("--------");
        }
        int index = 1000 ;
        Scanner scanner = new Scanner(System.in);
        while (index >= users.size()) {
            System.out.println("Index : ");
            index = scanner.nextInt() - 1;
            if (index >= users.size()) {
                System.out.println("Index out of range");
            } else {
                return users.get(index);
            }
        }
        return users.get(index) ;
    }

    public static Post selectPost(User user, ArrayList<Post> posts){
        int i = 0;
        for (Post post : posts)
        {
            System.out.print(i+1 + "- ");
            post.showInList();
            i++;
            System.out.println("--------");
        }
        int index = 1000 ;
        Scanner scanner = new Scanner(System.in);
        while (index >= posts.size()) {
            System.out.println("Index : ");
            index = scanner.nextInt() - 1;
            if (index >= posts.size()) {
                System.out.println("Index out of range");
            } else {
                return posts.get(index);
            }
        }
        return posts.get(index) ;
    }

    public static Subreddit selectSubreddit(User user, ArrayList<Subreddit> subreddits){
        int i = 0;
        for (Subreddit subreddit : subreddits) {
            System.out.print(i+1 + "- ");
            System.out.println(subreddit.getName());
            i++;
            System.out.println("--------");
        }
        int index = 1000 ;
        Scanner scanner = new Scanner(System.in);
        while (index >= subreddits.size()) {
            System.out.println("Index : ");
            index = scanner.nextInt() - 1;
            if (index >= subreddits.size()) {
                System.out.println("Index out of range");
            } else {
                return subreddits.get(index);
            }
        }
        return subreddits.get(index) ;
    }

    public static Comment selectComment(User user, ArrayList<Comment> comments){
        int i = 0;
        for (Comment comment : comments)
        {
            System.out.println(i+1 + "- ");
            comment.show();
            System.out.println("--------");
        }
        int index = 1000 ;
        Scanner scanner = new Scanner(System.in);
        while (index >= comments.size()) {
            System.out.println("Index : ");
            index = scanner.nextInt() - 1;
            if (index >= comments.size()) {
                System.out.println("Index out of range");
            } else {
                return comments.get(index);
            }
        }
        return null ;
    }

    public static void newPage () {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }


}
