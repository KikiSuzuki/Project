package po;

public class Friend {
    private int friends_id;
    private int user1_id;
    private int user2_id;
    private int status;

    public int getLevel() { return status; }

    public void setLevel(int level) { this.status = status; }

    public int getFriends_id() {
        return friends_id;
    }

    public void setFriends_id(int friends_id) {
        this.friends_id = friends_id;
    }

    public int getUser1_id() {
        return user1_id;
    }

    public void setUser1_id(int user1_id) {
        this.user1_id = user1_id;
    }

    public int getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(int user2_id) {
        this.user2_id = user2_id;
    }
}
