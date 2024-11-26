package org.kong.edge.framework.models;

import java.util.ArrayList;

public class Todos
{

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
    }

    private ArrayList<Todo> todos;

}
