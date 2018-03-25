package e.fikri6998.fikrialfitrarahadi_1202150086_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by fikri6998 on 3/25/2018.
 */

public class Database extends SQLiteOpenHelper {

    //deklarasi variabel yang akan digunakan
    Context mContext;
    SQLiteDatabase database;

    public static final String DB_NAME = "listtodo.db";
    public static final String TABLE_NAME = "daftartodo";
    public static final String col_1 = "todo";
    public static final String col_2 = "description";
    public static final String col_3 = "priority";


    //contructor yang digunakan
    public Database(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
        database = this.getWritableDatabase();
        database.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    //untuk membaca data dari database
    public void readData(ArrayList<Data> mArrayList) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT todo, description, priority FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            mArrayList.add(new Data(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }

    //untuk menghapus data pada database
    public boolean removeData(String ToDo) {
        return database.delete(TABLE_NAME, col_1 + "=\"" + ToDo + "\"", null) > 0;
    }

    //ketika table dibuat
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (todo varchar(35) primary key, description varchar(50), priority varchar(3))");

    }

    //ketika table sudah dibuat
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //ketika input data ke dalam table database
    public boolean inputData(Data list) {
        //mencocokkan kolom beserta nilainya
        ContentValues val = new ContentValues();
        val.put(col_1, list.getTodo());
        val.put(col_2, list.getDescription());
        val.put(col_3, list.getPriority());
        long hasil = database.insert(TABLE_NAME, null, val);
        if (hasil == -1) {
            return false;
        } else {
            return true;
        }
    }
}
