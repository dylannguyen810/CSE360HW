package application;

/**
 * Represents a staff user who can view, flag, and delete content.
 */
public class Staff {

    private String username;
    private String role;

    public Staff(String username) {
        this.username = username;
        this.role = "staff";
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }
}