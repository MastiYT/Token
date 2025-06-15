package com.example.tokensmp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class TokenManager {

    private final Map<String, Token> tokenMap = new HashMap<>();
    private final Random random = new Random();

    public void loadTokens(FileConfiguration config) {
        tokenMap.clear();

        if (config.contains("tokens")) {
            for (String key : config.getConfigurationSection("tokens").getKeys(false)) {
                String name = config.getString("tokens." + key + ".name");
                int modelData = config.getInt("tokens." + key + ".custom_model_data");
                String power = config.getString("tokens." + key + ".power");
                int duration = config.getInt("tokens." + key + ".duration");
                int cooldown = config.getInt("tokens." + key + ".cooldown");

                Token token = new Token(key, name, modelData, power, duration, cooldown);
                tokenMap.put(key, token);
            }
        }
    }

    public Token getToken(String name) {
        return tokenMap.get(name.toLowerCase());
    }

    public List<String> getAllTokenNames() {
        return new ArrayList<>(tokenMap.keySet());
  
