package com.example.harshkumar.pickpokemon;

public class Pokemon {

    String name;
    String candyName;
    String img;
    int candyCount;
    double height;
    double weight;
    double egg;
    String spawnTime;
    double spawnChance;
    double avgSpawn;
    String[] types;
    String[] weakness;

    public Pokemon(String name, String candyName, String img, int candyCount, double height, double weight,
                   double egg, String spawnTime, double spawnChance, double avgSpawn,
                   String[] types, String[] weakness){
        this.name = name;
        this.candyName = candyName;
        this.candyCount =candyCount;
        this.height = height;
        this.weight = weight;
        this.egg = egg;
        this.spawnTime = spawnTime;
        this.spawnChance = spawnChance;
        this.avgSpawn = avgSpawn;
        this.types = types;
        this.weakness = weakness;
    }

    public String getName() {
        return name;
    }

    public String getCandyName() {
        return candyName;
    }

    public String getImg() {
        return img;
    }

    public int getCandyCount() {
        return candyCount;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getEgg() {
        return egg;
    }

    public String getSpawnTime() {
        return spawnTime;
    }

    public double getSpawnChance() {
        return spawnChance;
    }

    public double getAvgSpawn() {
        return avgSpawn;
    }

    public String[] getTypes() {
        return types;
    }

    public String[] getWeakness() {
        return weakness;
    }
}
