package peggame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
public class TriangleReader {
    /**
     * attempts to read a board from a given file
     * @param filename
     * @return PegBoard object
     * @throws IOException if the file is not found
     */
    public static PegGame readBoard(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader reader = new BufferedReader(fileReader);
        LinkedHashMap<Location,String> Board = new LinkedHashMap<>();

        String line = reader.readLine();
        int rows = Integer.parseInt(line);
        int rowCount = 0;
        while((line = reader.readLine()) != null) {
            String[] tokens = line.split("");
            for(int i = 0; i < tokens.length; i++) {
                Location location = new Location(rowCount, i);
                if (tokens[i].equals(".")) {
                    Board.put(location, "-");
                }
                else {
                    Board.put(location, tokens[i]);
                }
                
            }
            rowCount++;
        }
        PegGame pegBoard = new TriangleBoard(rows, Board);
        reader.close();
        fileReader.close();
        return pegBoard;
    }

}
