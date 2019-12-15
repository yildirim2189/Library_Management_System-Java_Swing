/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yildirimbayrakci.enums_and_constants;

/**
 *
 * @author YILDIRIM
 */
public enum BookStatus {
    MEVCUT("MEVCUT"),
    REZERVE("REZERVE"),
    ODUNC_ALINMIS("ÖDÜNÇ ALINMIŞ"),
    KAYIP("KAYIP");
    
    private final String displayName;

    private BookStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String displayName(){ return displayName; } 
}
