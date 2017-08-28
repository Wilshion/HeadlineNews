package com.wilshion.headlinenews.model.http.response;

import java.util.List;

/**
 * Created by Wilshion on 2017/8/7 15:12.
 * [description : ]
 * [version : 1.0]
 */
public class TestResponse {


    /**
     * ret : 0
     * desc : 
     * data : [{"custId":"603004","custName":"金玉珍律师","custStatus":0}]
     */

    private String ret;
    private String desc;
    private List<DataBean> data;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * custId : 603004
         * custName : 金玉珍律师
         * custStatus : 0
         */

        private String custId;
        private String custName;
        private int custStatus;

        public String getCustId() {
            return custId;
        }

        public void setCustId(String custId) {
            this.custId = custId;
        }

        public String getCustName() {
            return custName;
        }

        public void setCustName(String custName) {
            this.custName = custName;
        }

        public int getCustStatus() {
            return custStatus;
        }

        public void setCustStatus(int custStatus) {
            this.custStatus = custStatus;
        }
    }
}
