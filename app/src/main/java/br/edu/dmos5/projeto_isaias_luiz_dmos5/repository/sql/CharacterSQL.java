package br.edu.dmos5.projeto_isaias_luiz_dmos5.repository.sql;

public abstract class CharacterSQL {

    public static final String CHARACTER_TABLE = "CHARACTER_TABLE";

    public static final String CHARACTER_ID = "CHARACTER_ID";

    public static final String CHARACTER_NAME = "CHARACTER_NAME";

    public static final String CHARACTER_ROLE = "CHARACTER_ROLE";

    public static final String CHARACTER_HOUSE = "CHARACTER_HOUSE";

    public static final String CHARACTER_MINISTRY_OF_MAGIC = "CHARACTER_MINISTRY_OF_MAGIC";

    public static final String CHARACTER_IS_MINISTRY_OF_MAGIC = "1";

    public static final String CHARACTER_IS_NOT_MINISTRY_OF_MAGIC = "0";

    public static final String CHARACTER_DEATH_EATER = "CHARACTER_DEATH_EATER";

    public static final String CHARACTER_IS_DEATH_EATER = "1";

    public static final String CHARACTER_IS_NOT_DEATH_EATER = "0";

    public static final String CHARACTER_BLOOD_STATUS = "CHARACTER_BLOOD_STATUS";

    public static final String CHARACTER_SPECIES = "CHARACTER_SPECIES";

    public static final String CHARACTER_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            CHARACTER_TABLE + "(" + CHARACTER_ID + " TEXT NOT NULL, " +
            CHARACTER_NAME + " TEXT NOT NULL, " +
            CHARACTER_ROLE + " TEXT, " +
            CHARACTER_HOUSE + " TEXT, " +
            CHARACTER_MINISTRY_OF_MAGIC + " INTEGER, " +
            CHARACTER_DEATH_EATER + " INTEGER, " +
            CHARACTER_BLOOD_STATUS + " TEXT, " +
            CHARACTER_SPECIES + " TEXT, " +
            "PRIMARY KEY (" + CHARACTER_ID + "))";

}
