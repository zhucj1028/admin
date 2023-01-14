package cn.zhucj1028.aclservice.entity;

import io.jsonwebtoken.Claims;

/**
 * @description: JWT验证信息
 * @author zhucj
 * @date 2023/1/11 20:17
 * @version 1.0
 */
public class CheckResult {

    private int errCode;

    private boolean success;

    private Claims claims;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

}
