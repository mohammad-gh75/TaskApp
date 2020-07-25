package org.maktab36.taskapp.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.maktab36.taskapp.R;
import org.maktab36.taskapp.controller.activity.TaskListActivity;


public class TaskBuilderFragment extends Fragment {
    private Button mButtonBuild;
    private TextView mTextViewName;
    private TextView mTextViewNumber;

    public TaskBuilderFragment() {
        // Required empty public constructor
    }

    public static TaskBuilderFragment newInstance() {
        TaskBuilderFragment fragment = new TaskBuilderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_builder, container, false);
        findViews(view);
        setListeners();
        return view;
    }

    private void findViews(View view) {
        mButtonBuild = view.findViewById(R.id.button_build);
        mTextViewName = view.findViewById(R.id.edit_text_username);
        mTextViewNumber = view.findViewById(R.id.edit_text_number_signed);
    }

    private void setListeners() {
        mButtonBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mTextViewName.getText().toString();
                int number = Integer.parseInt(mTextViewNumber.getText().toString());
                Intent intent = TaskListActivity.newIntent(getActivity(), name, number);
                startActivity(intent);
            }
        });
    }
}