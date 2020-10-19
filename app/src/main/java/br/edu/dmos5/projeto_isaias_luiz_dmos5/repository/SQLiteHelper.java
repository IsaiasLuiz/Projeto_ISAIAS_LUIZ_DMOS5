package br.edu.dmos5.projeto_isaias_luiz_dmos5.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import br.edu.dmos5.projeto_isaias_luiz_dmos5.repository.sql.CharacterSQL;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.repository.sql.HouseSQL;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "hogwarts.db";

    private Context context;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(HouseSQL.HOUSE_CREATE_TABLE);
        sqLiteDatabase.execSQL(CharacterSQL.CHARACTER_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
