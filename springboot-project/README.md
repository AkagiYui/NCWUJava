# Springboot 结课作业

经典的 **教师、学生、课程管理系统** 。

```mermaid
erDiagram
    "教师" {
        int id PK
        string name "姓名"
        string teacherNumber UK "教师编号"
    }
    "学生" {
        int id PK
        string name "姓名"
        string studentNumber UK "学号"
    }
    "课程" {
        int id PK
        string name UK "课程名"
    }

    "教授" {
        int id PK
        int teacherId "教师id"
        int courseId "课程id"
    }
    "修读" {
        int id PK
        int studentId "学生id"
        int courseId "课程id"
    }

    "学生" ||--|{ "修读" : ""
    "教师" ||--|{ "教授" : ""
    "课程" ||--|{ "教授" : ""
    "课程" ||--|{ "修读" : ""
```
