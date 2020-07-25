package org.maktab36.taskapp.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import org.maktab36.taskapp.R;
import org.maktab36.taskapp.controller.fragment.TaskBuilderFragment;

public class TaskBuilderActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return TaskBuilderFragment.newInstance();
    }
}