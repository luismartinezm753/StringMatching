package SuffixTrie;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by luism on 10-06-15.
 */
public class CompactCharSequence implements CharSequence, Serializable, Comparable, Appendable {
    static final long serialVersionUID = 1L;

    private static final String ENCODING = "ISO-8859-1";
    private final int offset;
    private final int end;
    private final byte[] data;

    public CompactCharSequence(String str) {
        try {
            data = str.getBytes(ENCODING);
            offset = 0;
            end = data.length;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unexpected: " + ENCODING + " not supported!");
        }
    }

    public char charAt(int index) {
        int ix = index+offset;
        if (ix >= end) {
            throw new StringIndexOutOfBoundsException("Invalid index " +
                    index + " length " + length());
        }
        return (char) (data[ix] & 0xff);
    }

    public int length() {
        return end - offset;
    }
    private CompactCharSequence(byte[] data, int offset, int end) {
        this.data = data;
        this.offset = offset;
        this.end = end;
    }
    public CharSequence subSequence(int start, int end) {
        if (start < 0) {
            throw new IllegalArgumentException("Illegal range " +
                    start + "-" + end + " for sequence of length " + length());
        }
        return new CompactCharSequence(data, start + offset, end + offset);
    }
    @Override
    public String toString() {
        try {
            return new String(data, offset, end-offset, ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unexpected: " + ENCODING + " not supported");
        }
    }
    @Override
    public int compareTo(Object o) {
        if (o instanceof CharSequence){
            CharSequence t= (CharSequence) o;
            int i = 0;
            while (i < this.length() && i < t.length()) {
                char a = this.charAt(i);
                char b = t.charAt(i);

                int diff = a - b;

                if (diff != 0) {
                    return diff;
                }

                i++;
            }
            return this.length() - t.length();
        }
        return 0;
    }

    @Override
    public Appendable append(CharSequence charSequence) throws IOException {
        /*StringBuilder builder=new StringBuilder(this);
        builder.append(charSequence);
        return new CompactCharSequence(builder.toString());*/
        char[] chars=new char[charSequence.length()];
        char[] thisChars=new char[this.length()];
        for (int i = 0; i < charSequence.length(); i++) {
            chars[i]=charSequence.charAt(i);
        }
        for (int i = 0; i < this.length(); i++) {
            thisChars[i]=this.charAt(i);
        }
        byte[] bytes = toBytes(chars);
        byte[] data=toBytes(thisChars);
        byte[] newData=concat(data,bytes);
        return new CompactCharSequence(newData,0,newData.length);
    }
    public byte[] concat(byte[] a, byte[] b) {
        int aLen = a.length;
        int bLen = b.length;
        byte[] c= new byte[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }
    private byte[] toBytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = Charset.forName("ISO-8859-1").encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
        return bytes;
    }

    @Override
    public Appendable append(CharSequence charSequence, int i, int i1) throws IOException {
        return null;
    }

    @Override
    public Appendable append(char c) throws IOException {
        return null;
    }
    @Override
    public boolean equals(Object s){
        if (s instanceof CompactCharSequence){
            return this.compareTo(s)==0;
        }
        return false;
    }
    public boolean startsWith(CharSequence sequence){

        for (int i = 0; i < sequence.length(); i++) {
            if (this.charAt(i)!=sequence.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public byte[] getData() {
        return data;
    }
}
