package database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    //atributo para acessar a database
    private static AppDataBase dataBase;

    //atributo para tarefa dao
    public abstract TarefaDao getTarefaDao();

    //metodo para acessar o atributo data base
    public static AppDataBase getDataBase(Context context){
        //instancia a database
        dataBase = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class, "todoList" ).build();
        return dataBase;
    }

}
