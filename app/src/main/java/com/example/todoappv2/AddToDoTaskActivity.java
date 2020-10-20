package com.example.todoappv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class AddToDoTaskActivity extends AppCompatActivity {
ImageView imageView;

    DatePicker picker;
    EditText taskText;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);
    }

    public void save_task(View view) {
        picker=(DatePicker)findViewById(R.id.datapicker);
        taskText = (EditText) findViewById(R.id.task_id);

        String date = "Selected Date: "+ picker.getDayOfMonth()+"/"+ (picker.getMonth() + 1)+"/"+picker.getYear();
        Log.d("date",date);

        Date taskDate = new Date();
        taskDate.setMonth(picker.getMonth());
        taskDate.setYear(picker.getYear());
        taskDate.setDate(picker.getDayOfMonth());

        ToDoTask newTask = new ToDoTask(taskText.getText().toString(),taskDate.toString());

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",newTask);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

}
