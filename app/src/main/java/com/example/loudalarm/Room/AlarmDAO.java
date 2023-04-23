package com.example.loudalarm.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlarmDAO {
    @Query("SELECT * FROM AlarmEntity")
    List<AlarmEntity> getAll();

    @Query("DELETE FROM alarmentity")
    void clear();


    @Insert
    void save(AlarmEntity alarm);

    @Insert
    void saveAll(List<AlarmEntity> list);

    @Query("SELECT * FROM AlarmEntity WHERE id = :id")
    AlarmEntity get(int id);


    @Query("SELECT id FROM AlarmEntity WHERE id = :id")
    boolean getID(int id);

    @Query("SELECT music FROM AlarmEntity WHERE id = :id")
    String getMusicUriInString(int id);

    @Query("SELECT EXISTS (SELECT * FROM AlarmEntity WHERE time = :time) ")
    boolean isTimeInMillisInDB(long time);

    @Query("SELECT * FROM AlarmEntity WHERE time = :time")
    AlarmEntity getAlarmKnowTime(long time);

    @Query("DELETE FROM AlarmEntity WHERE id =:id")
    void delete(int id);

    @Delete
    void deleteAll(List<AlarmEntity> alarmEntities);

    @Update
    void updateAll(List<AlarmEntity> data);


    @Update
    void update(AlarmEntity alarm);
}
