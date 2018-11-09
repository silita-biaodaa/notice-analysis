package com.silita.biaodaa.analysisRules.vo;

/**
 * 成对匹配规则
 * Created by dh on 2018/11/5.
 */
public class PairRule {
    private String kRegex;

    private String vRegex;

    private String desc;

    public PairRule() {
    }

    public PairRule(String kRegex, String vRegex, String desc) {
        this.kRegex = kRegex;
        this.vRegex = vRegex;
        this.desc = desc;
    }

    public String getkRegex() {
        return kRegex;
    }

    public void setkRegex(String kRegex) {
        this.kRegex = kRegex;
    }

    public String getvRegex() {
        return vRegex;
    }

    public void setvRegex(String vRegex) {
        this.vRegex = vRegex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
