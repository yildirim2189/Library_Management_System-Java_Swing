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
public enum AccountType {
    ADMIN("ADMİN"),
    UYE("NORMAL ÜYE"),
    OGRETIM("ÖĞRETİM GÖREVLİSİ");
    
    private final String displayName;

    private AccountType(String displayName) {
        this.displayName = displayName;
    }
    
    public String displayName(){ return displayName; } 
    
}
