package by.den.javaCodeVer;

public class SimpleUserService implements UserService{
    UserPreferences userPreferences;

    public UserPreferences getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }
}
