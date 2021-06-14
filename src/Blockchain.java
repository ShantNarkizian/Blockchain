import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class Blockchain {

    public static ArrayList<Block> blockChain = new ArrayList<Block>();
    public static final int difficulty = 5;

    public static void main(String[] args){

        blockChain.add(new Block("First block", "0"));
        blockChain.get(0).mineBlock(difficulty);

        for(int i = 1; i < 3; i++){
            blockChain.add(new Block("Block number " + i, blockChain.get(blockChain.size() -1 ).hash));
            blockChain.get(i).mineBlock(difficulty);
        }

        //creates a json representation of the java Block object
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println(blockchainJson);

        System.out.println("Chain status: " + isValidChain());

    }

    public static Boolean isValidChain(){
        Block currentBlock;
        Block prevBlock;

        for(int i = 1; i < blockChain.size(); i++){
            currentBlock = blockChain.get(i);
            prevBlock = blockChain.get(i-1);

            //compare calculated hash with saved hash
            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current Block Hash error");
                System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(currentBlock));
                return false;
            }

            //compare calculated prev hash with saved prev hash
            if(!prevBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Previous Block Hash error");
                System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(prevBlock));
                return false;
            }
        }
        return true;
    }
}
