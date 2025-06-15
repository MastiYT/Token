package com.example.tokensmp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TokenSMP extends JavaPlugin {

    private static TokenSMP instance;
    private TokenManager tokenManager;

    @Override
    public void onEnable() {
        instance = this;

        // Save default config
        saveDefaultConfig();

        // Load all tokens
        tokenManager = new TokenManager();
        tokenManager.loadTokens(getConfig());

        // Register events
        Bukkit.getPluginManager().registerEvents(new TokenListener(tokenManager),
                                                 
