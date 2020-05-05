package sh.ncurl.util;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author mei
 * @date 2020/5/3
 */
public class IdUtil {

    /**
     * ID 生成器（三位随机数 + 时间戳）
     */
    public static final String generate() {
        try {
            int i = ThreadLocalRandom.current().nextInt(62 * 62 * 62 - 1);
            String time = StringUtils.leftPad(To62String(System.currentTimeMillis()), 7, '0'); // 前 7 位是时间戳，精确到毫秒。
            String random = StringUtils.leftPad(To62String(i), 3, '0'); // 后三位是随机数
            return time + random;
        } catch (Exception e) {
            throw new RuntimeException("ID 生成失败");
        }
    }

    /** Base64 字符 */
    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x',
            'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z',
            '*', '-'
    };

    /**
     * 10 进制转成 62 进制
     */
    private static final String To62String(long timeMillis) {
        int mask = 62;
        int bufLength = 11;
        int charPos = bufLength;
        char[] buf = new char[bufLength];
        do {
            buf[--charPos] = DIGITS[(int) (timeMillis % mask)];
            timeMillis = timeMillis / mask;
        } while (timeMillis > 0);
        return new String(buf, charPos, (bufLength - charPos));
    }
}
