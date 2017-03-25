
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DO000
 */
public class PosDevice {
    HashMap<String,String> posDevice;
    
    public PosDevice(String filePath){
        posDevice = new HashMap<String,String>();
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] record = line.split(cvsSplitBy);
                String deviceID = record[0];
                String location = record[1];
                posDevice.put(deviceID, location);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
    public void checkData(ArrayList<Transaction> dataset){
        for(Transaction record: dataset){
            if(! posDevice.containsKey(record.deviceID) ){
                System.out.println(record);
            }
        }
    }
    
    public static void main(String args[]){
        PosDevice posData = new PosDevice("pos_device");
        TransactionLoader loader = new TransactionLoader("transaction.csv");
        ArrayList<Transaction> transData = loader.loadDataset();
        posData.checkData(transData);
    }
}
