package br.edu.dmos5.projeto_isaias_luiz_dmos5.model;

public class CharacterDetails extends Character {

    private String role;

    private String house;

    private boolean ministryOfMagic;

    private boolean deathEater;

    private String bloodStatus;

    private String species;

    public CharacterDetails(String id, String name, String role, String house, boolean ministryOfMagic, boolean deathEater, String bloodStatus, String species) {
        super(id, name);
        this.role = role;
        this.house = house;
        this.ministryOfMagic = ministryOfMagic;
        this.deathEater = deathEater;
        this.bloodStatus = bloodStatus;
        this.species = species;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public boolean isMinistryOfMagic() {
        return ministryOfMagic;
    }

    public void setMinistryOfMagic(boolean ministryOfMagic) {
        this.ministryOfMagic = ministryOfMagic;
    }

    public boolean isDeathEater() {
        return deathEater;
    }

    public void setDeathEater(boolean deathEater) {
        this.deathEater = deathEater;
    }

    public String getBloodStatus() {
        return bloodStatus;
    }

    public void setBloodStatus(String bloodStatus) {
        this.bloodStatus = bloodStatus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "Papel: " + role + '\n' +
                "Casa: " + house + '\n' +
                "Ministério da Magia: " + (ministryOfMagic ? "Yes" : "No" + '\n') +
                "Comensal da Morte: " + (deathEater ? "Yes" : "No") + '\n' +
                "Sangue: " + bloodStatus + '\n' +
                "Espécie: " + species;
    }
}
