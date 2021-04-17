package it.ipfsdownloadmonitor.web;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.ipfsdownloadmonitor.ipfs.IpfsManager;

 
@Controller
public class MainController {
	
	/** Logger **/
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/")
	public String root() {
		return "index";
	}
	
	
	@GetMapping("/download.htm")
	public String download(@RequestParam String cid) {
		logger.info("cid={}", cid);
		
		if (IpfsManager.getInstance().isDownloadRun()) {
			// do nothing
		} else {
			IpfsManager.getInstance().stopDownload();
			IpfsManager.getInstance().init("localhost", 5001);
			try {
				IpfsManager.getInstance().startDownload(cid);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return "monitor";
	}
	
	@GetMapping("/reset.htm")
	public String reset() {
		logger.info("");
		
		
		return "monitor";
	}
	
	
	@GetMapping("/monitor.htm")
	public String index(ModelMap model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		return "monitor";
	}

 
}