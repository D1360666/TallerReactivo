package co.com.sofka.taller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import co.com.sofka.taller.model.Player;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class csvUtilFile {
    private csvUtilFile(){}
    public static List<Player> getPlayers(){
        var uri =  csvUtilFile.class.getClassLoader().getResource("data.csv");
        List<Player> list = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(uri.getFile()))) {
            List<String[]> registers = reader.readAll();
            registers.forEach(strings -> list.add(new Player(
                    strings[0],
                    strings[1],
                    Integer.parseInt(Optional.of(strings[2].trim()).filter(h -> !h.isBlank()).orElse("0")),
                    strings[3],
                    strings[4],
                    Integer.parseInt(strings[5].trim()),
                    Integer.parseInt(strings[6].trim()),
                    strings[7],
                    Double.parseDouble(strings[8].trim())
            )));
            return list;
        } catch (IOException | CsvException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}