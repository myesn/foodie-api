package cn.myesn.enums;

public enum GenderEnum {
    female(0, "女"),
    male(1,"男"),
    secret(2, "保密");

    public final Integer value;
    public final String description;

    GenderEnum(Integer value, String description){
        this.value = value;
        this.description = description;
    }
}
