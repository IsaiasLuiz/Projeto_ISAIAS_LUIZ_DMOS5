package br.edu.dmos5.projeto_isaias_luiz_dmos5.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.House;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.exceptions.EmptyDatabaseException;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.repository.sql.HouseSQL;

public class HouseRepository {

    private SQLiteDatabase sqLiteDatabase;

    private SQLiteHelper sqlLiteHelper;

    public HouseRepository(Context context) {
        sqlLiteHelper = new SQLiteHelper(context);
    }

    public void save(House house) {
        if (house == null) {
            throw new NullPointerException();
        }

        SQLiteDatabase sqLiteDatabase = sqlLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(HouseSQL.HOUSE_ID, house.getId());
        values.put(HouseSQL.HOUSE_NAME, house.getName());

        if (sqLiteDatabase.insert(HouseSQL.HOUSE_TABLE, null, values) == -1) {
            throw new SQLException();
        }
        sqLiteDatabase.close();
    }

    public List<House> findAll() {
        List<House> houses = new LinkedList<>();
        Cursor cursor;

        sqLiteDatabase = sqlLiteHelper.getReadableDatabase();


        String columns[] = new String[]{
                HouseSQL.HOUSE_ID,
                HouseSQL.HOUSE_NAME
        };

        cursor = sqLiteDatabase.query(
                HouseSQL.HOUSE_TABLE,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.getCount() < 1) {
            cursor.close();
            sqLiteDatabase.close();
            throw new EmptyDatabaseException();
        }

        while (cursor.moveToNext()) {
            houses.add(new House(cursor.getString(0), cursor.getString(1)));
        }

        cursor.close();
        sqLiteDatabase.close();

        return houses;
    }


}
