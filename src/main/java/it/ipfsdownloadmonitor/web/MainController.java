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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.ipfs.multihash.Multihash;
import it.ipfsdownloadmonitor.ipfs.IpfsManager;


@Controller
public class MainController {

	/** Logger **/
	private Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/")
	public ModelAndView root() {
		if (IpfsManager.getInstance().isDownloadRun()) {
			ModelAndView andView = new ModelAndView("monitor");
			andView.addObject("downloadrun", true);
			return andView;
		} 
		return new ModelAndView("index");
	}


	@GetMapping("/download.htm")
	public ModelAndView download(@RequestParam String cid) {
		logger.info("cid={}", cid);

		

		if (IpfsManager.getInstance().isDownloadRun()) {
			ModelAndView andView = new ModelAndView("index");
			andView.addObject("downloadrun", true);
			return andView;
		} else {
			// validate cid
			try {
				Multihash.fromBase58(cid);
			} catch (Throwable th) {
				ModelAndView andView = new ModelAndView("index");
				andView.addObject("errorcid", true);
				return andView;
			}
			
			try {
				IpfsManager.getInstance().stopDownload();
				IpfsManager.getInstance().init("localhost", 5001);
				try {
					IpfsManager.getInstance().startDownload(cid);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Throwable th) {
				ModelAndView andView = new ModelAndView("index");
				andView.addObject("erroripfs", true);
				return andView;
			}

		}
		return new ModelAndView("monitor");
	}

	@GetMapping(value = "/canceldownload")
	public String cancelDownload() {
		IpfsManager.getInstance().stopDownload();
		return "redirect:/";
	}


	@GetMapping("/monitor.htm")
	public String index(ModelMap model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		return "monitor";
	}


}