package models;

public class User {

    private int id;
    private String username;
    private int profile;
    private String sessionId;

    public User() {
    }

    public User(int id, String username, int profile, String sessionId) {
        this.id = id;
        this.username = username;
        this.profile = profile;
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", profile=" + profile +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}