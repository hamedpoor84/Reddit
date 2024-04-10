import java.util.ArrayList;
import java.util.UUID;

public class Comment {
    public long startTime = System.currentTimeMillis();
    private long showTime ;
    private Post post;
    private int likes;
    private int dislikes;
    private User creator;
    private UUID uuid ;
    private String text;
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<User> voters = new ArrayList<>();
    private ArrayList<Integer> votes = new ArrayList<>();

    public Comment(Post post , User creator, String text , UUID uuid) {
        this.post = post;
        this.creator = creator;
        this.text = text;
        this.uuid = uuid ;
        likes = 0;
        dislikes = 0;
        post.setCommentCounter(post.getCommentCounter()+1);
    }

    public void show ()
    {
        showTime = System.currentTimeMillis() - startTime ;
        System.out.print("*");
        System.out.println("By : " + creator.getUserName() + "Made in : " + showTime);
        System.out.printf("%-40s", text); // This will output "42//     "
        System.out.println();
        System.out.printf("%-5s", likes);
        System.out.print(" likes   ");
        System.out.printf("%-5s", dislikes);
        System.out.print(" dislikes   ");
        System.out.printf("%-5s" , comments.size());
        System.out.println(" Reply");
    }

    public void showComments ()
    {
        int i = 1 ;
        for (Comment comment : comments)
        {
            System.out.print(i+1 + "- ");
            comment.show();
        }
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
    }
    public void AddComment (Comment comment)
    {
        comments.add(comment) ;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
