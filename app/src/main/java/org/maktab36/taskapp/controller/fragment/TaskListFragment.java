package org.maktab36.taskapp.controller.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.maktab36.taskapp.R;
import org.maktab36.taskapp.model.Task;
import org.maktab36.taskapp.model.TaskState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TaskListFragment extends Fragment {
    public static final String ARG_TASK_NAME = "taskName";
    public static final String ARG_TASK_NUMBER = "taskNumber";
    private RecyclerView mRecyclerView;
    private FloatingActionButton mActionButton;
    private TaskAdapter mAdapter;
    private String mTaskName;
    private int mTaskNumber;
    private List<Task> mTasks;

    public TaskListFragment() {
        // Required empty public constructor
    }

    public static TaskListFragment newInstance(String taskName, int taskNumber) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TASK_NAME, taskName);
        args.putInt(ARG_TASK_NUMBER, taskNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createTaskList();
    }

    private List<Task> createTaskList() {
        List<Task> tasks = new ArrayList<>();
        mTaskName = getArguments().getString(ARG_TASK_NAME);
        mTaskNumber = getArguments().getInt(ARG_TASK_NUMBER);
        for (int i = 1; i <= mTaskNumber; i++) {
            Random random = new Random();
            TaskState[] taskStates = TaskState.values();
            int r = random.nextInt(taskStates.length);
            Task task = new Task(mTaskName + "#" + i, taskStates[r]);
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        findViews(view);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mTasks = createTaskList();
        updateUI();
        setListener();
        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_tasks);
        mActionButton = view.findViewById(R.id.floating_action_button_add_task);
    }

    private void setListener() {
        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTaskNumber++;
                Random random = new Random();
                TaskState[] taskStates = TaskState.values();
                int r = random.nextInt(taskStates.length);
                Task task = new Task(mTaskName + "#" + mTaskNumber, taskStates[r]);
                mTasks.add(task);
                updateUI();
            }
        });
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new TaskAdapter(mTasks);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyItemInserted(mTaskNumber);
        }
    }

    private class TaskHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTaskName;
        private TextView mTextViewTaskState;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewTaskName = itemView.findViewById(R.id.text_view_task_name);
            mTextViewTaskState = itemView.findViewById(R.id.text_view_task_state);
        }

        public void bindTask(Task task) {
            mTextViewTaskName.setText(task.getName());
            mTextViewTaskState.setText(task.getState().toString());
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> mTaskList;

        public TaskAdapter(List<Task> taskList) {
            mTaskList = taskList;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view;

            if (viewType == 0) {
                view = inflater.inflate(R.layout.list_row_task_even, parent, false);
            } else {
                view = inflater.inflate(R.layout.list_row_task_odd, parent, false);
            }
            return new TaskHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = mTaskList.get(position);
            holder.bindTask(task);
        }

        @Override
        public int getItemCount() {
            return mTaskList.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position % 2 == 0) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}