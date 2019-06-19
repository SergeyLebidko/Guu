package guu;

public class StackElement {

    private String subName;
    private Integer pointer;

    public StackElement(String subName, Integer pointer) {
        this.subName = subName;
        this.pointer = pointer;
    }

    public String getSubName() {
        return subName;
    }

    public Integer getPointer() {
        return pointer;
    }

    public void setPointer(Integer pointer) {
        this.pointer = pointer;
    }

    public void shiftPointer(){
        pointer++;
    }

}
