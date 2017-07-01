package com.prizeclaw.shwdtech.inetprizeclaw.bean;

/**
 * Created by autyan on 7/1/2017.
 */

public class MachineOperateResultBean {
    private String ClientCode;

    private Boolean IsOperateSuccess;

    private Boolean IsOperateResultOk;

    private String OperateMessage;

    public String getClientCode() {return ClientCode;}

    public void setClientCode(String clientCode) {this.ClientCode = clientCode;}

    public Boolean getIsOperateSuccess() {return IsOperateSuccess;}

    public void setIsOperateSuccess(Boolean isOperateSuccess) {this.IsOperateSuccess = isOperateSuccess;}

    public Boolean getIsOperateResultOk() {return IsOperateResultOk;}

    public void setIsOperateResultOk(Boolean isOperateResultOk) {this.IsOperateResultOk = isOperateResultOk;}

    public String getOperateMessage() {return OperateMessage;}

    public void setOperateMessage(String operateMessage) {this.OperateMessage = operateMessage;}
}
