package cn.bootx.paymentcenter.code.paymodel;

import java.util.Objects;

import static cn.bootx.paymentcenter.code.paymodel.WalletCode.*;

/**   
* 钱包保护模式
* @author xxm  
* @date 2020/12/8 
*/
public enum WalletProtectionMode {
    /**
     * 无保护
     */
    NONE(PROTECTION_MODE_NONE, "无保护"),

    /**
     * PinCode
     */
    PIN_CODE(PROTECTION_MODE_PIN, "PIN CODE"),

    /**
     * 其他
     */
    OTHER(PROTECTION_MODE_OTHER, "其他(Finger Print/Face ID)");

    private final int type;

    private final String name;

    WalletProtectionMode(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getName(Integer type) {
        for (WalletProtectionMode value : WalletProtectionMode.values()) {
            if (Objects.equals(type, value.type)) {
                return value.name;
            }
        }
        return null;
    }
}
