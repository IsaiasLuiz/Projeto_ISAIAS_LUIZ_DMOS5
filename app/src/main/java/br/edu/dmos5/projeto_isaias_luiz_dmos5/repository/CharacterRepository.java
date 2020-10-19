package br.edu.dmos5.projeto_isaias_luiz_dmos5.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.Character;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.CharacterDetails;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.House;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.exceptions.EmptyDatabaseException;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.repository.sql.CharacterSQL;

public class CharacterRepository {

    private SQLiteDatabase sqLiteDatabase;

    private SQLiteHelper sqlLiteHelper;

    public CharacterRepository(Context context) {
        sqlLiteHelper = new SQLiteHelper(context);
    }

    public void save(CharacterDetails character) {
        if (character == null) {
            throw new NullPointerException();
        }

        SQLiteDatabase sqLiteDatabase = sqlLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CharacterSQL.CHARACTER_ID, character.getId());
        values.put(CharacterSQL.CHARACTER_NAME, character.getName());
        values.put(CharacterSQL.CHARACTER_ROLE, character.getRole());
        values.put(CharacterSQL.CHARACTER_HOUSE, character.getHouse());
        values.put(CharacterSQL.CHARACTER_MINISTRY_OF_MAGIC, character.isMinistryOfMagic() ? CharacterSQL.CHARACTER_IS_MINISTRY_OF_MAGIC : CharacterSQL.CHARACTER_IS_NOT_MINISTRY_OF_MAGIC);
        values.put(CharacterSQL.CHARACTER_DEATH_EATER, character.isDeathEater() ? CharacterSQL.CHARACTER_IS_DEATH_EATER : CharacterSQL.CHARACTER_IS_NOT_DEATH_EATER);
        values.put(CharacterSQL.CHARACTER_BLOOD_STATUS, character.getBloodStatus());
        values.put(CharacterSQL.CHARACTER_SPECIES, character.getSpecies());

        if (sqLiteDatabase.insert(CharacterSQL.CHARACTER_TABLE, null, values) == -1) {
            throw new SQLException();
        }
        sqLiteDatabase.close();
    }

    public CharacterDetails findById(String characterId) {
        if (characterId == null) {
            throw new NullPointerException();
        }

        Cursor cursor;
        sqLiteDatabase = sqlLiteHelper.getReadableDatabase();


        String columns[] = new String[]{
                CharacterSQL.CHARACTER_ID,
                CharacterSQL.CHARACTER_NAME,
                CharacterSQL.CHARACTER_ROLE,
                CharacterSQL.CHARACTER_HOUSE,
                CharacterSQL.CHARACTER_MINISTRY_OF_MAGIC,
                CharacterSQL.CHARACTER_DEATH_EATER,
                CharacterSQL.CHARACTER_BLOOD_STATUS,
                CharacterSQL.CHARACTER_SPECIES
        };

        String where = "upper(" + CharacterSQL.CHARACTER_ID + ") = upper(?)";
        String args[] = new String[]{characterId};

        cursor = sqLiteDatabase.query(
                CharacterSQL.CHARACTER_TABLE,
                columns,
                where,
                args,
                null,
                null,
                null
        );

        if (cursor.getCount() < 1) {
            cursor.close();
            sqLiteDatabase.close();
            throw new EmptyDatabaseException();
        }

        cursor.moveToNext();
        CharacterDetails characterDetails = new CharacterDetails(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4).equals(CharacterSQL.CHARACTER_IS_MINISTRY_OF_MAGIC),
                cursor.getString(5).equals(CharacterSQL.CHARACTER_IS_DEATH_EATER),
                cursor.getString(6),
                cursor.getString(7)
        );

        cursor.close();
        sqLiteDatabase.close();

        return characterDetails;
    }

}
