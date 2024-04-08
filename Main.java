import java.util.Scanner;

public class Main{
    public static Reddit reddit = new Reddit();
    public static void main(String[] args) {
        while (true) {
            reddit.fakeRed();
            User user = LogInSignUp();
            if (user.equals(null)) {
                System.out.println("thanks for your time ");
                return;
            } else {
                System.out.println("your sign up successful");
                RunReddit(user);
            }
        }
    }
    public static User LogInSignUp () {
        User user = null;
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
                    return null ;
                }
            }
        }
    }

    public static void RunReddit (User user){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1- Profile");
            System.out.println("2- Creat Subreddit");
            System.out.println("3- Search");
            System.out.println("4- Join Subreddit");
            System.out.println("5- Change personal info");
            System.out.println("6- Creat Post");
            System.out.println("7- Search By UUID");
            System.out.println("8- View Subreddits");
            System.out.println("9- My Subreddits ");
            System.out.println("10- Exit");
            String option = scanner.next();
            switch (option) {
                case "1" -> user.showProfile();
                case "2" -> reddit.creatSubreddit(user);
                case "3" -> {
                    System.out.print("target : ");
                    String target = scanner.next();
                    reddit.searchbyName(target);
                }
                case "4" -> reddit.joinSubreddit(user);
                case "5" -> reddit.changePersonalInfo(user);
                case "6" -> reddit.creatPost(user);
                case "7" -> {
                    System.out.print("target : ");
                    String target = scanner.next();
                    reddit.SearchbyUUID(target);
                }
                case "8" -> reddit.viewSubreddits();
                case "9" -> {
                    user.showJoinedSubreddits();
                }
                case "11" -> {
                    reddit.addAdmin(user);
                }
                case "10" -> {
                    return;
                }
            }
            System.out.println("______________________________________");
        }
    }
    public void selectPost (User user , Post post )
    {
        post.Show();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- Add Comment");
        System.out.println("2- Up vote");
        System.out.println("3- Down vote");
        System.out.println("4- creator");
        System.out.println("5- subreddit");
        System.out.println("6- select comment ");
        System.out.println("7- previous page");
        System.out.println("8- Explore");
        System.out.println("9- Menu");
    }

    public void selectSubreddit (User user , Subreddit subreddit)
    {
        Scanner scanner = new Scanner(System.in);
        subreddit.Show();
        System.out.println("options :");
        System.out.println("1- Join");
        System.out.println("2- Creat Post");
        System.out.println("3- Select Post ");
        System.out.println("4- previous page");
        System.out.println("5- Explore");
        System.out.println("6- Menu");
    }

    public void selectComment (User user , Comment comment)
    {
        Scanner scanner = new Scanner(System.in);
        comment.show();
        System.out.println("options : ");
        System.out.println("1- Creator");
        System.out.println("2- Post");
        System.out.println("3- Subreddit");
        System.out.println("4- Reply");
        System.out.println("5- previous page");
        System.out.println("6- Explore");
        System.out.println("7- Menu");
    }
}
