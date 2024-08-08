package com.i_mario.agrodirect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;

public class TodoAdapter extends BaseAdapter {
    private Context context;
    private List<Todo> todoList;

    public TodoAdapter(Context context, List<Todo> todoList) {
        this.context = context;
        this.todoList = todoList;
    }

    @Override
    public int getCount() {
        return todoList.size();
    }

    @Override
    public Object getItem(int position) {
        return todoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false);
        }

        Todo todo = todoList.get(position);

        TextView textView = convertView.findViewById(R.id.todo_text);
        CheckBox checkBox = convertView.findViewById(R.id.todo_checkbox);
        Button editButton = convertView.findViewById(R.id.edit_button);
        Button deleteButton = convertView.findViewById(R.id.delete_button);

        textView.setText(todo.getText());
        checkBox.setChecked(todo.isCompleted());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todo.setCompleted(checkBox.isChecked());
                notifyDataSetChanged();
                // Guardar cambios
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementar edición de tarea
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoList.remove(position);
                notifyDataSetChanged();
                // Guardar cambios
            }
        });

        return convertView;
    }
}
