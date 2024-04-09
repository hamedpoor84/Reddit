// This class is for test what U learn .
import java.time.LocalDateTime;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current date and time: " + currentDateTime);
        System.out.println(currentDateTime.getClass());
    }
}
