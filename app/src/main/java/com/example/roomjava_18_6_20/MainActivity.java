package com.example.roomjava_18_6_20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import adapter.TasksAdapter;
import database.DatabaseClient;
import entity.Task;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton = findViewById(R.id.floating_button_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddTaskActivity.class);
                startActivity(intent);
            }
        });
        getTasks();
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void,Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> listTask = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().getAllTask();
                return listTask;
            }

            @Override
            protected void onPostExecute(List<Task> taskList) {
                super.onPostExecute(taskList);
                TasksAdapter tasksAdapter = new TasksAdapter(MainActivity.this,taskList);
                recyclerView.setAdapter(tasksAdapter);
            }
        }

        GetTasks getTasks = new GetTasks();
        getTasks.execute();

    }


}