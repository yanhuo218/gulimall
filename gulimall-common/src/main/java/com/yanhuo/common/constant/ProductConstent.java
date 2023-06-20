package com.yanhuo.common.constant;

public class ProductConstent {
    public enum AttrEnum{
        ATTR_TYPE_BASE(1,"基本属性"),ATTR_TYPE_SALE(0,"销售属性");
        AttrEnum(int code,String msg){
            this.code  = code;
            this.msg = msg;
        }
        private final int code;
        private final String msg;

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
