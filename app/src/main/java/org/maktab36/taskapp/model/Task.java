package org.maktab36.taskapp.model;

import java.util.UUID;

public class Task {
    private UUID mId;
    private String mName;
    private TaskState mState;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public TaskState getState() {
        return mState;
    }

    public void setState(TaskState state) {
        mState = state;
    }

    public Task() {
        mId=UUID.randomUUID();
    }

    public Task(String name, TaskState state) {
        this();
        mName = name;
        mState = state;
    }
}
