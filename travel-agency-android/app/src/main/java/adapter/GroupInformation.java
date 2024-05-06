package adapter;

import java.util.ArrayList;

public class GroupInformation {
    private String mainSetName;
    private ArrayList<ChildInfo> list = new ArrayList<ChildInfo>();

    public String getName() {
        return mainSetName;
    }

    public void setName(String mainSetName) {
        this.mainSetName = mainSetName;
    }

    public ArrayList<ChildInfo> getSubsetName() {
        return list;
    }

    public void setSubsetName(ArrayList<ChildInfo> subSetName) {
        this.list = subSetName;
    }
}
