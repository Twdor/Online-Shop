package com.codecool.shop.dao.implementation.mem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MemManager {

    protected <T> void saveDataToJson(List<T> models, String jasonFile) throws IOException {
        Writer writer = new FileWriter(jasonFile);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(models, writer);
        writer.flush();
        writer.close();
    }

    protected static <T> List<T> getDataFromJson(TypeToken<List<T>> targets, String jsonFile) {
        List<T> data = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(jsonFile));
            data = new Gson().fromJson(reader, targets.getType());
            reader.close();
        } catch (Exception ignored) {}
        return data;
    }
}
