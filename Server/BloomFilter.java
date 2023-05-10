package src;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
public class BloomFilter {
    private BitSet bitSet;
    private int length;
    private ArrayList<MessageDigest> hashes;
    public BloomFilter(int length, String...algs) {
        hashes = new ArrayList<MessageDigest>();
        bitSet = new BitSet(length);
        this.length = length;
        bitSet.clear();
        for (String s:algs) {
            try {
                MessageDigest md = MessageDigest.getInstance(s);
                hashes.add(md);
            }
            catch (NoSuchAlgorithmException e){
                continue;
            }
        }
    }
    public void add(String word){
        BigInteger bint;
        for (MessageDigest md:hashes) {
            bint = new BigInteger(md.digest(word.getBytes()));
            bitSet.set(Math.abs(bint.intValue() % length));
        }
    }
    public boolean contains(String word){
        BigInteger bint;
        for (MessageDigest md:hashes){
            bint = new BigInteger(md.digest(word.getBytes()));
            if (!bitSet.get(Math.abs(bint.intValue() % length)))
                return false;
        }
        return true;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<bitSet.length();i++) {
            if(bitSet.get(i))
                sb.append('1');
            else
                sb.append('0');
        }
        return sb.toString();
    }
}
