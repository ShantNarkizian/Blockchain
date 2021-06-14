import java.util.Date;

/*
This class represents a single block in our blockchain
 */
public class Block {

    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int miningVar;

    //constructor for a single block on the blockchain
    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    //algorithm to calculate a hash value for a block
    public String calculateHash(){
        return StringUtil.applySha256(
                this.data +
                this.previousHash +
                Long.toString(this.timeStamp) +
                Integer.toString(miningVar)
        );
    }

    /*
     method to mine blocks that start with
     difficulty 0's
    */
    public void mineBlock(int difficulty){

        //creates a string with difficulty # of 0s
        String target = new String(new char[difficulty]).replace('\0', '0');

        while(!hash.substring(0, difficulty).equals(target)){
            miningVar ++;
            this.hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

}
