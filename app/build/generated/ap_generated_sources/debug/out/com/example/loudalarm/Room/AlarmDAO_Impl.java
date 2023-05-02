package com.example.loudalarm.Room;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AlarmDAO_Impl implements AlarmDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AlarmEntity> __insertionAdapterOfAlarmEntity;

  private final EntityDeletionOrUpdateAdapter<AlarmEntity> __deletionAdapterOfAlarmEntity;

  private final EntityDeletionOrUpdateAdapter<AlarmEntity> __updateAdapterOfAlarmEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public AlarmDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAlarmEntity = new EntityInsertionAdapter<AlarmEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `AlarmEntity` (`id`,`isAlarmAdjustVolume`,`vol`,`hours`,`minutes`,`time_on_clock_in_hours_and_minutes`,`days`,`time`,`vib`,`on`,`today`,`monday`,`tuesday`,`wednesday`,`thursday`,`friday`,`saturday`,`sunday`,`music`,`textMessage`,`alarmCanPlay`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AlarmEntity value) {
        stmt.bindLong(1, value.id);
        final int _tmp = value.isAlarmAdjustVolume ? 1 : 0;
        stmt.bindLong(2, _tmp);
        stmt.bindLong(3, value.vol);
        stmt.bindLong(4, value.hours);
        stmt.bindLong(5, value.minutes);
        if (value.time_on_clock_in_hours_and_minutes == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.time_on_clock_in_hours_and_minutes);
        }
        if (value.days == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.days);
        }
        stmt.bindLong(8, value.time);
        final int _tmp_1 = value.vib ? 1 : 0;
        stmt.bindLong(9, _tmp_1);
        final int _tmp_2 = value.on ? 1 : 0;
        stmt.bindLong(10, _tmp_2);
        final int _tmp_3 = value.today ? 1 : 0;
        stmt.bindLong(11, _tmp_3);
        final int _tmp_4 = value.monday ? 1 : 0;
        stmt.bindLong(12, _tmp_4);
        final int _tmp_5 = value.tuesday ? 1 : 0;
        stmt.bindLong(13, _tmp_5);
        final int _tmp_6 = value.wednesday ? 1 : 0;
        stmt.bindLong(14, _tmp_6);
        final int _tmp_7 = value.thursday ? 1 : 0;
        stmt.bindLong(15, _tmp_7);
        final int _tmp_8 = value.friday ? 1 : 0;
        stmt.bindLong(16, _tmp_8);
        final int _tmp_9 = value.saturday ? 1 : 0;
        stmt.bindLong(17, _tmp_9);
        final int _tmp_10 = value.sunday ? 1 : 0;
        stmt.bindLong(18, _tmp_10);
        if (value.music == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.music);
        }
        if (value.textMessage == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.textMessage);
        }
        final int _tmp_11 = value.alarmCanPlay ? 1 : 0;
        stmt.bindLong(21, _tmp_11);
      }
    };
    this.__deletionAdapterOfAlarmEntity = new EntityDeletionOrUpdateAdapter<AlarmEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `AlarmEntity` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AlarmEntity value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfAlarmEntity = new EntityDeletionOrUpdateAdapter<AlarmEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `AlarmEntity` SET `id` = ?,`isAlarmAdjustVolume` = ?,`vol` = ?,`hours` = ?,`minutes` = ?,`time_on_clock_in_hours_and_minutes` = ?,`days` = ?,`time` = ?,`vib` = ?,`on` = ?,`today` = ?,`monday` = ?,`tuesday` = ?,`wednesday` = ?,`thursday` = ?,`friday` = ?,`saturday` = ?,`sunday` = ?,`music` = ?,`textMessage` = ?,`alarmCanPlay` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AlarmEntity value) {
        stmt.bindLong(1, value.id);
        final int _tmp = value.isAlarmAdjustVolume ? 1 : 0;
        stmt.bindLong(2, _tmp);
        stmt.bindLong(3, value.vol);
        stmt.bindLong(4, value.hours);
        stmt.bindLong(5, value.minutes);
        if (value.time_on_clock_in_hours_and_minutes == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.time_on_clock_in_hours_and_minutes);
        }
        if (value.days == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.days);
        }
        stmt.bindLong(8, value.time);
        final int _tmp_1 = value.vib ? 1 : 0;
        stmt.bindLong(9, _tmp_1);
        final int _tmp_2 = value.on ? 1 : 0;
        stmt.bindLong(10, _tmp_2);
        final int _tmp_3 = value.today ? 1 : 0;
        stmt.bindLong(11, _tmp_3);
        final int _tmp_4 = value.monday ? 1 : 0;
        stmt.bindLong(12, _tmp_4);
        final int _tmp_5 = value.tuesday ? 1 : 0;
        stmt.bindLong(13, _tmp_5);
        final int _tmp_6 = value.wednesday ? 1 : 0;
        stmt.bindLong(14, _tmp_6);
        final int _tmp_7 = value.thursday ? 1 : 0;
        stmt.bindLong(15, _tmp_7);
        final int _tmp_8 = value.friday ? 1 : 0;
        stmt.bindLong(16, _tmp_8);
        final int _tmp_9 = value.saturday ? 1 : 0;
        stmt.bindLong(17, _tmp_9);
        final int _tmp_10 = value.sunday ? 1 : 0;
        stmt.bindLong(18, _tmp_10);
        if (value.music == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.music);
        }
        if (value.textMessage == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.textMessage);
        }
        final int _tmp_11 = value.alarmCanPlay ? 1 : 0;
        stmt.bindLong(21, _tmp_11);
        stmt.bindLong(22, value.id);
      }
    };
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM alarmentity";
        return _query;
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM AlarmEntity WHERE id =?";
        return _query;
      }
    };
  }

  @Override
  public void save(final AlarmEntity alarm) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAlarmEntity.insert(alarm);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void saveAll(final List<AlarmEntity> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAlarmEntity.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll(final List<AlarmEntity> alarmEntities) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfAlarmEntity.handleMultiple(alarmEntities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateAll(final List<AlarmEntity> data) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAlarmEntity.handleMultiple(data);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final AlarmEntity alarm) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAlarmEntity.handle(alarm);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clear() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClear.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClear.release(_stmt);
    }
  }

  @Override
  public void delete(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public List<AlarmEntity> getAll() {
    final String _sql = "SELECT * FROM AlarmEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfIsAlarmAdjustVolume = CursorUtil.getColumnIndexOrThrow(_cursor, "isAlarmAdjustVolume");
      final int _cursorIndexOfVol = CursorUtil.getColumnIndexOrThrow(_cursor, "vol");
      final int _cursorIndexOfHours = CursorUtil.getColumnIndexOrThrow(_cursor, "hours");
      final int _cursorIndexOfMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "minutes");
      final int _cursorIndexOfTimeOnClockInHoursAndMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "time_on_clock_in_hours_and_minutes");
      final int _cursorIndexOfDays = CursorUtil.getColumnIndexOrThrow(_cursor, "days");
      final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final int _cursorIndexOfVib = CursorUtil.getColumnIndexOrThrow(_cursor, "vib");
      final int _cursorIndexOfOn = CursorUtil.getColumnIndexOrThrow(_cursor, "on");
      final int _cursorIndexOfToday = CursorUtil.getColumnIndexOrThrow(_cursor, "today");
      final int _cursorIndexOfMonday = CursorUtil.getColumnIndexOrThrow(_cursor, "monday");
      final int _cursorIndexOfTuesday = CursorUtil.getColumnIndexOrThrow(_cursor, "tuesday");
      final int _cursorIndexOfWednesday = CursorUtil.getColumnIndexOrThrow(_cursor, "wednesday");
      final int _cursorIndexOfThursday = CursorUtil.getColumnIndexOrThrow(_cursor, "thursday");
      final int _cursorIndexOfFriday = CursorUtil.getColumnIndexOrThrow(_cursor, "friday");
      final int _cursorIndexOfSaturday = CursorUtil.getColumnIndexOrThrow(_cursor, "saturday");
      final int _cursorIndexOfSunday = CursorUtil.getColumnIndexOrThrow(_cursor, "sunday");
      final int _cursorIndexOfMusic = CursorUtil.getColumnIndexOrThrow(_cursor, "music");
      final int _cursorIndexOfTextMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "textMessage");
      final int _cursorIndexOfAlarmCanPlay = CursorUtil.getColumnIndexOrThrow(_cursor, "alarmCanPlay");
      final List<AlarmEntity> _result = new ArrayList<AlarmEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final AlarmEntity _item;
        final String _tmpMusic;
        if (_cursor.isNull(_cursorIndexOfMusic)) {
          _tmpMusic = null;
        } else {
          _tmpMusic = _cursor.getString(_cursorIndexOfMusic);
        }
        _item = new AlarmEntity(_tmpMusic);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsAlarmAdjustVolume);
        _item.isAlarmAdjustVolume = _tmp != 0;
        _item.vol = _cursor.getInt(_cursorIndexOfVol);
        _item.hours = _cursor.getInt(_cursorIndexOfHours);
        _item.minutes = _cursor.getInt(_cursorIndexOfMinutes);
        if (_cursor.isNull(_cursorIndexOfTimeOnClockInHoursAndMinutes)) {
          _item.time_on_clock_in_hours_and_minutes = null;
        } else {
          _item.time_on_clock_in_hours_and_minutes = _cursor.getString(_cursorIndexOfTimeOnClockInHoursAndMinutes);
        }
        if (_cursor.isNull(_cursorIndexOfDays)) {
          _item.days = null;
        } else {
          _item.days = _cursor.getString(_cursorIndexOfDays);
        }
        _item.time = _cursor.getLong(_cursorIndexOfTime);
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfVib);
        _item.vib = _tmp_1 != 0;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfOn);
        _item.on = _tmp_2 != 0;
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfToday);
        _item.today = _tmp_3 != 0;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfMonday);
        _item.monday = _tmp_4 != 0;
        final int _tmp_5;
        _tmp_5 = _cursor.getInt(_cursorIndexOfTuesday);
        _item.tuesday = _tmp_5 != 0;
        final int _tmp_6;
        _tmp_6 = _cursor.getInt(_cursorIndexOfWednesday);
        _item.wednesday = _tmp_6 != 0;
        final int _tmp_7;
        _tmp_7 = _cursor.getInt(_cursorIndexOfThursday);
        _item.thursday = _tmp_7 != 0;
        final int _tmp_8;
        _tmp_8 = _cursor.getInt(_cursorIndexOfFriday);
        _item.friday = _tmp_8 != 0;
        final int _tmp_9;
        _tmp_9 = _cursor.getInt(_cursorIndexOfSaturday);
        _item.saturday = _tmp_9 != 0;
        final int _tmp_10;
        _tmp_10 = _cursor.getInt(_cursorIndexOfSunday);
        _item.sunday = _tmp_10 != 0;
        if (_cursor.isNull(_cursorIndexOfTextMessage)) {
          _item.textMessage = null;
        } else {
          _item.textMessage = _cursor.getString(_cursorIndexOfTextMessage);
        }
        final int _tmp_11;
        _tmp_11 = _cursor.getInt(_cursorIndexOfAlarmCanPlay);
        _item.alarmCanPlay = _tmp_11 != 0;
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public AlarmEntity get(final int id) {
    final String _sql = "SELECT * FROM AlarmEntity WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfIsAlarmAdjustVolume = CursorUtil.getColumnIndexOrThrow(_cursor, "isAlarmAdjustVolume");
      final int _cursorIndexOfVol = CursorUtil.getColumnIndexOrThrow(_cursor, "vol");
      final int _cursorIndexOfHours = CursorUtil.getColumnIndexOrThrow(_cursor, "hours");
      final int _cursorIndexOfMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "minutes");
      final int _cursorIndexOfTimeOnClockInHoursAndMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "time_on_clock_in_hours_and_minutes");
      final int _cursorIndexOfDays = CursorUtil.getColumnIndexOrThrow(_cursor, "days");
      final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final int _cursorIndexOfVib = CursorUtil.getColumnIndexOrThrow(_cursor, "vib");
      final int _cursorIndexOfOn = CursorUtil.getColumnIndexOrThrow(_cursor, "on");
      final int _cursorIndexOfToday = CursorUtil.getColumnIndexOrThrow(_cursor, "today");
      final int _cursorIndexOfMonday = CursorUtil.getColumnIndexOrThrow(_cursor, "monday");
      final int _cursorIndexOfTuesday = CursorUtil.getColumnIndexOrThrow(_cursor, "tuesday");
      final int _cursorIndexOfWednesday = CursorUtil.getColumnIndexOrThrow(_cursor, "wednesday");
      final int _cursorIndexOfThursday = CursorUtil.getColumnIndexOrThrow(_cursor, "thursday");
      final int _cursorIndexOfFriday = CursorUtil.getColumnIndexOrThrow(_cursor, "friday");
      final int _cursorIndexOfSaturday = CursorUtil.getColumnIndexOrThrow(_cursor, "saturday");
      final int _cursorIndexOfSunday = CursorUtil.getColumnIndexOrThrow(_cursor, "sunday");
      final int _cursorIndexOfMusic = CursorUtil.getColumnIndexOrThrow(_cursor, "music");
      final int _cursorIndexOfTextMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "textMessage");
      final int _cursorIndexOfAlarmCanPlay = CursorUtil.getColumnIndexOrThrow(_cursor, "alarmCanPlay");
      final AlarmEntity _result;
      if(_cursor.moveToFirst()) {
        final String _tmpMusic;
        if (_cursor.isNull(_cursorIndexOfMusic)) {
          _tmpMusic = null;
        } else {
          _tmpMusic = _cursor.getString(_cursorIndexOfMusic);
        }
        _result = new AlarmEntity(_tmpMusic);
        _result.id = _cursor.getInt(_cursorIndexOfId);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsAlarmAdjustVolume);
        _result.isAlarmAdjustVolume = _tmp != 0;
        _result.vol = _cursor.getInt(_cursorIndexOfVol);
        _result.hours = _cursor.getInt(_cursorIndexOfHours);
        _result.minutes = _cursor.getInt(_cursorIndexOfMinutes);
        if (_cursor.isNull(_cursorIndexOfTimeOnClockInHoursAndMinutes)) {
          _result.time_on_clock_in_hours_and_minutes = null;
        } else {
          _result.time_on_clock_in_hours_and_minutes = _cursor.getString(_cursorIndexOfTimeOnClockInHoursAndMinutes);
        }
        if (_cursor.isNull(_cursorIndexOfDays)) {
          _result.days = null;
        } else {
          _result.days = _cursor.getString(_cursorIndexOfDays);
        }
        _result.time = _cursor.getLong(_cursorIndexOfTime);
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfVib);
        _result.vib = _tmp_1 != 0;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfOn);
        _result.on = _tmp_2 != 0;
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfToday);
        _result.today = _tmp_3 != 0;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfMonday);
        _result.monday = _tmp_4 != 0;
        final int _tmp_5;
        _tmp_5 = _cursor.getInt(_cursorIndexOfTuesday);
        _result.tuesday = _tmp_5 != 0;
        final int _tmp_6;
        _tmp_6 = _cursor.getInt(_cursorIndexOfWednesday);
        _result.wednesday = _tmp_6 != 0;
        final int _tmp_7;
        _tmp_7 = _cursor.getInt(_cursorIndexOfThursday);
        _result.thursday = _tmp_7 != 0;
        final int _tmp_8;
        _tmp_8 = _cursor.getInt(_cursorIndexOfFriday);
        _result.friday = _tmp_8 != 0;
        final int _tmp_9;
        _tmp_9 = _cursor.getInt(_cursorIndexOfSaturday);
        _result.saturday = _tmp_9 != 0;
        final int _tmp_10;
        _tmp_10 = _cursor.getInt(_cursorIndexOfSunday);
        _result.sunday = _tmp_10 != 0;
        if (_cursor.isNull(_cursorIndexOfTextMessage)) {
          _result.textMessage = null;
        } else {
          _result.textMessage = _cursor.getString(_cursorIndexOfTextMessage);
        }
        final int _tmp_11;
        _tmp_11 = _cursor.getInt(_cursorIndexOfAlarmCanPlay);
        _result.alarmCanPlay = _tmp_11 != 0;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public boolean getID(final int id) {
    final String _sql = "SELECT id FROM AlarmEntity WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final boolean _result;
      if(_cursor.moveToFirst()) {
        final int _tmp;
        _tmp = _cursor.getInt(0);
        _result = _tmp != 0;
      } else {
        _result = false;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getMusicUriInString(final int id) {
    final String _sql = "SELECT music FROM AlarmEntity WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _result = null;
        } else {
          _result = _cursor.getString(0);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public boolean isTimeInMillisInDB(final long time) {
    final String _sql = "SELECT EXISTS (SELECT * FROM AlarmEntity WHERE time = ?) ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, time);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final boolean _result;
      if(_cursor.moveToFirst()) {
        final int _tmp;
        _tmp = _cursor.getInt(0);
        _result = _tmp != 0;
      } else {
        _result = false;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public AlarmEntity getAlarmKnowTime(final long time) {
    final String _sql = "SELECT * FROM AlarmEntity WHERE time = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, time);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfIsAlarmAdjustVolume = CursorUtil.getColumnIndexOrThrow(_cursor, "isAlarmAdjustVolume");
      final int _cursorIndexOfVol = CursorUtil.getColumnIndexOrThrow(_cursor, "vol");
      final int _cursorIndexOfHours = CursorUtil.getColumnIndexOrThrow(_cursor, "hours");
      final int _cursorIndexOfMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "minutes");
      final int _cursorIndexOfTimeOnClockInHoursAndMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "time_on_clock_in_hours_and_minutes");
      final int _cursorIndexOfDays = CursorUtil.getColumnIndexOrThrow(_cursor, "days");
      final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final int _cursorIndexOfVib = CursorUtil.getColumnIndexOrThrow(_cursor, "vib");
      final int _cursorIndexOfOn = CursorUtil.getColumnIndexOrThrow(_cursor, "on");
      final int _cursorIndexOfToday = CursorUtil.getColumnIndexOrThrow(_cursor, "today");
      final int _cursorIndexOfMonday = CursorUtil.getColumnIndexOrThrow(_cursor, "monday");
      final int _cursorIndexOfTuesday = CursorUtil.getColumnIndexOrThrow(_cursor, "tuesday");
      final int _cursorIndexOfWednesday = CursorUtil.getColumnIndexOrThrow(_cursor, "wednesday");
      final int _cursorIndexOfThursday = CursorUtil.getColumnIndexOrThrow(_cursor, "thursday");
      final int _cursorIndexOfFriday = CursorUtil.getColumnIndexOrThrow(_cursor, "friday");
      final int _cursorIndexOfSaturday = CursorUtil.getColumnIndexOrThrow(_cursor, "saturday");
      final int _cursorIndexOfSunday = CursorUtil.getColumnIndexOrThrow(_cursor, "sunday");
      final int _cursorIndexOfMusic = CursorUtil.getColumnIndexOrThrow(_cursor, "music");
      final int _cursorIndexOfTextMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "textMessage");
      final int _cursorIndexOfAlarmCanPlay = CursorUtil.getColumnIndexOrThrow(_cursor, "alarmCanPlay");
      final AlarmEntity _result;
      if(_cursor.moveToFirst()) {
        final String _tmpMusic;
        if (_cursor.isNull(_cursorIndexOfMusic)) {
          _tmpMusic = null;
        } else {
          _tmpMusic = _cursor.getString(_cursorIndexOfMusic);
        }
        _result = new AlarmEntity(_tmpMusic);
        _result.id = _cursor.getInt(_cursorIndexOfId);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsAlarmAdjustVolume);
        _result.isAlarmAdjustVolume = _tmp != 0;
        _result.vol = _cursor.getInt(_cursorIndexOfVol);
        _result.hours = _cursor.getInt(_cursorIndexOfHours);
        _result.minutes = _cursor.getInt(_cursorIndexOfMinutes);
        if (_cursor.isNull(_cursorIndexOfTimeOnClockInHoursAndMinutes)) {
          _result.time_on_clock_in_hours_and_minutes = null;
        } else {
          _result.time_on_clock_in_hours_and_minutes = _cursor.getString(_cursorIndexOfTimeOnClockInHoursAndMinutes);
        }
        if (_cursor.isNull(_cursorIndexOfDays)) {
          _result.days = null;
        } else {
          _result.days = _cursor.getString(_cursorIndexOfDays);
        }
        _result.time = _cursor.getLong(_cursorIndexOfTime);
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfVib);
        _result.vib = _tmp_1 != 0;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfOn);
        _result.on = _tmp_2 != 0;
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfToday);
        _result.today = _tmp_3 != 0;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfMonday);
        _result.monday = _tmp_4 != 0;
        final int _tmp_5;
        _tmp_5 = _cursor.getInt(_cursorIndexOfTuesday);
        _result.tuesday = _tmp_5 != 0;
        final int _tmp_6;
        _tmp_6 = _cursor.getInt(_cursorIndexOfWednesday);
        _result.wednesday = _tmp_6 != 0;
        final int _tmp_7;
        _tmp_7 = _cursor.getInt(_cursorIndexOfThursday);
        _result.thursday = _tmp_7 != 0;
        final int _tmp_8;
        _tmp_8 = _cursor.getInt(_cursorIndexOfFriday);
        _result.friday = _tmp_8 != 0;
        final int _tmp_9;
        _tmp_9 = _cursor.getInt(_cursorIndexOfSaturday);
        _result.saturday = _tmp_9 != 0;
        final int _tmp_10;
        _tmp_10 = _cursor.getInt(_cursorIndexOfSunday);
        _result.sunday = _tmp_10 != 0;
        if (_cursor.isNull(_cursorIndexOfTextMessage)) {
          _result.textMessage = null;
        } else {
          _result.textMessage = _cursor.getString(_cursorIndexOfTextMessage);
        }
        final int _tmp_11;
        _tmp_11 = _cursor.getInt(_cursorIndexOfAlarmCanPlay);
        _result.alarmCanPlay = _tmp_11 != 0;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
