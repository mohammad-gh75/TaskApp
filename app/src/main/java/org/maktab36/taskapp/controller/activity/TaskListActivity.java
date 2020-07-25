package org.maktab36.taskapp.controller.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import org.maktab36.taskapp.controller.fragment.TaskListFragment;

public class TaskListActivity extends SingleFragmentActivity {

    private static final String EXTRA_TASK_NAME="org.maktab36.taskapp.taskName";
    private static final String EXTRA_TASK_NUMBER="org.maktab36.taskapp.taskNumber";

    public static Intent newIntent(Context context, String name, int number) {
        Intent intent = new Intent(context, TaskListActivity.class);
        intent.putExtra(EXTRA_TASK_NAME, name);
        intent.putExtra(EXTRA_TASK_NUMBER, number);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        String name=getIntent().getStringExtra(EXTRA_TASK_NAME);
        int number=getIntent().getIntExtra(EXTRA_TASK_NUMBER,1);
        return TaskListFragment.newInstance(name,number);
    }
}