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
	
	private final Map<String,String> CACHE= new HashMap<>();
	
	public String getShortCode(String url){
		String hashString = hashString(url);
		CACHE.put(hashString, url);
		return hashString;
	}
	
	public String getOriginUrl(String code){
		return CACHE.get(code);
	}
	
	private String hashString(String s){
		return Optional.ofNullable(s).map(String::hashCode).map(Integer::toHexString).map(String::valueOf).orElse(null);
	}
}
