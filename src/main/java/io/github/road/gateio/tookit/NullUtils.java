package io.github.road.gateio.tookit;

import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
public class NullUtils {

    public static BigDecimal ifNullDefaultZero(String val) {
        if (StringUtils.isBlank(val)) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(val.trim());
    }


    public static String ifNullDefaultBlank(String val) {
        if (StringUtils.isBlank(val)) {
            return org.apache.commons.lang3.StringUtils.EMPTY;
        }
        return val;
    }
}
