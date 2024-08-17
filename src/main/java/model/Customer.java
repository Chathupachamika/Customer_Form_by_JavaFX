package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.*;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@ToString
public class Customer {
    private static final String ID_FILE_PATH = "id_counter.txt";
    private static int idCounter = 0;
    private String id;
    private String name;
    private String address;
    private LocalDate dateofbirth;
    static {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ID_FILE_PATH));
            String lastId = reader.readLine();
            if (lastId != null) {
                idCounter = Integer.parseInt(lastId);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Customer(String id,String name, String address, String title, LocalDate dateofbirth) {
        this.id = id;
        this.name = title + name;
        this.address = address;
        this.dateofbirth = dateofbirth;
    }



}
