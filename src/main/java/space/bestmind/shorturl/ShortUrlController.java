package space.bestmind.shorturl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

/**
 * @author weicheng.zhao
 * @date 2020/4/3
 */
@Controller
public class ShortUrlController {
	
	private static final String BASE_URL ="http://127.0.0.1:8080/getOriginUrl?code=";
	
	@Resource
	private ShortCodeService shortCodeService;
	
	@RequestMapping("/getShortUrl")
	@ResponseBody
	public String getShortUrl(String url){
		return BASE_URL +shortCodeService.getShortCode(url);
	}
	
	@RequestMapping("/getOriginUrl")
	public RedirectView getOriginUrl(String code){
		return new RedirectView(shortCodeService.getOriginUrl(code));
	}
}
