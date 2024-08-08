package com.i_mario.agrodirect;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Todo> todoList;
    private TodoAdapter adapter;
    private SharedPreferences sharedPreferences;
    private EditText inputText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("todos", MODE_PRIVATE);
        todoList = loadTodos();

        adapter = new TodoAdapter(this, todoList);
        ListView listView = findViewById(R.id.todo_list);
        listView.setAdapter(adapter);

        inputText = findViewById(R.id.input_text);
        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString();
                if (!text.isEmpty()) {
                    addTodo(text);
                    inputText.setText("");
                }
            }
        });
    }

    private List<Todo> loadTodos() {
        String json = sharedPreferences.getString("todos", null);
        if (json != null) {
            Type type = new TypeToken<ArrayList<Todo>>() {}.getType();
            return new Gson().fromJson(json, type);
        }
        return new ArrayList<>();
    }

    private void saveTodos() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = new Gson().toJson(todoList);
        editor.putString("todos", json);
        editor.apply();
    }

    private void addTodo(String text) {
        Todo newTodo = new Todo();
        newTodo.setId(System.currentTimeMillis());
        newTodo.setText(text);
        newTodo.setCompleted(false);
        todoList.add(newTodo);
        adapter.notifyDataSetChanged();
        saveTodos();
    }

    // Implementar métodos para editar y eliminar tareas
}