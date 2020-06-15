package com.lunz.fin.config.entity.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DtoEStage implements Serializable {

    private String publicKey;

    private String privateKey;

    private String assureNo;

    private String bankCode;

    private String platNo;

    private String bankType;

    private String productType;

    private String cocomId;

    private String marketingArchivesNum;

    private String url;

    private String esburl;

    private String SalerRoleID;

    private String FinSpecRoleID;

    private String PreAuditRoleID;

    private Boolean isCheckCredit;

    private Boolean isApplyCard;

    private Boolean isApplyStage;
}
