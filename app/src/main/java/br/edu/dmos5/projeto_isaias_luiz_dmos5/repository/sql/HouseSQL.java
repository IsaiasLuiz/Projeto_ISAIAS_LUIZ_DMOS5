package br.edu.dmos5.projeto_isaias_luiz_dmos5.repository.sql;

public abstract class HouseSQL {

    public static final String HOUSE_TABLE = "HOUSE_TABLE";

    public static final String HOUSE_ID = "HOUSE_ID";

    public static final String HOUSE_NAME = "HOUSE_NAME";

    public static final String HOUSE_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            HOUSE_TABLE + "(" + HOUSE_ID + " TEXT NOT NULL," +
            HOUSE_NAME + " TEXT NOT NULL, " +
            "PRIMARY KEY (" + HOUSE_ID + "))";

}
