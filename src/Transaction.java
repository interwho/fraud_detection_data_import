/**
 * Created by DO000 on 3/25/2017.
 */
public class Transaction {
    String transactionID;
    String deviceID;
    String transactionValue;
    String accountId;
    String timeStamp;

    public Transaction(String transactionID, String deviceID, String transactionValue, String accountId, String timeStamp) {
        this.transactionID = transactionID;
        this.deviceID = deviceID;
        this.transactionValue = transactionValue;
        this.accountId = accountId;
        this.timeStamp = timeStamp;
    }
    
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(String transactionValue) {
        this.transactionValue = transactionValue;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Transaction{" + "transactionID=" + transactionID + ", deviceID=" + deviceID + ", transactionValue=" + transactionValue + ", accountId=" + accountId + ", timeStamp=" + timeStamp + '}';
    }   
    
    
   public String toJsonString(){
       return "id=" + transactionID +"&" +
               "device_id=" + deviceID +"&" +
               "transaction_value=" + transactionValue+ "&" +
               "account_id=" + accountId + "&" +
               "ts_millis=" + timeStamp;
   }
}
