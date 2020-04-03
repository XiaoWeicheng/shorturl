package space.bestmind.shorturl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author weicheng.zhao
 * @date 2020/4/3
 */
@Service
public final class ShortCodeService {

    private static final int LENGTH = 6;
    private static final char[] DICTIONARY = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z' };
    private final Map<String, String> CACHE = new HashMap<>();

    public String getShortCode(String url) {
        String hashString = generateShortCode(url);
        CACHE.put(hashString, url);
        return hashString;
    }

    public String getOriginUrl(String code) {
        return CACHE.get(code);
    }

    private String generateShortCode(String s) {
        return Optional.ofNullable(s).map(String::hashCode).map(this::transHashCode).map(String::valueOf).orElse(null);
    }

    private String transHashCode(int hashCode) {
        char[] temp = new char[] { 0, 0, 0, 0, 0, 0 };
        int start = LENGTH - 1;
        while (hashCode > 0) {
            temp[start] = DICTIONARY[hashCode % 62];
            hashCode /= 62;
            start--;
        }
        for (; start >= 0; start--) {
            temp[start] = '0';
        }
        return String.valueOf(temp);
    }
}
