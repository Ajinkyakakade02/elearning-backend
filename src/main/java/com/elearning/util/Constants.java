package com.elearning.util;

public class Constants {

    public static final String API_BASE = "/api";
    public static final String API_AUTH = API_BASE + "/auth";
    public static final String API_USERS = API_BASE + "/users";
    public static final String API_COURSES = API_BASE + "/courses";
    public static final String API_LESSONS = API_BASE + "/lessons";
    public static final String API_ENROLLMENTS = API_BASE + "/enrollments";
    public static final String API_QUIZZES = API_BASE + "/quizzes";
    public static final String API_PROGRESS = API_BASE + "/progress";

    public static final String ROLE_STUDENT = "student";
    public static final String ROLE_INSTRUCTOR = "instructor";
    public static final String ROLE_ADMIN = "admin";

    public static final String LEVEL_BEGINNER = "Beginner";
    public static final String LEVEL_INTERMEDIATE = "Intermediate";
    public static final String LEVEL_ADVANCED = "Advanced";

    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 50;

    public static final String ERROR_USER_NOT_FOUND = "User not found";
    public static final String ERROR_COURSE_NOT_FOUND = "Course not found";
    public static final String ERROR_LESSON_NOT_FOUND = "Lesson not found";
    public static final String ERROR_EMAIL_EXISTS = "Email already exists";
    public static final String ERROR_INVALID_CREDENTIALS = "Invalid email or password";
    public static final String ERROR_UNAUTHORIZED = "Unauthorized access";
}