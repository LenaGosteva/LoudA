package com.example.loudalarm.Room;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class Database_Impl extends Database {
  private volatile AlarmDAO _alarmDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `AlarmEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `isAlarmAdjustVolume` INTEGER NOT NULL, `vol` INTEGER NOT NULL, `hours` INTEGER NOT NULL, `minutes` INTEGER NOT NULL, `time_on_clock_in_hours_and_minutes` TEXT, `days` TEXT, `time` INTEGER NOT NULL, `vib` INTEGER NOT NULL, `on` INTEGER NOT NULL, `today` INTEGER NOT NULL, `monday` INTEGER NOT NULL, `tuesday` INTEGER NOT NULL, `wednesday` INTEGER NOT NULL, `thursday` INTEGER NOT NULL, `friday` INTEGER NOT NULL, `saturday` INTEGER NOT NULL, `sunday` INTEGER NOT NULL, `music` TEXT, `textMessage` TEXT, `alarmCanPlay` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '857295e50be326850ff4910a374621a4')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `AlarmEntity`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsAlarmEntity = new HashMap<String, TableInfo.Column>(21);
        _columnsAlarmEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("isAlarmAdjustVolume", new TableInfo.Column("isAlarmAdjustVolume", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("vol", new TableInfo.Column("vol", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("hours", new TableInfo.Column("hours", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("minutes", new TableInfo.Column("minutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("time_on_clock_in_hours_and_minutes", new TableInfo.Column("time_on_clock_in_hours_and_minutes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("days", new TableInfo.Column("days", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("time", new TableInfo.Column("time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("vib", new TableInfo.Column("vib", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("on", new TableInfo.Column("on", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("today", new TableInfo.Column("today", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("monday", new TableInfo.Column("monday", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("tuesday", new TableInfo.Column("tuesday", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("wednesday", new TableInfo.Column("wednesday", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("thursday", new TableInfo.Column("thursday", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("friday", new TableInfo.Column("friday", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("saturday", new TableInfo.Column("saturday", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("sunday", new TableInfo.Column("sunday", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("music", new TableInfo.Column("music", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("textMessage", new TableInfo.Column("textMessage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlarmEntity.put("alarmCanPlay", new TableInfo.Column("alarmCanPlay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAlarmEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAlarmEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAlarmEntity = new TableInfo("AlarmEntity", _columnsAlarmEntity, _foreignKeysAlarmEntity, _indicesAlarmEntity);
        final TableInfo _existingAlarmEntity = TableInfo.read(_db, "AlarmEntity");
        if (! _infoAlarmEntity.equals(_existingAlarmEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "AlarmEntity(com.example.loudalarm.Room.AlarmEntity).\n"
                  + " Expected:\n" + _infoAlarmEntity + "\n"
                  + " Found:\n" + _existingAlarmEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "857295e50be326850ff4910a374621a4", "ebae3a68ecea8ac47343ef67d8f2b895");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "AlarmEntity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `AlarmEntity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AlarmDAO.class, AlarmDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public AlarmDAO alarmDAO() {
    if (_alarmDAO != null) {
      return _alarmDAO;
    } else {
      synchronized(this) {
        if(_alarmDAO == null) {
          _alarmDAO = new AlarmDAO_Impl(this);
        }
        return _alarmDAO;
      }
    }
  }
}
