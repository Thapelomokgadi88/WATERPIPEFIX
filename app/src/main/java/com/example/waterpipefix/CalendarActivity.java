package com.example.waterpipefix;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private RecyclerView taskRecyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;
    private Button addTaskButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        taskRecyclerView = findViewById(R.id.taskRecyclerView);
        addTaskButton = findViewById(R.id.addTaskButton);
        backButton = findViewById(R.id.backButton);

        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        taskList = new ArrayList<>();
        // Add sample tasks to the list
        taskList.add(new Task("Task 1", "Task 1 description"));
        taskList.add(new Task("Task 2", "Task 2 description"));
        taskList.add(new Task("Task 3", "Task 3 description"));

        taskAdapter = new TaskAdapter(taskList);
        taskRecyclerView.setAdapter(taskAdapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Handle date selection event
                // You can filter tasks based on the selected date if needed
            }
        });

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTaskDialog();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_task, null);
        dialogBuilder.setView(dialogView);

        final EditText taskTitleEditText = dialogView.findViewById(R.id.taskTitleEditText);
        final EditText taskDescriptionEditText = dialogView.findViewById(R.id.taskDescriptionEditText);
        Button addButton = dialogView.findViewById(R.id.addButton);

        final AlertDialog dialog = dialogBuilder.create();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskTitle = taskTitleEditText.getText().toString().trim();
                String taskDescription = taskDescriptionEditText.getText().toString().trim();

                if (!taskTitle.isEmpty()) {
                    Task newTask = new Task(taskTitle, taskDescription);
                    taskList.add(newTask);
                    taskAdapter.notifyDataSetChanged();
                    // Scroll to the newly added task
                    taskRecyclerView.scrollToPosition(taskList.size() - 1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(CalendarActivity.this, "Please enter a task title", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

        private List<Task> tasks;

        public TaskAdapter(List<Task> tasks) {
            this.tasks = tasks;
        }

        @NonNull
        @Override
        public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
            return new TaskViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
            Task task = tasks.get(position);
            holder.taskTitle.setText(task.getTitle());
            holder.taskDescription.setText(task.getDescription());
        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }

        private class TaskViewHolder extends RecyclerView.ViewHolder {
            TextView taskTitle;
            TextView taskDescription;

            public TaskViewHolder(@NonNull View itemView) {
                super(itemView);
                taskTitle = itemView.findViewById(R.id.taskTitle);
                taskDescription = itemView.findViewById(R.id.taskDescription);
            }
        }
    }

    private class Task {
        private String title;
        private String description;

        public Task(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }
}
