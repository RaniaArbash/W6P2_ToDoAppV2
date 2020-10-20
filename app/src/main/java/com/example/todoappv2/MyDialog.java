package com.example.todoappv2;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Date;

public class MyDialog extends DialogFragment implements View.OnClickListener{

    private saveTaskEventListener saveTaskEventListener;

    public interface saveTaskEventListener {
        public void saveTask(ToDoTask newTask);
    }

    EditText text;
    DatePicker picker;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;

        if (context instanceof Activity){
            a = (Activity) context;
            saveTaskEventListener = (saveTaskEventListener) a;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment,container,false);
        text = (EditText) view.findViewById(R.id.task_id);
        picker = (DatePicker) view.findViewById(R.id.datapicker);

        Button button = (Button)view.findViewById(R.id.save_id);
        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Date taskDate = new Date();
        taskDate.setMonth(picker.getMonth());
        taskDate.setYear(picker.getYear());
        taskDate.setDate(picker.getDayOfMonth());
        ToDoTask newTask = new ToDoTask(text.getText().toString(),taskDate.toString());
        Log.d("date",newTask.time);

        saveTaskEventListener.saveTask(newTask);
        dismiss();
    }
}
