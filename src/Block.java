import java.util.Date;

/*
This class represents a single block in our blockchain
 */
public class Block {

    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;

    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        return StringUtil.applySha256(
                this.data +
                this.previousHash +
                Long.toString(this.timeStamp));
    }

}
