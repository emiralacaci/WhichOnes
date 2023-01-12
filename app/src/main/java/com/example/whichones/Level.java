package com.example.whichones;

public class Level {
    private String level;
    private String levelPermission;

    public Level(String level, String levelPermission) {
        this.level = level;
        this.levelPermission = levelPermission;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelPermission() {
        return levelPermission;
    }

    public void setLevelPermission(String levelPermission) {
        this.levelPermission = levelPermission;
    }
}
