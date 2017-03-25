
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
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

    final static String urlPath = "http://172.104.35.219/api/datastream/transactions";
    URL url;
    URLConnection conn;
    OutputStreamWriter writer;
    BufferedReader reader;
    public TransactionStream(String urlPath){
        try{
            url = new URL(urlPath);
            conn = url.openConnection();
            conn.setDoOutput(true);
            writer = new OutputStreamWriter(conn.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void postData(String message) {
        try {
            writer.write(message);
            writer.flush();
            String line;            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        TransactionLoader loader = new TransactionLoader("transaction.csv");
        ArrayList<Transaction> data = loader.loadDataset();
        TransactionStream stream = new TransactionStream(urlPath);       
        try {
            for (int i = 0; i < 1000; i++) {
                System.out.println(data.get(i).toJsonString());
                stream.postData(data.get(i).toJsonString());
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
}
