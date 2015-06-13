package SuffixTrie;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

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
        StringBuilder builder=new StringBuilder(this);
        builder.append(charSequence);
        return new CompactCharSequence(builder.toString());
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
}
