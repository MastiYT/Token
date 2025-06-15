package com.example.tokensmp;

public class Token {

    private final String id;
    private final String displayName;
    private final int customModelData;
    private final String power;
    private final int duration;
    private final int cooldown;

    public Token(String id, String displayName, int customModelData, String power, int duration, int cooldown) {
        this.id = id;
        this.displayName = displayName;
        this.customModelData = customModelData;
        this.power = power;
        this.duration = duration;
        this.cooldown = cooldown;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public String getPower() {
        return power;
    }

    public int getDuration() {
        return duration;
    }

    public int getCooldown() {
        return cooldown;
    }
}
