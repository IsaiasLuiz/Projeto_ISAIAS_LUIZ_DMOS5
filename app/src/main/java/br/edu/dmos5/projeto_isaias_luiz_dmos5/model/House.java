package br.edu.dmos5.projeto_isaias_luiz_dmos5.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class House implements Serializable {

    public static final String HOUSE_KEY = "HOUSE_KEY";

    @SerializedName("_id")
    private String id;

    private String name;

    public House(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
