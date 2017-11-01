package com.example.template4fx.model;

import java.util.HashMap;
import java.util.Map;

public class Settings
{
    private final Map<String, String> values = new HashMap<>();

    public Settings()
    {
    }

    public Map<String, String> getValues()
    {
        return values;
    }
}
