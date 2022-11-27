package main.java.com.akagiyui.foundation.experiment.sixth;

// 有五个学生，每个学生有3门课的成绩，包括学生号，姓名，三门课成绩

public class Student {
    private final String id;
    private final String name;
    private final int[] scores;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int[] getScores() {
        return scores;
    }

    public Student(String id, String name, int[] scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }
}
