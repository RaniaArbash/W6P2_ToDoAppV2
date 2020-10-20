package com.example.todoappv2;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyDialog.saveTaskEventListener {
    CustomAdapter customAdapter;
    ListView simpleList;
    ArrayList<ToDoTask> toDoTasks = new ArrayList<ToDoTask>(5);
    int LAUNCH_SECOND_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            toDoTasks = savedInstanceState.getParcelableArrayList("todolist");
        }
        else {
            toDoTasks = new ArrayList<ToDoTask>(5);
        }

        simpleList = (ListView) findViewById(R.id.simpleListView);
        customAdapter = new CustomAdapter(getApplicationContext(), toDoTasks);
        simpleList.setAdapter(customAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addcountry,menu);
        return true;

    }

    public void createDialgo(int textId) {
        MyDialog newDialog = new MyDialog();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        newDialog.show(transaction,"fragment");

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.add_country_id: {
                this.createDialgo(R.string.meg);


//                FragmentList newList = new FragmentList();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container, newList).commit();

            }
        }
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                ToDoTask result=data.getParcelableExtra("result");
                Log.d("task","value");
                toDoTasks.add(result);
                customAdapter = new CustomAdapter(getApplicationContext(), toDoTasks);
                simpleList.setAdapter(customAdapter);
                //customAdapter.notifyDataSetChanged();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("todolist",toDoTasks);

    }

    @Override
    public void saveTask(ToDoTask newTask) {
        toDoTasks.add(newTask);
        customAdapter = new CustomAdapter(getApplicationContext(), toDoTasks);
        simpleList.setAdapter(customAdapter);
    }
}

