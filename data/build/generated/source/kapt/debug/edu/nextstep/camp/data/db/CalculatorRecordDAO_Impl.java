package edu.nextstep.camp.data.db;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import edu.nextstep.camp.data.model.CalculatorRecord;
import edu.nextstep.camp.domain.calculator.Expression;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CalculatorRecordDAO_Impl implements CalculatorRecordDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CalculatorRecord> __insertionAdapterOfCalculatorRecord;

  private final Convertors __convertors = new Convertors();

  public CalculatorRecordDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCalculatorRecord = new EntityInsertionAdapter<CalculatorRecord>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `CalculatorRecord` (`id`,`expression`,`result`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CalculatorRecord value) {
        stmt.bindLong(1, value.getId());
        final String _tmp = __convertors.expressionToJson(value.getExpression());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp);
        }
        stmt.bindLong(3, value.getResult());
      }
    };
  }

  @Override
  public void insertRecord(final CalculatorRecord record) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCalculatorRecord.insert(record);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Flow<List<CalculatorRecord>> getAllRecord() {
    final String _sql = "SELECT * FROM CalculatorRecord";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"CalculatorRecord"}, new Callable<List<CalculatorRecord>>() {
      @Override
      public List<CalculatorRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfExpression = CursorUtil.getColumnIndexOrThrow(_cursor, "expression");
          final int _cursorIndexOfResult = CursorUtil.getColumnIndexOrThrow(_cursor, "result");
          final List<CalculatorRecord> _result = new ArrayList<CalculatorRecord>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CalculatorRecord _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final Expression _tmpExpression;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfExpression)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfExpression);
            }
            _tmpExpression = __convertors.jsonToExpression(_tmp);
            final int _tmpResult;
            _tmpResult = _cursor.getInt(_cursorIndexOfResult);
            _item = new CalculatorRecord(_tmpId,_tmpExpression,_tmpResult);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
