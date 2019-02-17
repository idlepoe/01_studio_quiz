package lee.quizquiz;

import java.util.Objects;

public class User {
    private String Name;
    private Integer Score;

    public User(String name, Integer score) {

        Name = name;
        Score = score;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Score=" + Score +
                '}';
    }

    public User() {
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

     public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
