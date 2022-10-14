import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadData {

    public static final String PATH = "data\\JDT.csv";
    public static List<List<Float>> dataList = new ArrayList<List<Float>>();
    public static List<Float> labelList = new ArrayList<Float>();

    static {
        try {
            init();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static void init() throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(
                (new FileInputStream(new File(PATH)))));

        String str = buff.readLine();
        while (str != null) {
            String[] arr = str.split(",");
            labelList.add(Float.parseFloat(arr[61]));
            dataList.add(Arrays.asList(Float.parseFloat(arr[0]),
                    Float.parseFloat(arr[1]), Float.parseFloat(arr[2]), Float.parseFloat(arr[3]), Float.parseFloat(arr[4]),
                    Float.parseFloat(arr[5]), Float.parseFloat(arr[6]), Float.parseFloat(arr[7]), Float.parseFloat(arr[8]),
                    Float.parseFloat(arr[9]), Float.parseFloat(arr[10]), Float.parseFloat(arr[11]), Float.parseFloat(arr[12]),
                    Float.parseFloat(arr[13]), Float.parseFloat(arr[14]), Float.parseFloat(arr[15]), Float.parseFloat(arr[16]),
                    Float.parseFloat(arr[17]), Float.parseFloat(arr[18]), Float.parseFloat(arr[19]), Float.parseFloat(arr[20]),
                    Float.parseFloat(arr[21]), Float.parseFloat(arr[22]), Float.parseFloat(arr[23]), Float.parseFloat(arr[24]),
                    Float.parseFloat(arr[25]), Float.parseFloat(arr[26]), Float.parseFloat(arr[27]), Float.parseFloat(arr[28]),
                    Float.parseFloat(arr[29]), Float.parseFloat(arr[30]), Float.parseFloat(arr[31]), Float.parseFloat(arr[32]),
                    Float.parseFloat(arr[33]), Float.parseFloat(arr[34]), Float.parseFloat(arr[35]), Float.parseFloat(arr[36]),
                    Float.parseFloat(arr[37]), Float.parseFloat(arr[38]), Float.parseFloat(arr[39]), Float.parseFloat(arr[40]),
                    Float.parseFloat(arr[41]), Float.parseFloat(arr[42]), Float.parseFloat(arr[43]), Float.parseFloat(arr[44]),
                    Float.parseFloat(arr[45]), Float.parseFloat(arr[46]), Float.parseFloat(arr[47]), Float.parseFloat(arr[48]),
                    Float.parseFloat(arr[50]), Float.parseFloat(arr[51]), Float.parseFloat(arr[52]), Float.parseFloat(arr[53]),
                    Float.parseFloat(arr[54]), Float.parseFloat(arr[55]), Float.parseFloat(arr[56]), Float.parseFloat(arr[57]),
                    Float.parseFloat(arr[58]), Float.parseFloat(arr[59]), Float.parseFloat(arr[60])));
            str = buff.readLine();
        }
        buff.close();

    }
}