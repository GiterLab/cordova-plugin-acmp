package org.giterlab;

/**
 * Created by wxj on 2017/9/19.
 */

public class TagsAlias {
    private int tag_key;
    private String[] tag_value;
    private String alias;

    public int getTag_key() {
        return tag_key;
    }

    public void setTag_key(int tag_key) {
        this.tag_key = tag_key;
    }

    public String[] getTag_value() {
        return tag_value;
    }

    public void setTag_value(String[] tag_value) {
        this.tag_value = tag_value;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
