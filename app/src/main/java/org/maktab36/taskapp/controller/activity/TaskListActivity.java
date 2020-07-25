package org.maktab36.taskapp.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.maktab36.taskapp.R;
import org.maktab36.taskapp.controller.fragment.TaskListFragment;

public class TaskListActivity extends SingleFragmentActivity {

    private static final String EXTRA_TASK_NAME="org.maktab36.taskapp.taskName";
    private static final String EXTRA_TASK_NUMBER="org.maktab36.taskapp.taskNumber";

    public static Intent newIntent(Context context,String name,int number){
        Intent intent=new Intent(context,TaskListActivity.class);
        intent.putExtra(EXTRA_TASK_NAME,name);
        intent.putExtra(EXTRA_TASK_NUMBER,number);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        return TaskListFragment.newInstance();
    }
}