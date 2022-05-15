package com.codeus.utils;

import com.codeus.config.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class FileUtils {

	@Autowired
	private WebApplicationContext context;

	//TODO : user, group id 로 저장할 수 있게 만들자
	public String parseFileInfo(String boardtype, String id,MultipartHttpServletRequest file)throws IOException{
//		MultipartHttpServletRequest file = ((MultipartHttpServletRequest) RequestContextHolder.currentRequestAttributes()).getHeader("file");

		if(ObjectUtils.isEmpty(file)) {
			return null;
		}

		String root_path = context.getServletContext().getRealPath("/");
		String attach_path = "upload/";
		String board_path = boardtype + "/";


		File path = new File(root_path + attach_path+ board_path);

		// file.exists : 저장된 파일이 있는지 확인
		if(path.exists() == false) {
			// mkdirs : 만들고자 하는 디렉토리의 상위 디렉토리가 존재하지 않을 경우, 상위 디렉토리까지 생성
			path.mkdirs();
		}

		//동일한 이름의 파일이 있는지 확인 => 파일이 있으면 삭제한 후 업로드
		File idPath = new File(root_path + attach_path+ board_path+id);
		if(idPath.exists()==true){
			path.delete();
		}


		String url;


		Iterator<String> iterator = file.getFileNames();
		while (iterator.hasNext()) {
			List<MultipartFile> list = file.getFiles(iterator.next());
			for (MultipartFile mf : list) {
				if (file.getContentLength() > 0) {
					url = root_path + attach_path + board_path + id;

					File newfile = new File(root_path + attach_path +board_path+id);
					mf.transferTo(newfile);
					log.info(url);
					return url;
				} else {
					throw new BadRequestException("file not exist");
				}
			}
		}

		return null;
	}
}


