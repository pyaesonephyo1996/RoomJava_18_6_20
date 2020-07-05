package database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dao.TaskDao;
import entity.Task;

@Database(entities = {Task.class} , version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();
}
