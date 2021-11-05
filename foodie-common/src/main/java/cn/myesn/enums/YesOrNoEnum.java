package cn.myesn.enums;

public enum YesOrNoEnum {
    no(0, "否"),
    yes(1, "是");

    public final Integer type;
    public final String value;

    YesOrNoEnum(Integer type,String value){
        this.type = type;
        this.value = value;
    }
}
