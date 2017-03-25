
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by DO000 on 3/25/2017.
 */
public class TransactionLoader {

    String filePath;

    public TransactionLoader(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Transaction> loadDataset() {
        ArrayList<Transaction> dataset = new ArrayList();
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] record = line.split(cvsSplitBy);
                String transactionID = record[0];
                String deviceID = record[1];
                String transactionValue= record[2];
                String accountId = record[3];
                String timeStamp = record[4];
                dataset.add(new Transaction(transactionID, deviceID, transactionValue, accountId, timeStamp));
                
                Timestamp ts = new Timestamp( Long.valueOf(record[4]) );
                if(record[3].equals("2604") )
                    System.out.println( dataset.get(dataset.size()-1).toString() );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataset;
    }
    
    public static void main(String args[]){
        TransactionLoader loader = new TransactionLoader("transaction.csv");
        ArrayList<Transaction> data = loader.loadDataset();
    }
    
}
