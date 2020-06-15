package com.lunz.fin.entity;

import lombok.Data;

/**
 * @author al
 * @date 2019/4/24 13:26
 * @description
 */
@Data
public class ObjDifferences {

    private String fieldName;

    private String fieldNameDesc;

    private Object oldValue;

    private String oldValueDesc;

    private Object newValue;

    private String newValueDesc;

    private boolean justRemind;

    public String getOldValueDesc() {
        if(oldValue==null){
            oldValueDesc = "空";
        }
        return oldValueDesc;
    }

    public void setOldValueDesc(String oldValueDesc) {
        this.oldValueDesc = oldValueDesc;
    }

    public String getNewValueDesc() {
        if(newValue==null){
            newValueDesc = "空";
        }
        return newValueDesc;
    }

    public void setNewValueDesc(String newValueDesc) {
        this.newValueDesc = newValueDesc;
    }
}
