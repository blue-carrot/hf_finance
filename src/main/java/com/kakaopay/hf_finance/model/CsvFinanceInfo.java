package com.kakaopay.hf_finance.model;

public class CsvFinanceInfo {

    private String year;

    private String month;

    private String bnk0001;  //주택도시기금

    private String bnk0002;  //국민은행

    private String bnk0003;  //우리은행

    private String bnk0004;  //신한은행

    private String bnk0005;  //한국시티은행

    private String bnk0006;  //하나은행

    private String bnk0007;  //농협은행/수협은행

    private String bnk0008;  //외환은행

    private String bnk0009;  //기타은행

    public int getYear() {
        return removeComma(year).intValue();
    }

    public int getMonth() {
        return removeComma(month).intValue();
    }

    public Long bnk0001() {
        return removeComma(bnk0001);
    }

    public Long bnk0002() {
        return removeComma(bnk0002);
    }

    public Long bnk0003() {
        return removeComma(bnk0003);
    }

    public Long bnk0004() {
        return removeComma(bnk0004);
    }

    public Long bnk0005() {
        return removeComma(bnk0005);
    }

    public Long bnk0006() {
        return removeComma(bnk0006);
    }

    public Long bnk0007() {
        return removeComma(bnk0007);
    }

    public Long bnk0008() {
        return removeComma(bnk0008);
    }

    public Long bnk0009() {
        return removeComma(bnk0009);
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setBnk0001(String bnk0001) {
        this.bnk0001 = bnk0001;
    }

    public void setBnk0002(String bnk0002) {
        this.bnk0002 = bnk0002;
    }

    public void setBnk0003(String bnk0003) {
        this.bnk0003 = bnk0003;
    }

    public void setBnk0004(String bnk0004) {
        this.bnk0004 = bnk0004;
    }

    public void setBnk0005(String bnk0005) {
        this.bnk0005 = bnk0005;
    }

    public void setBnk0006(String bnk0006) {
        this.bnk0006 = bnk0006;
    }

    public void setBnk0007(String bnk0007) {
        this.bnk0007 = bnk0007;
    }

    public void setBnk0008(String bnk0008) {
        this.bnk0008 = bnk0008;
    }

    public void setBnk0009(String bnk0009) {
        this.bnk0009 = bnk0009;
    }

    private Long removeComma(String value){
        return Long.getLong( value.replaceAll(",", "") );
    }

    @Override
    public String toString() {
        return "CsvFinanceInfo{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", bnk0001='" + bnk0001 + '\'' +
                ", bnk0002='" + bnk0002 + '\'' +
                ", bnk0003='" + bnk0003 + '\'' +
                ", bnk0004='" + bnk0004 + '\'' +
                ", bnk0005='" + bnk0005 + '\'' +
                ", bnk0006='" + bnk0006 + '\'' +
                ", bnk0007='" + bnk0007 + '\'' +
                ", bnk0008='" + bnk0008 + '\'' +
                ", bnk0009='" + bnk0009 + '\'' +
                '}';
    }
}
