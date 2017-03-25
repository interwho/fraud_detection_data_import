
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DO000
 */
public class TransactionStream {

    /*URL url;
    URLConnection conn;
    OutputStreamWriter writer;*/

    /*public TransactionStream(String urlPath) {
        try {            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void postData(String urlPath, String message) {
        try {
            URL url = new URL(urlPath);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(message);
            writer.flush();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            //writer.close();
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        TransactionLoader loader = new TransactionLoader("transaction.csv");
        ArrayList<Transaction> data = loader.loadDataset();
        TransactionStream stream = new TransactionStream();
        try {
            for (int i = 0; i < data.size(); i++) {
                System.out.println(data.get(i).toJsonString());
                stream.postData("http://172.104.35.219/api/datastream/transactions", data.get(i).toJsonString());
                TimeUnit.MILLISECONDS.sleep(100);
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
}
