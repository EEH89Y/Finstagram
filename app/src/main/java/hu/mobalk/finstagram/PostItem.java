package hu.mobalk.finstagram;

import java.util.ArrayList;

public class PostItem {
    private String userName;
    private String description;

    private final int imageResource;

    public PostItem(String userName, String description, int imageResource) {
        this.userName = userName;
        this.description = description;
        this.imageResource = imageResource;
    }

    public String getUserName() {return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getImageResource() { return imageResource; }


}
